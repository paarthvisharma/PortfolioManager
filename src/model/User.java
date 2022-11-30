package model;

import java.util.List;

/**
 * This interface represents a user that would create one or more portfolios
 * with shares of one or more stock.
 * The interface contains all the methods that can be performed on a User.
 */

public interface User {

  /**
   * Method to get commission.
   *
   * @return the commission value.
   */
  double getCommission();

  /**
   * Sets the commission value.
   *
   * @param commission amount.
   * @throws IllegalArgumentException in case negative commission is entered.
   */
  void setCommission(double commission) throws IllegalArgumentException;

  /**
   * Retrieves the userId associated with a particular user.
   *
   * @return returns userId.
   */
  int getUserId();

  /**
   * Retrieves the firstName of a particular user as String.
   *
   * @return returns the firstName.
   */

  String getFirstName();

  /**
   * Retrieves the lastName of a particular user as String.
   *
   * @return returns the lastName.
   */

  String getLastName();

  /**
   * Method to get the portfolio ID of next flexible portfolio.
   *
   * @return portfolioID.
   */
  int getNextFlexiblePortfolioId();

  /**
   * Processes a portfolio object and adds it to the list of portfolios.
   *
   * @param portfolio an object of type rigid portfolio.
   */

  void addToListOfRigidPortfolios(RigidPortfolio portfolio);

  /**
   * Processes a portfolio object and adds it to the list of portfolios.
   *
   * @param portfolio an object of type flexible portfolio.
   */
  void addToListOfFlexiblePortfolios(FlexiblePortfolio portfolio);

  /**
   * Retrieves the list of rigid portfolios.
   *
   * @return returns list of rigid portfolios.
   */

  List<RigidPortfolio> getListOfRigidPortfolios();

  /**
   * Retrieves the list of flexible portfolios.
   *
   * @return returns list of flexible portfolios.
   */
  List<FlexiblePortfolio> getListOfFlexiblePortfolios();

  /**
   * Processes the Portfolio name and listOfStocks to create Portfolio object.
   *
   * @return returns an object of type portfolio.
   */
  RigidPortfolio createRigidPortfolio(String portfolioName, List<Stock> listOfStocks);

  /**
   * Processes the Portfolio ID.
   *
   * @return returns an object of type portfolio.
   */
  RigidPortfolio getRigidPortfolio(int portfolioId);

  /**
   * Method to create flexible portfolio.
   *
   * @param portfolioName name of portfolio.
   * @param listOfStocks  stick list.
   * @param portfolioPath path of file.
   * @return an object of type flexible portfolio.
   */
  FlexiblePortfolio createFlexiblePortfolio(String portfolioName, List<Stock> listOfStocks,
                                            String portfolioPath);

  /**
   * Method to retrieve a flexible portfolio.
   *
   * @param portfolioId portfolio ID.
   * @return an object of type flexible portfolio.
   */
  FlexiblePortfolio getFlexiblePortfolio(int portfolioId);

  /**
   * Represents the User in a xml format as a string.
   */
  String xml();

}
