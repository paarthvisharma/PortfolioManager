package controller;

import java.util.Scanner;

import model.FlexiblePortfolio;
import model.Model;
import model.RigidPortfolio;
import model.User;
import model.utils.StatusObject;
import view.View;

import static controller.Utils.listFlexiblePortfolios;
import static controller.Utils.listRigidPortfolios;
import static controller.Utils.takeLineInput;

/**
 * This constructor implements the ControllerCommand class and runs the loop to view portfolio.
 */

public class ViewPortfolioLoop implements ControllerCommand {

  private User user;

  /**
   * This is a constructor for the ViewPortfolioLoop class. It uses the following parameters
   * to create the instance of the class.
   *
   * @param user object of type User.
   */
  public ViewPortfolioLoop(User user) {
    this.user = user;
  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayPromptWhichTypeOfPortfolio();
        String option = takeLineInput(in).trim();
        switch (option) {
          case "1":
            this.viewRigidPortfolio(model, in, view);
            break;
          case "2":
            viewFlexiblePortfolio(model, in, view);
            break;
          case "3":
            view.displayClear();
            return;
          default:
            view.displayInvalidInputFailureMessageAndAskForPrompt();
            takeLineInput(in);
            view.displayClear();
        }
      } catch (InterruptedException e) {
        throw new InterruptedException("Terminating program because of Interrupt");
      } catch (Exception e) {
        view.displayFailureMessage(e.getMessage());
      }
    }
  }


  private void viewRigidPortfolio(
          Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayPortfolioFromPortfolioIdPrompt();
        view.displayMessage(listRigidPortfolios(user, model));
        String getUserInput = takeLineInput(in).trim();
        int portfolioId = Integer.parseInt(getUserInput);
        StatusObject<RigidPortfolio> particularPortfolio =
                model.getParticularRigidPortfolio(user, portfolioId);
        if (particularPortfolio.statusCode < 1) {
          view.displayFailureMessage(particularPortfolio.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
        } else {
          view.displayMessage(particularPortfolio.returnedObject.toString());
          view.displayMessage("The portfolio " + getUserInput + " is printed.\n");
          view.askPromptToContinue();
          takeLineInput(in);
          view.displayClear();
          return;
        }
      } catch (InterruptedException e) {
        throw new InterruptedException("Terminating program because of Interrupt");
      } catch (Exception e) {
        view.displayFailureMessage(e.getMessage());
      }
    }
  }

  private void viewFlexiblePortfolio(
          Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayPortfolioFromPortfolioIdPrompt();
        view.displayMessage(listFlexiblePortfolios(user, model));
        String getUserInput = takeLineInput(in).trim();
        int portfolioId = Integer.parseInt(getUserInput);
        StatusObject<FlexiblePortfolio> particularPortfolio =
                model.getParticularFlexiblePortfolio(user, portfolioId);
        if (particularPortfolio.statusCode < 1) {
          view.displayFailureMessage(particularPortfolio.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
        } else {
          view.displayClear();
          view.displayPromptForDate();
          String date = takeLineInput(in).trim();
          StatusObject<String> status = model.viewCompositionOfFlexiblePortfolio(user,
                  particularPortfolio.returnedObject, date);
          if (status.statusCode < 1) {
            view.displayFailureMessage(status.statusMessage);
            view.askPromptToContinue();
            takeLineInput(in);
            return;
          }
          view.displayMessage(status.returnedObject);
          view.displayMessage("The portfolio " + getUserInput + " is printed.\n");
          view.askPromptToContinue();
          takeLineInput(in);
          view.displayClear();
          return;
        }
      } catch (InterruptedException e) {
        throw new InterruptedException("Terminating program because of Interrupt");
      } catch (Exception e) {
        view.displayFailureMessage(e.getMessage());
      }
    }
  }
}
