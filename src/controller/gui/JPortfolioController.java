package controller.gui;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for Portfolio Menu GUI which displays the main portfolio menu.
 */
public interface JPortfolioController {
  /**
   * Method to implement back button functionality.
   */
  void back();

  /**
   * Method for set commission menu option.
   */
  void setCommission();

  /**
   * Method for create portfolio menu option.
   */
  void createPortfolio();

  /**
   * Method for value of portfolio menu option.
   */
  void valueOfPortfolio();

  /**
   * Method for calculating cost of portfolio menu option.
   */
  void costBasisPortfolio();

  /**
   * Method for load portfolio menu option.
   */
  void loadPortfolio();

  /**
   * Method for view portfolio menu option.
   */
  void viewPortfolio();

  /**
   * Method for performance of portfolio menu option.
   */
  void performanceOfPortfolio();

  /**
   * Method for transact in portfolio menu option.
   */
  void transactInPortfolio();

  /**
   * Method to set the user.
   *
   * @param user an object of type user.
   */
  void setUser(User user);

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */
  void setView(JView jView);
}
