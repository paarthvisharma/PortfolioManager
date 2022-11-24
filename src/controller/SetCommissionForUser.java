package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Model;
import model.User;
import view.View;

import static controller.Utils.takeLineInput;

/**
 * This constructor implements the ControllerCommand class and runs the loop to set commission
 * for the User.
 */

public class SetCommissionForUser implements ControllerCommand {

  private final User user;

  /**
   * This is a constructor for the SetCommissionForUser class. It uses the following parameters
   * to create the instance of the class.
   *
   * @param user an object of type User.
   */
  public SetCommissionForUser(User user) {
    this.user = user;
  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.promptForCommissionChange(user.getCommission());
        double newCommission = Double.parseDouble(takeLineInput(in));
        if (newCommission < 0) {
          throw new NumberFormatException();
        }
        user.setCommission(newCommission);
        model.updateUserFile(user);
        view.displaySuccessMessage("Successfully set the commission for the UserId "
                + user.getUserId() + " to $" + user.getCommission());
        view.askPromptToContinue();
        takeLineInput(in);
        return;
      } catch (NumberFormatException e) {
        view.displayFailureMessage("The commission should be a positive number");
        view.askPromptToContinue();
        takeLineInput(in);
        return;
      } catch (IOException e) {
        view.displayFailureMessage("Could not update the commission rate as the User XML "
                + "file is missing or corrupted.");
        view.askPromptToContinue();
        takeLineInput(in);
      }
    }
  }
}
