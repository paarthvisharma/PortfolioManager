package controller.gui;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.gui.JCalculateValueView;
import view.gui.JView;

import view.gui.JPortfolioMenuView;

/**
 * This class implements the JCalculateValueController interface and contains all the methods
 * required to display the Value Calculate Menu.
 */

public class JCalculateValueController {

  private final Model model;
  private User user;
  private JCalculateValueView jCalculateValueView;
  private JPortfolioMenuView jPortfolioMenuView;

  /**
   * A constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JCalculateValueController(Model model) {
    this.model = model;
  }

  /**
   * Method to implement back button functionality.
   */
  public void back() {
    this.jCalculateValueView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
  }

  /**
   * Method to run the loop to calculate the value of a portfolio.
   */
  public void calculatePortfolioValue(String date, String selectedPortfolio) {
    if (date.equals("")) {
      this.jCalculateValueView.setFailureOutput("Date Cannot be empty");
      return;
    }
    if (selectedPortfolio == null) {
      this.jCalculateValueView.setFailureOutput("One of the portfolios must be selected");
      return;
    }
    try {
      int portfolioId = Integer.parseInt(selectedPortfolio.split("\\s")[0]);
      StatusObject<FlexiblePortfolio> particularPortfolio =
              model.getParticularFlexiblePortfolio(user, portfolioId);
      if (particularPortfolio.statusCode < 1) {
        this.jCalculateValueView.setFailureOutput(particularPortfolio.statusMessage);
      } else {
        StatusObject<Double> value =
                model.getValueOfPortfolioForDate(particularPortfolio.returnedObject, date.trim());
        if (value.statusCode < 0) {
          this.jCalculateValueView.setFailureOutput(value.statusMessage);
          return;
        }
        this.jCalculateValueView.setSuccessOutput("The value of the portfolio is $"
                + value.returnedObject + "\n");
      }
    } catch (Exception e) {
      this.jCalculateValueView.setFailureOutput(e.getMessage());
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
    this.jCalculateValueView = jView.getCalculateValueView();
    this.jPortfolioMenuView = jView.getPortfolioMenuView();
    this.jCalculateValueView.addFeatures(this);
  }
}
