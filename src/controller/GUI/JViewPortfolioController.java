package controller.GUI;

import model.User;
import view.GUI.JView;

/**
 * This is a controller interface for View Portfolio GUI which displays the
 * menu to view portfolio.
 */
public interface JViewPortfolioController {

  /**
   * Method to implement back button functionality.
   */

  void back();

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

  /**
   * Method to run the loop to view portfolio.
   *
   * @param date              required date.
   * @param selectedPortfolio required portfolio.
   */
  void viewPortfolio(String date, String selectedPortfolio);
}
