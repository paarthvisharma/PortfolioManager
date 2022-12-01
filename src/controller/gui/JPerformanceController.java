package controller.gui;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for performance GUI which displays the performance menu.
 */
public interface JPerformanceController {
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
   * Method to display select portfolio menu option.
   *
   * @param portfolioIdAndName portfolioID and name.
   */
  void selectPortfolio(String portfolioIdAndName);

  /**
   * Method to implement back button functionality.
   */

  void back();

  /**
   * Method to plot the graph.
   *
   * @param startDate start date string.
   * @param endDate   end date string.
   */
  void plotGraph(String startDate, String endDate);
}
