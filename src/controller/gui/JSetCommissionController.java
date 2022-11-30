package controller.gui;

import model.User;
import view.gui.JView;

/**
 * This is a controller interface for SetCommission Menu GUI which displays the commission menu.
 */
public interface JSetCommissionController {

  /**
   * Method to implement back button functionality.
   */
  void back();

  /**
   * Method to run the set commission loop.
   *
   * @param commission
   */
  void setCommission(String commission);

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
