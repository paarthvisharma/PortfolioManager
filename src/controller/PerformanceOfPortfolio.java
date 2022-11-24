package controller;

import java.util.Scanner;

import model.FlexiblePortfolio;
import model.Model;
import model.Portfolio;
import model.RigidPortfolio;
import model.User;
import model.utils.StatusObject;
import view.View;

import static controller.Utils.listFlexiblePortfolios;
import static controller.Utils.listRigidPortfolios;
import static controller.Utils.takeLineInput;

/**
 * This class implements the ControllerCommand Interface and provides method to run the loop to
 * calculate the performance of portfolio.
 */

public class PerformanceOfPortfolio implements ControllerCommand {

  private User user;

  /**
   * This is the constructor for PerformanceOfPortfolio.
   *
   * @param user an object of type User.
   */

  public PerformanceOfPortfolio(User user) {
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
            this.showPerformance(model, in, view, true);
            break;
          case "2":
            this.showPerformance(model, in, view, false);
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

  private void showPerformance(Model model, Scanner in, View view, boolean isRigid)
          throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayPortfolioFromPortfolioIdPrompt();
        if (isRigid) {
          view.displayMessage(listRigidPortfolios(user, model));
        } else {
          view.displayMessage(listFlexiblePortfolios(user, model));
        }
        String getUserInput = takeLineInput(in).trim();
        int portfolioId = Integer.parseInt(getUserInput);
        Portfolio returnedPortfolio;
        if (isRigid) {
          StatusObject<RigidPortfolio> particularPortfolio =
                  model.getParticularRigidPortfolio(user, portfolioId);
          if (particularPortfolio.statusCode < 1) {
            view.displayFailureMessage(particularPortfolio.statusMessage);
            view.askPromptToContinue();
            takeLineInput(in);
          }
          returnedPortfolio = particularPortfolio.returnedObject;
        } else {
          StatusObject<FlexiblePortfolio> particularPortfolio =
                  model.getParticularFlexiblePortfolio(user, portfolioId);
          if (particularPortfolio.statusCode < 1) {
            view.displayFailureMessage(particularPortfolio.statusMessage);
            view.askPromptToContinue();
            takeLineInput(in);
            return;
          }
          returnedPortfolio = particularPortfolio.returnedObject;
        }
        view.displayClear();
        view.displayPromptForStartDatePerformance();
        String startDate = takeLineInput(in).trim();
        view.displayClear();
        view.displayPromptForEndDatePerformance();
        String endDate = takeLineInput(in).trim();
        StatusObject<String> performance = model.performanceOfPortfolioOverTime(user,
                returnedPortfolio, startDate, endDate);
        if (performance.statusCode < 1) {
          view.displayFailureMessage(performance.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
        }
        view.displayMessage(performance.returnedObject);
        view.askPromptToContinue();
        takeLineInput(in);
        view.displayClear();
        return;
      } catch (InterruptedException e) {
        throw new InterruptedException("Terminating program because of Interrupt");
      }
    }
  }

}
