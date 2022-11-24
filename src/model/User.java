package model;

import java.util.List;

/**
 * This interface represents a user that would create one or more portfolios
 * with shares of one or more stock.
 * The interface contains all the methods that can be performed on a User.
 */

public interface User {

  double getCommission();

  void setCommission(double commission) throws InterruptedException;

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

  int getNextFlexiblePortfolioId();

  /**
   * Processes a portfolio object and adds it to the list of portfolios.
   *
   * @param portfolio an object of type portfolio.
   */

  void addToListOfRigidPortfolios(RigidPortfolio portfolio);

  void addToListOfFlexiblePortfolios(FlexiblePortfolio portfolio);

  /**
   * Retrieves the list of portfolios.
   *
   * @return returns list of portfolios.
   */

  List<RigidPortfolio> getListOfRigidPortfolios();


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

  FlexiblePortfolio createFlexiblePortfolio(String portfolioName, List<Stock> listOfStocks,
                                            String portfolioPath);

  FlexiblePortfolio getFlexiblePortfolio(int portfolioId);

  /**
   * Represents the User in a xml format as a string.
   */
  String xml();

}
