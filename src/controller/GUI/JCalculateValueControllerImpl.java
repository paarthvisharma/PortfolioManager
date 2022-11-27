package controller.GUI;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.GUI.JCalculateValueView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;


public class JCalculateValueControllerImpl implements JCalculateValueController {

  private Model model;
  private JView view;
  private User user;
  private JCalculateValueView jCalculateValueView;
  private JPortfolioMenuView jPortfolioMenuView;

  public JCalculateValueControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.jCalculateValueView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
  }

  @Override
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

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.jCalculateValueView = this.view.getCalculateValueView();
    this.jPortfolioMenuView = this.view.getPortfolioMenuView();
    this.jCalculateValueView.addFeatures(this);
  }
}
