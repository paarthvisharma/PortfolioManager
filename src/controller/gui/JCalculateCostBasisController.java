package controller.gui;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for Cost Basis GUI which displays the Cost Basis Menu.
 */

public interface JCalculateCostBasisController {
  /**
   * Method to implement back button functionality.
   */
  void back();

  /**
   * Method to run the loop to calculate the cost basis of a portfolio.
   */
  void calculatePortfolioCostBasis(String date, String selectedPortfolio);

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
