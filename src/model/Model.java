package model;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

import model.utils.DollarCostAveraging;
import model.utils.StatusObject;
import model.utils.Transaction;

/**
 * The Model Interface represents the Model part of the Stocks Program out of the three
 * broad categories of model-view-controller (MVC) design.
 * This Interface represents the actual functionalities offered by the Stocks Program
 * and delegates it to the different classes like Stock, Portfolio, User and PortfolioManager.
 */
public interface Model {

  boolean validateTicker(String ticker);

  /**
   * Create a user in the portfolio management system.
   *
   * @return returns StatusObject containing the statusMessage, statusCode and the User Object.
   */
  StatusObject<User> createUser(String firstName, String lastName, double commission);

  /**
   * Create a user in the portfolio management system from a String pointing to a XML file.
   *
   * @return returns StatusObject containing the statusMessage, statusCode and the User Object.
   */
  StatusObject<User> createUserFromXML(String xmlPath);

  StatusObject<Portfolio> createPortfolioFromXML(User user, String xmlPath);

  /**
   * Updates the XML file for the passed user.
   *
   * @param user an object of type User.
   * @throws IOException in case directory does not exist.
   */
  void updateUserFile(User user) throws IOException;

  /**
   * Processes the userId and retrieves the user data.
   *
   * @param userId takes in the userId as a String.
   * @return returns StatusObject containing the statusMessage, statusCode and the User Object.
   */
  StatusObject<User> getUser(String userId);

  /**
   * Method to create the rigid portfolio object.
   *
   * @param user          an object of type User.
   * @param portfolioName name of the portfolio to be created.
   * @param listOfStocks  list of stocks to be added in portfolio.
   * @return returns StatusObject containing the statusMessage, statusCode and the Portfolio Object.
   */
  StatusObject<RigidPortfolio> createRigidPortfolio(
          User user, String portfolioName, List<Stock> listOfStocks);


  /**
   * Lists rigid portfolios that belong to a particular user.
   *
   * @param user an object of type User.
   * @return returns a list of portfolios.
   */

  List<RigidPortfolio> getRigidPortfoliosForUser(User user);

  /**
   * Lists flexible portfolios that belong to a particular user.
   *
   * @param user an object of type User.
   * @return returns a list of portfolios.
   */
  List<FlexiblePortfolio> getFlexiblePortfoliosForUser(User user);

  /**
   * Lists the details of a rigid portfolio belonging to a User.
   *
   * @param user        an object of type User.
   * @param portfolioId ID of the portfolio for which the details need to be fetched.
   * @return returns StatusObject containing the statusMessage, statusCode and the Portfolio Object.
   */
  StatusObject<RigidPortfolio> getParticularRigidPortfolio(
          User user, int portfolioId);

  /**
   * Lists the details of a flexible portfolio belonging to a User.
   *
   * @param user        an object of type User.
   * @param portfolioId ID of the portfolio for which the details need to be fetched.
   * @return returns StatusObject containing the statusMessage, statusCode and the Portfolio Object.
   */
  StatusObject<FlexiblePortfolio> getParticularFlexiblePortfolio(User user, int portfolioId);

  StatusObject<String> createDCAPlan(
          FlexiblePortfolio portfolio, String startDate, String endDate, String interval,
          String dollarAmount, String commission, List<List<String>> dcaData);

  /**
   * Processes the portfolio object and retrieves the portfolioId.
   *
   * @param portfolio an object of type Portfolio.
   * @return returns the portfolioId.
   */
  int getPortfolioId(Portfolio portfolio);

  /**
   * Processes the portfolio object and retrieves the portfolioName.
   *
   * @param portfolio an object of type Portfolio.
   * @return returns the portfolioName.
   */
  String getPortfolioName(Portfolio portfolio);

  /**
   * Processes the portfolio and retrieves the portfolio type.
   *
   * @param portfolio an object of type Portfolio.
   * @return returns the portfolioName.
   */
  String getPortfolioType(Portfolio portfolio);

  /**
   * Method to create the flexible portfolio object.
   *
   * @param user          an object of type User.
   * @param portfolioName name of the portfolio to be created.
   * @param listOfStocks  list of stocks to be added in portfolio.
   * @return returns StatusObject containing the statusMessage, statusCode and the Portfolio Object.
   */

  StatusObject<FlexiblePortfolio> createFlexiblePortfolio(User user, String portfolioName,
                                                          List<Stock> listOfStocks);

  /**
   * Method to calculate the value of portfolio.
   *
   * @param portfolio an object of type portfolio.
   * @param date      required date.
   * @return returns StatusObject containing the statusMessage, statusCode and the double Object.
   */
  StatusObject<Double> getValueOfPortfolioForDate(Portfolio portfolio, String date);

  /**
   * Method to get the userId of the next user that will be created.
   *
   * @return returns next userId.
   */

  int getNextUserId();

  /**
   * Method to create a Stock object.
   *
   * @param ticker   tickerSymbol of a stock.
   * @param quantity number of stocks to be added.
   * @param date     date of purchase of the stock YYYY-MM-DD.
   * @return an object of type Stock.
   */
  Stock createStock(String ticker, double quantity, String date);

  /**
   * Method to get stock ticker.
   *
   * @param stock object of type stock.
   * @return ticker string.
   */
  String getStockTicker(Stock stock);

  /**
   * Method to add stock to uninitialized portfolio.
   *
   * @param stock    an object of type stock.
   * @param quantity number of stock.
   * @return returns StatusObject containing the statusMessage, statusCode and the Stock Object.
   */
  StatusObject<Stock> addStockToUninitializedPortfolio(Stock stock, double quantity);

  /**
   * Method to get the stock purchase date.
   *
   * @param stock an object of type stock.
   * @return date string.
   */
  String getStockPurchaseDate(Stock stock);

  /**
   * Method to validate transactions.
   *
   * @param portfolio    an object of type portfolio.
   * @param transactions transaction values.
   * @return returns StatusObject containing the statusMessage, statusCode
   *                 and the priority queue object.
   */
  StatusObject<PriorityQueue<Transaction>> getValidatedTransactions(
          FlexiblePortfolio portfolio, PriorityQueue<Transaction> transactions);

  /**
   * Method to buy stocks in a flexible portfolio.
   *
   * @param portfolio an object of type portfolio.
   * @param ticker    ticker symbol.
   * @param date      date string.
   * @param quantity  number of stocks.
   * @return returns StatusObject containing the statusMessage, statusCode and the portfolio object.
   */
  StatusObject<FlexiblePortfolio> buyStockInFlexiblePortfolio(FlexiblePortfolio portfolio,
                                                              String ticker,
                                                              String date, double quantity);

  /**
   * Method to sell stocks in a flexible portfolio.
   *
   * @param portfolio an object of type portfolio.
   * @param ticker    ticker symbol.
   * @param date      date string.
   * @param quantity  number of stocks.
   * @return returns StatusObject containing the statusMessage,
   *                 statusCode and the portfolio object.
   */
  StatusObject<FlexiblePortfolio> sellStockInFlexiblePortfolio(FlexiblePortfolio portfolio,
                                                               String ticker,
                                                               String date, double quantity);

  /**
   * Method to get previous transactions for flexible portfolio.
   *
   * @param user      an object of type user.
   * @param portfolio an object of type portfolio.
   * @return returns StatusObject containing the statusMessage,
   *                 statusCode and the priority queue object.
   */

  StatusObject<PriorityQueue<Transaction>> getFlexiblePortfoliosPastTransactions(
          User user, FlexiblePortfolio portfolio);

  /**
   * Method to update CSV file for flexible portfolio.
   *
   * @param user      an object of type user.
   * @param portfolio an object of type portfolio.
   * @return returns StatusObject containing the statusMessage,
   *                 statusCode and the string.
   */
  StatusObject<String> updateFlexiblePortfolioCsv(User user, FlexiblePortfolio portfolio);

  /**
   * Method to view composition of flexible portfolio.
   *
   * @param user      an object of type user.
   * @param portfolio an object of type portfolio.
   * @param date      date string.
   * @return returns StatusObject containing the statusMessage,
   *                 statusCode and the string.
   */
  StatusObject<String> viewCompositionOfFlexiblePortfolio(User user,
                                                          FlexiblePortfolio portfolio, String date);

  StatusObject<List<List<String>>> getCompositionOfFlexiblePortfolioAsList(
          User user, FlexiblePortfolio portfolio, String date);

  /**
   * Method to calculate cost basis.
   *
   * @param user      an object of type user.
   * @param portfolio an object of type portfolio.
   * @param date      date string.
   * @return returns StatusObject containing the statusMessage,
   *                 statusCode and double.
   */
  StatusObject<Double> getCostBasisOfFlexiblePortfolioForDate(
          User user, FlexiblePortfolio portfolio, String date);

  /**
   * Method to calculate portfolio performance.
   *
   * @param user      an object of type user.
   * @param portfolio an object of type portfolio.
   * @param startDate start date string.
   * @param endDate   end date string.
   * @return returns StatusObject containing the statusMessage,
   *                 statusCode and the string.
   */
  StatusObject<String> performanceOfPortfolioOverTime(User user, Portfolio portfolio,
                                                      String startDate, String endDate);
}
