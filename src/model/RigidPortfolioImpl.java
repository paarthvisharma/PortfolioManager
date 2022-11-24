package model;

import java.util.List;

import model.utils.XmlFormat;

import static model.utils.Utils.portfolioToString;

/**
 * This class is an implementation of the RigidPortfolio Interface.
 * The methods associated with the class help perform operations on the RigidPortfolio objects
 * such as getting portfolio id, name, listOfStocks.
 */

public class RigidPortfolioImpl implements RigidPortfolio {
  private final String portfolioName;
  private final int portfolioId;
  private final List<Stock> listOfStocks;

  private final String type = "rigid";

  /**
   * This is the constructor for PortfolioImpl. An Instance of PortfolioImpl is created
   * with the help of this constructor.
   * It creates a PortfolioImpl by taking the following parameters.
   *
   * @param portfolioName name of the portfolio to be created.
   * @param portfolioId   id assigned to a particular portfolio.
   * @param listOfStocks  list of stocks to be added in a portfolio.
   */

  public RigidPortfolioImpl(String portfolioName, int portfolioId, List<Stock> listOfStocks) {
    this.portfolioName = portfolioName;
    this.portfolioId = portfolioId;
    this.listOfStocks = listOfStocks;
  }

  @Override
  public int getPortfolioId() {
    return this.portfolioId;
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
  public List<Stock> getListOfStock() {
    return this.listOfStocks;
  }

  @Override
  public String toString() {
    return portfolioToString(this, true, true, true, false);
  }

  @Override
  public XmlFormat xml(int tabs) {
    int lTabs = tabs;
    StringBuilder toReturn = new StringBuilder();
    toReturn.append("\t".repeat(lTabs)).append("<rigidPortfolio portfolioId=\"")
            .append(this.getPortfolioId()).append("\">\n");
    lTabs += 1;
    toReturn.append("\t".repeat(lTabs)).append("<portfolioName>")
            .append(this.getPortfolioName()).append("</portfolioName>\n");
    toReturn.append("\t".repeat(lTabs)).append("<stocks>\n");
    lTabs += 1;
    for (Stock stock : this.getListOfStock()) {
      XmlFormat stockXml = stock.xml(lTabs, true, true, false);
      toReturn.append(stockXml.xml);
      lTabs = stockXml.tabs;
    }
    lTabs -= 1;
    toReturn.append("\t".repeat(lTabs)).append("</stocks>\n");
    lTabs -= 1;
    toReturn.append("\t".repeat(lTabs)).append("</rigidPortfolio>\n");
    return new XmlFormat(toReturn.toString(), lTabs);
  }

  @Override
  public double getValueForDate(String dateOnInfo) {
    int totalValue = 0;
    for (Stock stock : this.listOfStocks) {
      totalValue += stock.getValue(dateOnInfo);
    }
    return totalValue;
  }
}
