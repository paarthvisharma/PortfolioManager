package model;

import model.utils.XmlFormat;

/**
 * The Stock interface represents a company stock.
 * A stock is simply a part of ownership in a company.
 * The interface contains all the methods that can be performed on a stock.
 */
public interface Stock {

  /**
   * Retrieves the date of purchase of stock.
   *
   * @return returns date as String.
   */
  String getDate();


  /**
   * Retrieves the date on which the stock was listed.
   *
   * @return returns date as String.
   */
  String getListingDate();

  /**
   * Retrieves the ticker symbol of a stock.
   *
   * @return returns ticker as String.
   */
  String getTicker();

  /**
   * Retrieves the name of a stock.
   *
   * @return returns name.
   */
  String getStockName();

  /**
   * Retrieves the quantity of a stock.
   *
   * @return returns quantity.
   */
  double getStockQuantity();

  /**
   * Sets the stock quantity.
   *
   * @param stockQuantity amount of shares.
   */
  void setStockQuantity(double stockQuantity);

  /**
   * Method to calculate the total value of a portfolio on a certain date.
   *
   * @param date on which the value needs to be calculated.
   * @return returns the total value.
   */

  double getValue(String date);

  /**
   * Retrieves the Stock details as a String.
   *
   * @return returns the Stock details .
   */
  String stockAsString();

  /**
   * Represents the Stock in a xml format as a string.
   *
   * @param tabs represents the spacing of xml file.
   */

  XmlFormat xml(int tabs, boolean name, boolean quantity, boolean date);

}
