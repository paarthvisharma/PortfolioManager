package model.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import model.AlphaVantageApi;
import model.Portfolio;
import model.Stock;
import model.StockApi;

import static utils.Utils.getConfigAndProgramState;

/**
 * This class contains static helper methods which are used by the model.
 */
public class Utils {

  /**
   * Static method to print portfolio in a readable format.
   */
  public static String portfolioToString(Portfolio portfolio, boolean ticker, boolean name,
                                         boolean quantity, boolean date) {
    StringBuilder toReturn = new StringBuilder("");
    toReturn.append("Portfolio Name: ").append(portfolio.getPortfolioName()).append("\n");
    toReturn.append("Portfolio ID: ").append(portfolio.getPortfolioId()).append("\n");
    toReturn.append("These are the stocks present in the portfolio and their details:\n");
    for (Stock i : portfolio.getListOfStock()) {
      toReturn.append("* ");
      if (ticker) {
        toReturn.append(i.getTicker()).append(" ");
      }
      if (name) {
        toReturn.append(i.getStockName()).append(" ");
      }
      if (date) {
        toReturn.append(i.getDate()).append(" ");
      }
      if (quantity) {
        toReturn.append(i.getStockQuantity()).append(" ");
      }
      toReturn.append("\n");
    }
    toReturn.append("\n");
    return toReturn.toString();
  }

  /**
   * A static method to arrange transactions in chronological order.
   *
   * @param path file path.
   * @throws IOException throws exception in case file is missing.
   */
  public static void makeCSVChronological(String path) throws IOException {
    PriorityQueue<Transaction> transactions = readCSVIntoQueue(path, true);
    writeToCSV(transactions, path);
  }

  /**
   * A static method to get the value for a stock on a particular date.
   *
   * @param ticker ticker symbol of a stock.
   * @param date   required date.
   * @return returns double value.
   */

  public static Double getValueOnDateForStock(String ticker, String date) {
    Map<String, String> config = getConfigAndProgramState();
    double priceOfStockOnDate = 0.0;
    int tempValueAfterComparison = 0;
    SimpleDateFormat dateParser;
    dateParser = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date tempDate = dateParser.parse(date);
      String stockFile = config.get("stockData") + "/" + ticker + ".csv";
      FileTime creationTime = (FileTime) Files.getAttribute(Path.of(stockFile),
              "creationTime");
      String fileModificationDate = dateParser.format(creationTime.toMillis());
      if (dateParser.parse(fileModificationDate).compareTo(dateParser.parse(date)) < 0) {
        StockApi stockApi = new AlphaVantageApi();
        stockApi.fetchAndCreateStockData(ticker);
      }
      File listedStockFile = new File(stockFile);
      Scanner stockValues = new Scanner(listedStockFile);
      String header = stockValues.nextLine();
      while (stockValues.hasNextLine()) {
        String[] rowValue = stockValues.nextLine().split(",");
        tempValueAfterComparison = (dateParser.parse(rowValue[0]).compareTo(tempDate));
        if (tempValueAfterComparison <= 0) {
          priceOfStockOnDate = Double.parseDouble(rowValue[4]);
          return priceOfStockOnDate;
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e.getMessage());
    } catch (ParseException e) {
      throw new IllegalArgumentException("Could not parse date");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return priceOfStockOnDate;
  }

  public static int compareDates(String baseDate, String comparableDate) throws ParseException {
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    return dateParser.parse(baseDate).compareTo(dateParser.parse(comparableDate));
  }

  /**
   * Static method to create a priority queue for transactions.
   *
   * @param pathToCSV         path to the transaction CSV file.
   * @param transactionStatus status.
   * @return transactions.
   * @throws FileNotFoundException throws exception in case of missing file.
   */
  public static PriorityQueue<Transaction> readCSVIntoQueue(
          String pathToCSV, boolean transactionStatus)
          throws FileNotFoundException {
    PriorityQueue<Transaction> transactions = new PriorityQueue<>();
    File csvFile = new File(pathToCSV);
    Scanner readFile = new Scanner(csvFile);
    while (readFile.hasNext()) {
      String line = readFile.nextLine();
      if (line.length() > 1) {
        transactions.add(new Transaction(line.split(","), transactionStatus));
      }
    }
    return transactions;
  }

  /**
   * Static method to write values to CSV file.
   *
   * @param transactions transaction values.
   * @param path         path to the transaction CSV file.
   * @throws IOException throws exception in case of missing file.
   */
  public static void writeToCSV(PriorityQueue<Transaction> transactions, String path)
          throws IOException {
    StringBuilder newCsv = new StringBuilder();
    while (!transactions.isEmpty()) {
      newCsv.append(transactions.poll().toCSV()).append("\n");
    }
    Files.writeString(Path.of(path), newCsv.toString());
  }

  /**
   * Static method to get path for directory.
   */
  public static String getParentDirPath(String path) {
    String[] brokenPath = path.trim().split("/");
    StringBuilder parentPath = new StringBuilder();
    for (int i = 1; i < brokenPath.length - 1; i++) {
      parentPath.append("/").append(brokenPath[i]);
    }
    return parentPath.toString();
  }

  /**
   * Static method to validate date input.
   */
  public static void validateForLegalDate(String date) throws IllegalArgumentException {
    boolean regexValid = Pattern.matches("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$", date);
    if (!regexValid) {
      throw new IllegalArgumentException("Date does not follow the YYYY-MM-DD format.");
    }
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    try {
      dateParser.parse(date);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Date does not follow the YYYY-MM-DD format.");
    }
  }

  /**
   * Method to get the listing date of a ticker.
   *
   * @param ticker ticker symbol for which listing date is needed.
   * @return The listing date in a sting format.
   * @throws IllegalArgumentException If the ticker is invalid.
   */
  public static String getListingDateForTicker(String ticker) throws IllegalArgumentException {
    try {
      Map<String, String> config = getConfigAndProgramState();
      File stockList = new File(config.get("listedStocks"));
      Scanner myReader = new Scanner(stockList);
      while (myReader.hasNextLine()) {
        String[] row = myReader.nextLine().split(",\\s*");
        String temp = row[0];
        if (temp.equalsIgnoreCase(ticker)) {
          return row[4];
        }
      }
      throw new IllegalArgumentException("Ticker is invalid");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Ticker is invalid");
    }
  }

  /**
   * Validates if a date is later the listing date of a stock.
   *
   * @param date        date of buying or selling a stock.
   * @param listingDate listing date of the stock in question.
   * @throws IllegalArgumentException If the date if before the listing date this error is thrown.
   */
  public static void validateForDateAfterListing(String date, String listingDate)
          throws IllegalArgumentException {
    validateForLegalDate(date);
    validateForLegalDate(listingDate);
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    try {
      if (dateParser.parse(date).compareTo(dateParser.parse(listingDate)) < 0) {
        throw new IllegalArgumentException("Can buy a stock before it was listed");
      }
    } catch (ParseException e) {
      throw new IllegalArgumentException("Date does not follow the YYYY-MM-DD format.");
    }
  }

  /**
   * Static method to verify if a double number is an non negative integer.
   *
   * @param number The double number to be tested.
   */
  public static void validateForNonZeroInteger(double number) {
    if (number < 0 | number - Math.round(number) != 0) {
      throw new IllegalArgumentException("The stock quantity should be a positive integer.");
    }
  }

  public static void validateForPositiveDouble(double number) {
    if (number < 0) {
      throw new IllegalArgumentException("The stock quantity should be a positive number.");
    }
  }

  /**
   * Static method to verify if a ticker is supported/valid.
   *
   * @param ticker Ticker to be validated.
   * @throws IllegalArgumentException When the ticker symbol is invalid.
   * @throws FileNotFoundException    When the listedStocks file is not found.
   */
  public static void validateTicker(String ticker)
          throws IllegalArgumentException, FileNotFoundException {
    StockApi stockApi = new AlphaVantageApi();
    Map<String, String> config = getConfigAndProgramState();
    File stockList = new File(config.get("listedStocks"));
    Scanner myReader = new Scanner(stockList);
    while (myReader.hasNextLine()) {
      String[] row = myReader.nextLine().split(",\\s*");
      String temp = row[0];
      if (temp.equalsIgnoreCase(ticker)) {
        return;
      }
    }
    throw new IllegalArgumentException("The stock ticker is invalid.");
  }

  public static String getPresentDate() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    return dtf.format(now);
  }

  public static String incrementDate(String date, int incrementNumber) throws ParseException {
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    c.setTime(dateParser.parse(date));
    c.add(Calendar.DATE, incrementNumber);
    return dateParser.format(c.getTime());
  }

  public static Map<Double, Integer> getDaysMappingAscending(String startDate, String endDate) throws ParseException {
    validateForLegalDate(startDate);
    validateForLegalDate(endDate);
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    long date1 = dateParser.parse(startDate).getTime();
    long date2 = dateParser.parse(endDate).getTime();
    long timeDiff = Math.abs(date2 - date1);
    double days = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
    double weeks = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) / 7;
    double biWeeks = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) / 14;
    double months = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) / 30;
    double quarter = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) / 90;
    double sixMonths = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) / 180;
    double years = (double) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS) / 365;

    Map<Double, Integer> daysMappingAscending = new HashMap<>();
    daysMappingAscending.put(days, 1);
    daysMappingAscending.put(weeks, 7);
    daysMappingAscending.put(biWeeks, 14);
    daysMappingAscending.put(months, 30);
    daysMappingAscending.put(quarter, 90);
    daysMappingAscending.put(sixMonths, 180);
    daysMappingAscending.put(years, 365);
    return daysMappingAscending;
  }

  public static Map<String, Double> getNumberOfSticksAndTimeMultiple(Map<Double, Integer> daysMappingAscending, String startDate) throws ParseException {
    HashMap<String, Double> toReturn = new HashMap<>();
    validateForLegalDate(startDate);
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    c.setTime(dateParser.parse(startDate));
    c.setTime(dateParser.parse(startDate));
    for (double timeQuant : daysMappingAscending.keySet()) {
      if (timeQuant > 5 & timeQuant < 30) {
        toReturn.put("numberOfSticks", timeQuant);
        toReturn.put("timeMultiple", daysMappingAscending.get(timeQuant) + 0.0);
        break;
      }
    }
    return toReturn;
  }

  public static List<String> generateDates(
          double numberOfSticks, int timeMultiple, String startDate, String endDate,
          boolean includeRemainderDate) throws ParseException {
    validateForLegalDate(startDate);
    validateForLegalDate(endDate);
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    c.setTime(dateParser.parse(startDate));
    List<String> valuesForDates = new ArrayList<>();
    while (numberOfSticks > 0) {
      valuesForDates.add(dateParser.format(c.getTime()));
      c.add(Calendar.DATE, (int) timeMultiple);
      numberOfSticks -= 1;
    }
    if (numberOfSticks != 0 & includeRemainderDate) {
      valuesForDates.add(endDate);
    }
    return valuesForDates;
  }

  public static List<Double> getPortfolioValuationsForDates(Portfolio portfolio, List<String> dates) throws FileNotFoundException, ParseException {
    List<Double> valuations = new ArrayList<>();
    for (String date : dates) {
      valuations.add(portfolio.getValueForDate(date));
    }
    return valuations;
  }
}