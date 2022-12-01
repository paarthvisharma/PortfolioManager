package controller.gui;

import java.util.List;
import java.util.Map;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for Portfolio GUI which displays the create portfolio menu.
 */
public interface JCreatePortfolioController {

  /**
   * Method to implement back button functionality.
   */

  void back();

  /**
   * Method to run the loop to create a portfolio.
   */
  void createPortfolio(String portfolioName, Map<String, String> dcaSetting,
                       List<List<String>> tableData);

  /**
   * Method to run the loop to add stock to a portfolio.
   */
  void addStock(String ticker, String quantity, String date);

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */
  void setView(JView jView);

  /**
   * Method that monitors the portfolio table for any changes.
   *
   * @param weightsColumn list.
   */
  void monitorTable(List<String> weightsColumn);

  /**
   * Method to set the user.
   *
   * @param user an object of type user.
   */
  void setUser(User user);

  /**
   * Method to reset stock list.
   */
  void resetStockList();

  /**
   * Method to add stocks with dollar cost averaging.
   *
   * @param ticker symbol of a company.
   */
  void addStockForDCA(String ticker);
}
