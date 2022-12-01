package controller.gui;

import java.io.IOException;

import model.Model;
import model.User;
import view.gui.JPortfolioMenuView;
import view.gui.JSetCommissionForUserView;
import view.gui.JView;


/**
 * Class which implements the JSetCommissionController Interface which helps display the commission
 * menu.
 */

public class JSetCommissionController {
  private final Model model;
  private User user;
  private JSetCommissionForUserView jSetCommissionForUserView;
  private JPortfolioMenuView jPortfolioMenuView;

  /**
   * Constructor to initialize the model.
   *
   * @param model an object of type Model.
   */

  public JSetCommissionController(Model model) {
    this.model = model;
  }

  /**
   * Method to implement back button functionality.
   */
  public void back() {
    this.jSetCommissionForUserView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
    this.jSetCommissionForUserView.clearAllUserInputs();
  }

  /**
   * Method to run the set commission loop.
   *
   * @param commission amount.
   */
  public void setCommission(String commission) {
    if (commission.equals("")) {
      this.jSetCommissionForUserView.setFailureOutput("Commission Cannot be empty");
      return;
    }
    try {
      double oldCommission = this.user.getCommission();
      if (Double.parseDouble(commission) < 0) {
        throw new NumberFormatException();
      }
      user.setCommission(Double.parseDouble(commission));
      model.updateUserFile(user);
      this.jSetCommissionForUserView.setSuccessOutput("For User ID " + user.getUserId() + " "
              + "Commission has been updated from $" + oldCommission
              + " to $" + user.getCommission());
    } catch (NumberFormatException e) {
      this.jSetCommissionForUserView.setFailureOutput("The commission should"
              + " be a positive number");
    } catch (IOException e) {
      this.jSetCommissionForUserView.setFailureOutput("Could not update the commission "
              + "rate as the User XML "
              + "file is missing or corrupted.");
    }
  }

  /**
   * Method to set the user.
   *
   * @param user an object of type user.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */
  public void setView(JView jView) {
    this.jSetCommissionForUserView = jView.getCommissionView();
    this.jPortfolioMenuView = jView.getPortfolioMenuView();
    this.jSetCommissionForUserView.addFeatures(this);

  }
}
