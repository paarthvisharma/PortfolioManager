package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.PriorityQueue;

import model.utils.dollarCostAveraging;
import model.utils.StatusObject;
import model.utils.Transaction;

/**
 * FlexiblePortfolio interface extends portfolio. This portfolio
 * supports buying or selling stocks once it's created.
 */
public interface FlexiblePortfolio extends Portfolio {
  /**
   * Method to add dollar cost averaging plan.
   *
   * @param dca object of type dollarCostAveraging.
   */
  void addDCAPlan(dollarCostAveraging dca);

  /**
   * Method to get dollar cost averaging plan.
   *
   * @return list of plans.
   */
  List<dollarCostAveraging> getDcaPlans();

  /**
   * Method to add dollar cost averaging plan.
   *
   * @param dca object of type dollarCostAveraging.
   */
  void executeDCAPlan(dollarCostAveraging dca);

  /**
   * Method to set commission.
   *
   * @param commission amount.
   */
  void setCommission(double commission);

  /**
   * Method to get commission value.
   *
   * @param commission amount.
   */
  double getCommission(double commission);

  /**
   * Method to get previous transactions.
   *
   * @return return a queue.
   * @throws FileNotFoundException in case of missing file.
   */
  PriorityQueue<Transaction> getPreviousTransactions() throws FileNotFoundException;

  /**
   * Method to get the validated transactions.
   *
   * @param transactions queue of transactions.
   * @return return a queue.
   */
  StatusObject<PriorityQueue<Transaction>> getValidatedTransactions(
          PriorityQueue<Transaction> transactions);

  /**
   * Method to get path of the transactions file.
   *
   * @return returns a string.
   */
  String getPathToPortfolioTransactions();

  /**
   * Method to buy stocks.
   *
   * @param ticker   symbol of a company.
   * @param date     date string.
   * @param quantity amount of sticks.
   * @throws IOException in case of file not found.
   */
  void buyStock(String ticker, String date, double quantity) throws IOException;

  /**
   * Method to sell stocks.
   *
   * @param ticker   symbol of a company.
   * @param date     date string.
   * @param quantity amount of sticks.
   * @throws IOException in case of file not found.
   */
  void sellStock(String ticker, String date, double quantity) throws ParseException, IOException;

  /**
   * Method to load past transactions.
   *
   * @param user an object of type user.
   * @return returns a queue.
   * @throws FileNotFoundException in case of file not found.
   */
  PriorityQueue<Transaction> loadPastTransactions(User user) throws FileNotFoundException;

  /**
   * Method to get composition of a flexible portfolio.
   *
   * @param date date string.
   * @return returns a list of stock object.
   * @throws ParseException        in case of error while parsing a file.
   * @throws FileNotFoundException in case file is not found.
   */
  List<Stock> getCompositionOfFlexiblePortfolio(String date)
          throws ParseException, FileNotFoundException;

  /**
   * Method to update csv file.
   *
   * @param date date string.
   * @return returns a list.
   * @throws FileNotFoundException in case file is not found.
   * @throws ParseException        in case of error while parsing a file.
   */
  List<String[]> getCSVUptoDate(String date) throws FileNotFoundException, ParseException;
}
