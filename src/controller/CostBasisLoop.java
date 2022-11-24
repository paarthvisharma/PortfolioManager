package controller;

import java.util.Scanner;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.View;

import static controller.Utils.listFlexiblePortfolios;
import static controller.Utils.takeLineInput;

/**
 * This class implements the ControllerCommand Interface and provides method to run the loop to
 * calculate the cost basis of a flexible portfolio.
 */
public class CostBasisLoop implements ControllerCommand {

  User user;

  /**
   * This is the constructor for CostBasisLoop. An Instance of CostBasisLoop is created with
   * the help of this constructor.
   * @param user an object of type User.
   */
  public CostBasisLoop(User user) {
    this.user = user;
  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayPromptForPortfolioIdCostBasis();
        view.displayMessage(listFlexiblePortfolios(user, model));
        view.displayBForBackPrompt();
        String getUserInput = takeLineInput(in).trim();
        if (getUserInput.equalsIgnoreCase("B")) {
          view.displayClear();
          return;
        }
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
          view.displayClear();
          StatusObject<Double> costBasis = model.getCostBasisOfFlexiblePortfolioForDate(user,
                  particularPortfolio.returnedObject, date);
          if (costBasis.statusCode < 0) {
            view.displayFailureMessage(costBasis.statusMessage);
            view.askPromptToContinue();
            takeLineInput(in);
            view.displayClear();
            return;
          }
          view.displaySuccessMessage("The cost basis of the portfolio is $"
                  + costBasis.returnedObject + "\n");
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
