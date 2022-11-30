package controller.GUI;

import java.io.IOException;

import model.Model;
import model.User;
import view.GUI.JPortfolioMenuView;
import view.GUI.JSetCommissionForUserView;
import view.GUI.JView;


/**
 * Class which implements the JSetCommissionController Interface which helps display the commission
 * menu.
 */

public class JSetCommissionControllerImpl implements JSetCommissionController {
  private Model model;
  private JView view;
  private User user;
  private JSetCommissionForUserView jSetCommissionForUserView;
  private JPortfolioMenuView jPortfolioMenuView;

  /**
   * Constructor to initialize the model.
   *
   * @param model an object of type Model.
   */

  public JSetCommissionControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.jSetCommissionForUserView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
    this.jSetCommissionForUserView.clearAllUserInputs();
  }

  @Override
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

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.jSetCommissionForUserView = this.view.getCommissionView();
    this.jPortfolioMenuView = this.view.getPortfolioMenuView();
    this.jSetCommissionForUserView.addFeatures(this);

  }
}
