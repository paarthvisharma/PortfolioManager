package controller.gui;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.gui.JCalculateValueView;
import view.gui.JPortfolioMenuView;
import view.gui.JView;

/**
 * This class implements the JCalculateValueController interface and contains all the methods
 * required to display the Value Calculate Menu.
 */

public class JCalculateValueControllerImpl implements JCalculateValueController {

  private final Model model;
  private User user;
  private JCalculateValueView jCalculateValueView;
  private JPortfolioMenuView jPortfolioMenuView;

  /**
   * A constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
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
    this.jCalculateValueView = jView.getCalculateValueView();
    this.jPortfolioMenuView = jView.getPortfolioMenuView();
    this.jCalculateValueView.addFeatures(this);
  }
}
