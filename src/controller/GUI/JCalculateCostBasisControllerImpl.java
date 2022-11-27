package controller.GUI;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.GUI.JCalculateCostBasisView;

import view.GUI.JPortfolioMenuView;
import view.GUI.JView;


public class JCalculateCostBasisControllerImpl implements JCalculateCostBasisController{
  private Model model;
  private JView view;
  private User user;
  private JCalculateCostBasisView jCalculateCostBasisView;
  private JPortfolioMenuView jPortfolioMenuView;

  public JCalculateCostBasisControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.jCalculateCostBasisView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
  }

  @Override
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

  @Override
  public void setUser(User user) {
    this.user = user;

  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.jCalculateCostBasisView = this.view.getCalculateCostBasisView();
    this.jPortfolioMenuView = this.view.getPortfolioMenuView();
    this.jCalculateCostBasisView.addFeatures(this);

  }
}
