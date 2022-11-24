package controller;

import java.util.Scanner;

import model.Model;
import model.User;
import model.utils.StatusObject;
import view.View;

import static controller.Utils.takeLineInput;

/**
 * This class implements the ControllerCommand Interface and provides the menu options used when
 * creating a portfolio.
 */

public class PortfolioLoop implements ControllerCommand {

  /**
   * This is the constructor for PortfolioLoop.
   */
  public PortfolioLoop() {
    // Portfolio Loop.

  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    User user = null;
    view.displayUserIdPrompt();
    String userId = takeLineInput(in).trim();
    StatusObject<User> selectedUser = model.getUser(userId);
    if (selectedUser.statusCode > 0) {
      user = selectedUser.returnedObject;
      view.displaySuccessMessage(selectedUser.statusMessage);
      view.askPromptToContinue();
      takeLineInput(in);
    } else {
      view.displayFailureMessage(selectedUser.statusMessage);
      view.askPromptToContinue();
      takeLineInput(in);
      return;
    }
    view.displayClear();
    ControllerCommand cmd = null;
    while (true) {
      try {
        view.displayPortfolioMenu();
        String option = takeLineInput(in);
        switch (option.trim()) {
          case "1":
            cmd = new SetCommissionForUser(user);
            break;
          case "2":
            cmd = new CreatePortfolio(user);
            break;
          case "3":
            cmd = new ViewPortfolioLoop(user);
            break;
          case "4":
            cmd = new GetValueOfPortfolioLoop(user);
            break;
          case "5":
            cmd = new CostBasisLoop(user);
            break;
          case "6":
            cmd = new PortfolioTransaction(user);
            break;
          case "7":
            cmd = new PerformanceOfPortfolio(user);
            break;
          case "8":
            view.displayClear();
            return;
          default:
            view.displayInvalidInputFailureMessageAndAskForPrompt();
            takeLineInput(in);
            view.displayClear();
        }
        if (cmd != null) {
          cmd.runLoop(model, in, view);
          cmd = null;
        }
      } catch (InterruptedException e) {
        throw new InterruptedException(e.getMessage());
      } catch (Exception e) {
        view.displayFailureMessage(e.getMessage());
      }
    }
  }
}
