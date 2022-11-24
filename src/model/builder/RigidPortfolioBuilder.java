package model.builder;

import java.util.List;

import model.RigidPortfolio;
import model.RigidPortfolioImpl;
import model.Stock;

/**
 * A builder method implementation to create RigidPortfolio objects.
 * This is used when a rigid portfolio is being created from an XML file.
 */
public class RigidPortfolioBuilder {

  private int portfolioId = 0;
  private String portfolioName;
  private List<Stock> listOfStocks;

  /**
   * Getter method to fetch the Portfolio ID of a portfolio.
   *
   * @return Portfolio ID of the portfolio.
   */
  public int getPortfolioId() {
    return portfolioId;
  }

  /**
   * Setter used to set the PortfolioID attribute.
   *
   * @param portfolioId Portfolio ID of the portfolio.
   */
  public void setPortfolioId(int portfolioId) {
    this.portfolioId = portfolioId;
  }

  /**
   * Getter method to fetch the name of the portfolio.
   *
   * @return Name of the portfolio.
   */
  public String getPortfolioName() {
    return portfolioName;
  }

  /**
   * Setter used to set the Portfolio Name attribute.
   *
   * @param portfolioName Portfolio name of the portfolio.
   */
  public void setPortfolioName(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  /**
   * Getter method to fetch list of stocks present in the portfolio.
   *
   * @return list of stocks.
   */
  public List<Stock> getListOfStocks() {
    return listOfStocks;
  }

  /**
   * Setter used to set the List of Stocks contained in the portfolio.
   *
   * @param listOfStocks list of stocks.
   */
  public void setListOfStocks(List<Stock> listOfStocks) {
    this.listOfStocks = listOfStocks;
  }

  /**
   * Processes the portfolio information to build portfolio object.
   * The builder collects all the attributes to be set for the RigidPortfolio and initialises
   * the instance by calling The RigidPortfolios constructor.
   *
   * @return returns an object of type RigidPortfolio.
   * @throws IllegalArgumentException in case any of the arguments inserted is missing.
   */
  public RigidPortfolio build() {
    if (this.portfolioName != null & this.listOfStocks != null & this.portfolioId != 0) {
      return new RigidPortfolioImpl(this.portfolioName, this.portfolioId, this.listOfStocks);
    } else {
      throw new IllegalArgumentException("One of the parameters required for"
              + " Portfolio have not been set");
    }
  }
}
