package controller.GUI;

import model.User;
import view.GUI.JView;

/**
 * This is a controller interface for Value GUI which displays the Calculate Value Menu.
 */
public interface JCalculateValueController {
  /**
   * Method to implement back button functionality.
   */
  void back();

  /**
   * Method to run the loop to calculate the value of a portfolio.
   */
  void calculatePortfolioValue(String date, String selectedPortfolio);

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
