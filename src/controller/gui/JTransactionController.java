package controller.gui;

import java.util.List;
import java.util.Map;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for Transaction Menu GUI which displays the
 * main transaction menu.
 */

public interface JTransactionController {

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */

  void setView(JView jView);

  /**
   * Method to run the loop to buy stocks.
   *
   * @param ticker   symbol of a company.
   * @param quantity number of stocks.
   * @param date     date required.
   */
  void buyStock(String ticker, String quantity, String date);

  /**
   * Method to run the loop to sell stocks.
   *
   * @param ticker   symbol of a company.
   * @param quantity number of stocks.
   * @param date     date required.
   */
  void sellStock(String ticker, String quantity, String date);

  /**
   * Method to run the loop for dollar cost averaging.
   */
  void createDCAPlan(Map<String, String> dcaSetting, List<List<String>> tableData);

  /**
   * Method to run the loop for buy/sell.
   */
  void displayBuySellView();

  /**
   * Method to run the loop for dollar cost averaging.
   */
  void displayDCAView();

  /**
   * Method to set the user.
   *
   * @param user an object of type user.
   */
  void setUser(User user);

  /**
   * Method to get the portfolio transaction.
   */
  void selectPortfolioTransaction(String portfolioIdAndName);

  /**
   * Method to monitor table for transactions.
   */
  void monitorTable(List<String> weightsColumn);

  /**
   * Method to add stock in case of dollar cost averaging.
   */
  void addNewStockToDCATable(String ticker);

  /**
   * Method to implement back button functionality.
   */
  void back();
}
