package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

import model.utils.XmlFormat;

import static model.utils.Utils.getValueOnDateForStock;
import static model.utils.Utils.validateForNonZeroInteger;

/**
 * This class is an implementation of the Stock Interface.
 * The methods associated with the class help perform operations on the Stock objects
 * such as getting stock date, name, tickerSymbol, value etc.
 */

public class StockImpl implements Stock {
  private final String stockName;
  private final String ticker;
  private double stockQuantity;
  private String date;

  private String listingDate;
  private final Map<String, String> config;
  private final StockApi stockApi = new AlphaVantageApi();

  /**
   * This is the constructor for StockImpl. An Instance of StockImpl is created
   * with the help of this constructor. It creates a StockImpl by taking the ticker,
   * stockQuantity, date, config.
   *
   * @param ticker        ticker Symbol of a company's stock.
   * @param stockQuantity number of stocks to be added.
   * @param date          date of purchase of the stocks.
   * @param config        directory structure where program related data is stored.
   */

  public StockImpl(String ticker, double stockQuantity, String date,
                   Map<String, String> config, boolean validate) {

    this.config = config;
    if (validate) {
      try {
        if (!this.validateTicker(ticker)) {
          throw new IllegalArgumentException("Ticker Symbol does not exist or is not currently"
                  + " supported. Please enter "
                  + "a valid Ticker Symbol");
        }
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
      if (!this.validateDateForTicker(date, ticker)) {
        throw new IllegalArgumentException("Please enter date in valid format YYYY-MM-DD that is "
                + "later than the listing date");
      }
      validateForNonZeroInteger(stockQuantity);
    }
    this.ticker = ticker;
    this.date = date;
    this.stockQuantity = stockQuantity;
    this.stockName = this.findStockName();
  }

  private String findStockName() {
    File stockList = new File(config.get("listedStocks"));
    Scanner myReader;
    try {
      myReader = new Scanner(stockList);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    while (myReader.hasNextLine()) {
      String[] row = myReader.nextLine().split(",\\s*");
      String temp = row[0];
      if (temp.equalsIgnoreCase(ticker)) {
        return row[1];
      }
    }
    return "";
  }

  @Override
  public String getListingDate() {
    return this.listingDate;
  }

  @Override
  public String getTicker() {
    return ticker;
  }

  @Override
  public String getStockName() {
    return this.stockName;
  }

  @Override
  public double getStockQuantity() {
    return this.stockQuantity;
  }

  @Override
  public void setStockQuantity(double stockQuantity) {
    this.stockQuantity = stockQuantity;
  }

  @Override
  public double getValue(String date) {
    return this.stockQuantity * this.getValueOnDate(date);
  }

  private Double getValueOnDate(String date) {
    return getValueOnDateForStock(this.ticker, date);
  }

  @Override
  public String getDate() {
    return date;
  }

  private boolean validateDateForTicker(String date, String ticker) {
    SimpleDateFormat dateParser;
    String listedDate = null;
    dateParser = new SimpleDateFormat("yyyy-MM-dd");
    dateParser.setLenient(false);
    try {
      if (date != null) {
        dateParser.parse(date);
      }
      File stockList = new File(config.get("listedStocks"));
      Scanner myReader = new Scanner(stockList);
      while (myReader.hasNextLine()) {
        String[] row = myReader.nextLine().split(",\\s*");
        if (row[0].equalsIgnoreCase(ticker)) {
          listedDate = row[4];
          this.listingDate = listedDate;
        }
      }
      if (date == null) {
        this.date = listedDate;
      } else if (dateParser.parse(date).compareTo(dateParser.parse(listedDate)) >= 0) {
        this.date = date;
      }
      return this.date != null;
    } catch (FileNotFoundException | ParseException e) {
      return false;
    }
  }

  private boolean validateTicker(String ticker) throws FileNotFoundException {
    File stockList = new File(config.get("listedStocks"));
    Scanner myReader = new Scanner(stockList);
    while (myReader.hasNextLine()) {
      String[] row = myReader.nextLine().split(",\\s*");
      String temp = row[0];
      if (temp.equalsIgnoreCase(ticker)) {
        String filename = ticker.toLowerCase() + ".csv";
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
          stockApi.fetchAndCreateStockData(ticker);
        } else {
          continue;
        }
        return true;
      }
    }
    return false;
  }

  @Override
  public String stockAsString() {
    return (this.ticker + " " + this.stockName + " " + this.stockQuantity + " " + this.date);
  }

  @Override
  public String toString() {
    return stockAsString();
  }

  @Override
  public XmlFormat xml(int tabs, boolean name, boolean quantity, boolean date) {
    int lTabs = tabs;
    StringBuilder toReturn = new StringBuilder();
    toReturn.append("\t".repeat(lTabs)).append("<stock ticker=\"").append(this.getTicker())
            .append("\">\n");
    lTabs += 1;
    if (name) {
      toReturn.append("\t".repeat(lTabs)).append("<stockName>").append(this.stockName)
              .append("</stockName>\n");
    }
    if (quantity) {
      toReturn.append("\t".repeat(lTabs)).append("<stockQuantity>")
              .append(this.stockQuantity).append("</stockQuantity>\n");
    }
    if (date) {
      toReturn.append("\t".repeat(lTabs)).append("<date>")
              .append(this.date).append("</date>\n");
    }
    lTabs -= 1;
    toReturn.append("\t".repeat(lTabs)).append("</stock>\n");
    return new XmlFormat(toReturn.toString(), lTabs);
  }
}
