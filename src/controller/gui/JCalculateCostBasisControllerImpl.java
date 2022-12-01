package controller.gui;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;

import view.gui.JCalculateCostBasisViewImpl;
import view.gui.JView;

import view.gui.JPortfolioMenuView;

/**
 * This class implements the JCalculateCostBasisController interface and contains all the methods
 * required to display the Cost Basis Menu.
 */
public class JCalculateCostBasisControllerImpl {
  private final Model model;
  private User user;
  private JCalculateCostBasisViewImpl jCalculateCostBasisView;
  private JPortfolioMenuView jPortfolioMenuView;

  /**
   * A constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JCalculateCostBasisControllerImpl(Model model) {
    this.model = model;
  }

  /**
   * Method to implement back button functionality.
   */
  public void back() {
    this.jCalculateCostBasisView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
  }

  /**
   * Method to run the loop to calculate the cost basis of a portfolio.
   */
  public void calculatePortfolioCostBasis(String date, String selectedPortfolio) {
    if (date.equals("")) {
      this.jCalculateCostBasisView.setFailureOutput("Date Cannot be empty");
      return;
    }
    if (selectedPortfolio == null) {
      this.jCalculateCostBasisView.setFailureOutput("One of the portfolios must be selected");
      return;
    }
    try {
      int portfolioId = Integer.parseInt(selectedPortfolio.split("\\s")[0]);
      StatusObject<FlexiblePortfolio> particularPortfolio =
              model.getParticularFlexiblePortfolio(user, portfolioId);
      if (particularPortfolio.statusCode < 1) {
        this.jCalculateCostBasisView.setFailureOutput(particularPortfolio.statusMessage);
      } else {
        StatusObject<Double> costBasis = model.getCostBasisOfFlexiblePortfolioForDate(user,
                particularPortfolio.returnedObject, date);
        if (costBasis.statusCode < 0) {
          this.jCalculateCostBasisView.setFailureOutput(costBasis.statusMessage);
          return;
        }
        this.jCalculateCostBasisView.setSuccessOutput("The cost basis of the portfolio is $"
                + costBasis.returnedObject + "\n");
      }
    } catch (NumberFormatException e) {
      throw new RuntimeException(e);
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
    this.jCalculateCostBasisView = jView.getCalculateCostBasisView();
    this.jPortfolioMenuView = jView.getPortfolioMenuView();
    this.jCalculateCostBasisView.addFeatures(this);

  }
}
