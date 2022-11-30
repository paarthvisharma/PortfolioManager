package controller.gui;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for Load Portfolio GUI which displays the load portfolio menu.
 */
public interface JLoadPortfolioController {
  /**
   * Method to implement back button functionality.
   */

  void back();

  /**
   * Method to load a portfolio.
   *
   * @param xmlPath path of the portfolio file.
   */
  void loadPortfolio(String xmlPath);

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */

  void setView(JView jView);

  /**
   * Method to set the user.
   *
   * @param user an object of type user.
   */
  void setUser(User user);
}
