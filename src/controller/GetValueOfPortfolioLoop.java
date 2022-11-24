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
 * This class implements the ControllerCommand Interface and provides method to run the loop to
 * calculate the value of portfolio.
 */

public class GetValueOfPortfolioLoop implements ControllerCommand {

  User user;

  /**
   * This is the constructor for GetValueOfPortfolioLoop.
   *
   * @param user an object of type User.
   */
  public GetValueOfPortfolioLoop(User user) {
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
            this.rigidPortfolioValue(model, in, view);
            break;
          case "2":
            this.flexiblePortfolioValue(model, in, view);
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

  private void rigidPortfolioValue(Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayValueOfPortfolioPrompt();
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
          view.displayClear();
          view.displayGetDateForValuation();
          String date = takeLineInput(in).trim();
          view.displayClear();
          StatusObject<Double> value =
                  model.getValueOfPortfolioForDate(particularPortfolio.returnedObject, date);
          view.displaySuccessMessage("The value of the portfolio is $"
                  + value.returnedObject + "\n");
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

  private void flexiblePortfolioValue(
          Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayValueOfPortfolioPrompt();
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
          view.displayGetDateForValuation();
          String date = takeLineInput(in).trim();
          view.displayClear();
          StatusObject<Double> value =
                  model.getValueOfPortfolioForDate(particularPortfolio.returnedObject, date);
          if (value.statusCode < 0) {
            view.displayFailureMessage(value.statusMessage);
            view.askPromptToContinue();
            takeLineInput(in);
            view.displayClear();
            return;
          }
          view.displaySuccessMessage("The value of the portfolio is $"
                  + value.returnedObject + "\n");
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
