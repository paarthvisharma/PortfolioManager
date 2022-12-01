package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import model.utils.DollarCostAveraging;
import model.utils.StatusObject;
import model.utils.Transaction;
import model.utils.XmlFormat;

import static model.utils.Utils.compareDates;
import static model.utils.Utils.getPresentDate;
import static model.utils.Utils.getValueOnDateForStock;
import static model.utils.Utils.incrementDate;
import static model.utils.Utils.portfolioToString;
import static utils.Utils.getConfigAndProgramState;

/**
 * Flexible interface extends portfolio. This portfolio support buying or
 * selling stocks once it's created.
 */

public class FlexiblePortfolioImpl implements FlexiblePortfolio {

  private final String portfolioName;

  private final Map<String, String> config = getConfigAndProgramState();

  private final String type = "flexible";
  private final int portfolioId;
  private List<Stock> listOfStocks;
  private final String portfolioPath;
  private double commission;

  private List<DollarCostAveraging> dcaPlans = new ArrayList<>();

  /**
   * This is the constructor for FlexiblePortfolioImpl. An Instance of flexible portfolio
   * is created with the help of this constructor.
   * It creates a flexible portfolio by taking the following parameters.
   *
   * @param portfolioName name of the portfolio.
   * @param portfolioId   ID of the portfolio.
   * @param listOfStocks  stock list.
   * @param commission    value.
   * @param portfolioPath file path.
   */
  public FlexiblePortfolioImpl(String portfolioName, int portfolioId,
                               List<Stock> listOfStocks, double commission, String portfolioPath) {
    this.portfolioName = portfolioName;
    this.portfolioId = portfolioId;
    this.listOfStocks = listOfStocks;
    this.portfolioPath = portfolioPath;
    File portfolioFile = new File(portfolioPath);
    this.commission = commission;
  }

  @Override
  public void addDCAPlan(DollarCostAveraging dca) {
    dcaPlans.add(dca);
    this.executeDCAPlan(dca);
  }

  @Override
  public List<DollarCostAveraging> getDcaPlans() {
    return this.dcaPlans;
  }

  @Override
  public void executeDCAPlan(DollarCostAveraging dca) {
    try {
      String cDate = getPresentDate();
      String eDate = dca.getEndDate();
      String sDate = dca.getStartDate();
      int interval = dca.getInterval();
      if (dca.getLastTransaction().equals("") & compareDates(cDate, sDate) < 0) {
        return;
      }
      if ((compareDates(cDate, eDate) >= 0 & !dca.getLastTransaction().equals(""))
              | compareDates(cDate, sDate) < 0) {
        return;
      }
      if (dca.getLastTransaction().equals("") & compareDates(cDate, sDate) == 0) {
        executeDCAPlanInstance(dca, sDate);
        return;
      }
      if (dca.getLastTransaction().equals("") & compareDates(cDate, sDate) > 0) {
        String tempDate = sDate;
        while (compareDates(tempDate, eDate) <= 0 & compareDates(tempDate, cDate) <= 0) {
          executeDCAPlanInstance(dca, tempDate);
          tempDate = incrementDate(tempDate, interval);
        }
        return;
      }
      if (!dca.getLastTransaction().equals("") & compareDates(cDate, eDate) <= 0
              & compareDates(cDate, dca.getLastTransaction()) != 0) {
        String tempDate = dca.getLastTransaction();
        while (compareDates(tempDate, eDate) <= 0 & compareDates(tempDate, cDate) <= 0) {
          executeDCAPlanInstance(dca, tempDate);
          tempDate = incrementDate(tempDate, interval);
        }
        return;
      }
      throw new RuntimeException("Un expected condition occurred");
    } catch (ParseException e) {
      throw new IllegalArgumentException("Dates are not in the correct format in DCA plan");
    } catch (IOException e) {
      throw new RuntimeException("Necessary stock file is not present");
    }
  }

  private void executeDCAPlanInstance(DollarCostAveraging dca, String date) throws IOException {
    double portfolioCommission = this.commission;
    this.commission = dca.getCommission() / dca.getDcaData().size();
    double investibleAmount = dca.getDollarAmount() - dca.getCommission();
    for (List<String> stockData: dca.getDcaData()) {
      this.buyStock(stockData.get(0), date,
              (investibleAmount * Double.parseDouble(stockData.get(3)) / 100)
                      / getValueOnDateForStock(stockData.get(0), date));
    }
    dca.setLastTransaction(date);
    this.commission = portfolioCommission;
  }

  @Override
  public void setCommission(double commission) {
    this.commission = commission;
  }

  @Override
  public double getCommission(double commission) {
    return this.commission;
  }

  @Override
  public PriorityQueue<Transaction> getPreviousTransactions() throws FileNotFoundException {
    PriorityQueue<Transaction> transactions = new PriorityQueue<>();
    Scanner reader = new Scanner(new File(this.portfolioPath));
    while (reader.hasNext()) {
      transactions.add(new Transaction(reader.nextLine().split(","), true));
    }
    return transactions;
  }

  @Override
  public String getPathToPortfolioTransactions() {
    return this.portfolioPath;
  }

  @Override
  public StatusObject<PriorityQueue<Transaction>> getValidatedTransactions(
          PriorityQueue<Transaction> transactions) {
    PriorityQueue<Transaction> validTransactions = new PriorityQueue<>();
    boolean valid = true;
    Map<String, Double> stockTrack = new HashMap<>();
    while (!transactions.isEmpty()) {
      Transaction t = transactions.poll();
      if (stockTrack.containsKey(t.getTicker())) {
        if (t.getType().equalsIgnoreCase("BUY")) {
          stockTrack.put(t.getTicker(), t.getQuantity() + stockTrack.get(t.getTicker()));
        } else if (t.getType().equalsIgnoreCase("SELL")) {
          if (stockTrack.get(t.getTicker()) - t.getQuantity() >= 0) {
            stockTrack.put(t.getTicker(), stockTrack.get(t.getTicker()) - t.getQuantity());
          } else {
            valid = false;
            break;
          }
        }
      } else {
        if (t.getType().equalsIgnoreCase("BUY")) {
          stockTrack.put(t.getTicker(), t.getQuantity());
        } else {
          valid = false;
          break;
        }
      }
      if (!t.isCompleted()) {
        validTransactions.add(t);
      }
    }
    if (!valid) {
      StringBuilder failedTransactions = new StringBuilder();
      while (!transactions.isEmpty()) {
        failedTransactions.append(transactions.poll().toString()).append("\n");
      }
      String returnMessage = "The following orders cannot be executed as they "
              + "make the portfolio unstable\n" + failedTransactions;
      return new StatusObject<PriorityQueue<Transaction>>(returnMessage, -1,
              validTransactions);
    }
    return new StatusObject<PriorityQueue<Transaction>>("All transactions "
            + "can be executed", 1, validTransactions);
  }

  @Override
  public String getPortfolioName() {
    return this.portfolioName;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public int getPortfolioId() {
    return this.portfolioId;
  }

  @Override
  public List<Stock> getListOfStock() {
    return this.listOfStocks;
  }

  @Override
  public double getValueForDate(String dateOnInfo) throws FileNotFoundException, ParseException {
    double value = 0;
    Map<String, Double> totalStocks = new HashMap<>();
    List<Stock> currentStocks = this.getCompositionOfFlexiblePortfolio(dateOnInfo);
    for (Stock stock : currentStocks) {
      totalStocks.put(stock.getTicker(), totalStocks.getOrDefault(stock.getTicker(),
              0.0) + stock.getStockQuantity());
    }
    for (String ticker : totalStocks.keySet()) {
      value += (getValueOnDateForStock(ticker, dateOnInfo) * totalStocks.get(ticker));
    }
    return value;
  }

  @Override
  public XmlFormat xml(int tabs) {
    int lTabs = tabs;
    StringBuilder toReturn = new StringBuilder();
    toReturn.append("\t".repeat(lTabs)).append("<flexiblePortfolio portfolioId=\"")
            .append(this.getPortfolioId()).append("\">\n");
    lTabs += 1;
    toReturn.append("\t".repeat(lTabs)).append("<portfolioName>")
            .append(this.getPortfolioName()).append("</portfolioName>\n");
    toReturn.append("\t".repeat(lTabs)).append("<stocks>\n");
    lTabs += 1;
    for (Stock stock : this.getListOfStock()) {
      XmlFormat stockXml = stock.xml(lTabs, true, true, true);
      toReturn.append(stockXml.xml);
      lTabs = stockXml.tabs;
    }
    lTabs -= 1;
    toReturn.append("\t".repeat(lTabs)).append("</stocks>\n");
    toReturn.append("\t".repeat(lTabs)).append(("<plans>\n"));
    lTabs += 1;
    for (DollarCostAveraging dcaPlan: this.dcaPlans) {
      toReturn.append("\t".repeat(lTabs)).append("<dcaPlan>").append(dcaPlan.toString());
      toReturn.append("</dcaPlan>\n");
    }
    lTabs -= 1;
    toReturn.append("\t".repeat(lTabs)).append(("</plans>\n"));
    lTabs -= 1;
    toReturn.append("\t".repeat(lTabs)).append("</flexiblePortfolio>\n");
    return new XmlFormat(toReturn.toString(), lTabs);
  }

  @Override
  public String toString() {
    String toReturn =  portfolioToString(
            this, true, true, true, true);
    toReturn = toReturn + "\n" + dcaPlans.toString();
    return toReturn;
  }

  @Override
  public void buyStock(String ticker, String date, double quantity) throws IOException {
    boolean stockExists = false;
    for (Stock stock : this.listOfStocks) {
      if (stock.getTicker().equalsIgnoreCase(ticker)
              & stock.getDate().equalsIgnoreCase(date)) {
        double newQuantity = stock.getStockQuantity() + quantity;
        stock.setStockQuantity(newQuantity);
        stockExists = true;
      }
    }
    if (!stockExists) {
      listOfStocks.add(new StockImpl(ticker, quantity, date, config, true));
    }
    FileWriter fw = new FileWriter(this.portfolioPath, true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(ticker + ", " + quantity + ", BUY, " + date + ", " + commission + ", true");
    bw.newLine();
    bw.close();
  }

  @Override
  public void sellStock(String ticker, String date, double quantity)
          throws ParseException, IOException {
    double existingQuantity = 0;
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    for (Stock stock : this.listOfStocks) {
      if (ticker.equalsIgnoreCase(stock.getTicker())
              & dateParser.parse(stock.getDate()).compareTo(dateParser.parse(date)) <= 0) {
        existingQuantity += stock.getStockQuantity();
      }
    }
    if (existingQuantity < quantity) {
      throw new IllegalArgumentException("You cant sell more shares than what you hold.");
    }
    double quantityToSell = quantity;
    for (Stock stock : this.listOfStocks) {
      if (ticker.equalsIgnoreCase(stock.getTicker())
              & dateParser.parse(stock.getDate()).compareTo(dateParser.parse(date)) <= 0) {
        if (quantityToSell > stock.getStockQuantity()) {
          quantityToSell -= stock.getStockQuantity();
          stock.setStockQuantity(0);
        } else {
          stock.setStockQuantity(stock.getStockQuantity() - quantityToSell);
          break;
        }
      }
    }
    this.listOfStocks.removeIf(s -> (s.getStockQuantity() == 0));
    FileWriter fw = new FileWriter(this.portfolioPath, true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(ticker + ", " + quantity + ", SELL, " + date + ", " + commission + ", true");
    bw.newLine();
    bw.close();
  }

  @Override
  public PriorityQueue<Transaction> loadPastTransactions(User user) throws FileNotFoundException {
    PriorityQueue<Transaction> transactions = new PriorityQueue<>();
    Scanner logFile = new Scanner(new File(config.get("userData") + "/"
            + user.getUserId() + "/" + this.portfolioId + ".csv"));
    while (logFile.hasNext()) {
      String line = logFile.nextLine();
      if (line.length() > 1) {
        transactions.add(new Transaction(line.split(","), true));
      }
    }
    return transactions;
  }

  @Override
  public List<Stock> getCompositionOfFlexiblePortfolio(String date)
          throws ParseException, FileNotFoundException {
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    List<String[]> csvData = getCSVUptoDate(date);
    Map<String, List<Stock>> stockTracker = new HashMap<>();
    for (String[] line : csvData) {
      if (stockTracker.containsKey(line[0].toLowerCase())) {
        if (line[2].equalsIgnoreCase("buy")) {
          boolean added = false;
          for (Stock stock : stockTracker.get(line[0].toLowerCase())) {
            if (dateParser.parse(line[3]).compareTo(dateParser.parse(stock.getDate())) == 0) {
              stock.setStockQuantity(stock.getStockQuantity() + Double.parseDouble(line[1]));
              added = true;
            }
          }
          if (!added) {
            stockTracker.get(line[0].toLowerCase()).add(new StockImpl(line[0],
                    Double.parseDouble(line[1]), line[3], config, false));
          }
        } else if (line[2].equalsIgnoreCase("sell")) {
          double toSubtract = Double.parseDouble(line[1]);
          for (Stock stock : stockTracker.get(line[0].toLowerCase())) {
            if (stock.getStockQuantity() >= toSubtract) {
              stock.setStockQuantity(stock.getStockQuantity() - toSubtract);
              break;
            } else {
              toSubtract = toSubtract - stock.getStockQuantity();
              stock.setStockQuantity(0);
            }
          }
        }
      } else {
        Stock createdStock =
                new StockImpl(line[0], Double.parseDouble(line[1]), line[3], config, false);
        List<Stock> listOfStocks = new ArrayList<>();
        listOfStocks.add(createdStock);
        stockTracker.put(line[0].toLowerCase(), listOfStocks);
      }
    }
    List<Stock> toReturn = new ArrayList<>();
    for (String ticker : stockTracker.keySet()) {
      for (Stock stock : stockTracker.get(ticker)) {
        if (stock.getStockQuantity() != 0) {
          toReturn.add(stock);
        }
      }
    }
    return toReturn;
  }

  @Override
  public List<String[]> getCSVUptoDate(String date) throws FileNotFoundException, ParseException {
    //  Scanner readFile = new Scanner(new File(config.get("userData")
    //  + "/" + user.getUserId() + "/" + this.portfolioId + ".csv"));
    Scanner readFile = new Scanner(new File(this.portfolioPath));
    List<String[]> csvData = new ArrayList<>();
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    while (readFile.hasNext()) {
      String[] csvLine = readFile.nextLine().split(", *");
      if (dateParser.parse(csvLine[3]).compareTo(dateParser.parse(date)) <= 0) {
        csvData.add(csvLine);
      } else {
        break;
      }
    }
    return csvData;
  }

}
