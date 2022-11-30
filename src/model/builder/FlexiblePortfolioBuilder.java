package model.builder;

import java.util.List;

import model.FlexiblePortfolio;
import model.FlexiblePortfolioImpl;
import model.Stock;
import model.utils.dollarCostAveraging;

/**
 * A builder method implementation to create FlexiblePortfolio objects.
 * This is used when a flexible portfolio is being created from an XML file.
 */
public class FlexiblePortfolioBuilder {
  private int portfolioId = 0;
  private String portfolioName;
  private List<Stock> listOfStocks;

  private double commission;

  private String portfolioPath;
  private List<String> dcaPlansAsString;

  /**
   * Getter method to get the path to Transactions.csv of the portfolio.
   *
   * @return Path to portfolio transactions.
   */
  public String getPortfolioPath() {
    return portfolioPath;
  }

  /**
   * Setter method to set path to Portfolio in FlexiblePortfolio.
   *
   * @param portfolioPath Directory path to where Transactions.csv is stored.
   */
  public void setPortfolioPath(String portfolioPath) {
    this.portfolioPath = portfolioPath;
  }

  /**
   * Getter method to get the commission.
   *
   * @return Commission rate set on the portfolio.
   */
  public double getCommission() {
    return commission;
  }

  /**
   * Setter method to set commission in FlexiblePortfolio.
   *
   * @param commission Commission which is charged for each transaction performed in the
   *                   flexible portfolio.
   */
  public void setCommission(double commission) {
    this.commission = commission;
  }

  /**
   * Getter method to get portfolio ID.
   *
   * @return Portfolio ID.
   */
  public int getPortfolioId() {
    return portfolioId;
  }

  /**
   * Setter method to set ID of the portfolio in FlexiblePortfolio.
   *
   * @param portfolioId Unique ID that is given to the flexible portfolio.
   */
  public void setPortfolioId(int portfolioId) {
    this.portfolioId = portfolioId;
  }

  /**
   * Getter method to get portfolio name.
   *
   * @return Name of the portfolio.
   */
  public String getPortfolioName() {
    return portfolioName;
  }

  /**
   * Setter method to set portfolio name in FlexiblePortfolio.
   *
   * @param portfolioName Name of the portfolio.
   */
  public void setPortfolioName(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  /**
   * Getter method to get list of stocks.
   *
   * @return List of stocks present in the portfolio.
   */
  public List<Stock> getListOfStocks() {
    return listOfStocks;
  }

  /**
   * Setter method to set list of stocks in FlexiblePortfolio.
   *
   * @param listOfStocks List of stocks present in the portfolio.
   */
  public void setListOfStocks(List<Stock> listOfStocks) {
    this.listOfStocks = listOfStocks;
  }

  /**
   * Processes the portfolio information to build portfolio object.
   * The builder collects all the attributes to be set for the FlexiblePortfolio and initialises
   * the instance by calling The FlexiblePortfolios constructor.
   *
   * @return returns an object of type FlexiblePortfolio.
   * @throws IllegalArgumentException in case any of the arguments inserted is missing.
   */
  public FlexiblePortfolio build() {
    if (this.portfolioName != null & this.listOfStocks != null & this.portfolioId != 0) {
      FlexiblePortfolio portfolio = new FlexiblePortfolioImpl(this.portfolioName,
              this.portfolioId, this.listOfStocks, this.commission, this.portfolioPath);
      for (String dcaPlanStr : dcaPlansAsString) {
        portfolio.addDCAPlan(new dollarCostAveraging(dcaPlanStr));
      }
      return portfolio;
    } else {
      throw new IllegalArgumentException("One of the parameters required for"
              + " Portfolio have not been set");
    }
  }

  /**
   * Method to get dollar cost averaging as string.
   */
  public List<String> getDcaPlansAsString() {
    return dcaPlansAsString;
  }

  /**
   * Method to set the dollar cost averaging as string.
   */
  public void setDcaPlansAsString(List<String> dcaPlansAsString) {
    this.dcaPlansAsString = dcaPlansAsString;
  }
}
