package model;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import model.utils.XmlFormat;

/**
 * The Portfolio interface is simply a collection of stocks and represents a combination
 * of different company stocks.
 * The interface contains all the methods that can be performed on a portfolio.
 */
public interface Portfolio {
  /**
   * Retrieves the portfolioName.
   *
   * @return returns the portfolioName.
   */
  String getPortfolioName();

  /**
   * Retrieves the type of portfolio.
   *
   * @return returns the portfolioType.
   */
  String getType();

  /**
   * Retrieves the portfolioId.
   *
   * @return returns the portfolioId.
   */
  int getPortfolioId();


  /**
   * Retrieves the list of stocks.
   *
   * @return returns List of Stocks.
   */
  List<Stock> getListOfStock();

  /**
   * Processes the date on which information of stock is required.
   *
   * @return returns valuation of portfolio.
   */
  double getValueForDate(String dateOnInfo) throws FileNotFoundException, ParseException;

  /**
   * Represents the portfolio in a xml format as a string.
   *
   * @param tabs represents the spacing of xml file.
   */
  XmlFormat xml(int tabs);

}
