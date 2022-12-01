package controller.gui;

import java.util.List;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.gui.JPortfolioMenuView;
import view.gui.JView;
import view.gui.JViewPortfolioView;

/**
 * This class implements the JViewPortfolioController interface for Portfolio GUI which and
 * contains the methods which help display the portfolio menu.
 */
public class JViewPortfolioControllerImpl implements JViewPortfolioController {

  private final Model model;
  private User user;
  private JViewPortfolioView jViewPortfolioView;
  private JPortfolioMenuView jPortfolioMenuView;

  /**
   * Constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JViewPortfolioControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.jViewPortfolioView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
  }

  @Override
  public void setView(JView jView) {
    this.jPortfolioMenuView = jView.getPortfolioMenuView();
    this.jViewPortfolioView = jView.getViewPortfolioView();
    this.jViewPortfolioView.addFeatures(this);
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void viewPortfolio(String date, String selectedPortfolio) {
    if (date.equals("")) {
      this.jViewPortfolioView.setFailureOutput("Date Cannot be empty");
      return;
    }
    if (selectedPortfolio == null) {
      this.jViewPortfolioView.setFailureOutput("One of the portfolios must be selected");
      return;
    }
    try {
      this.jViewPortfolioView.clearTable();
      int portfolioId = Integer.parseInt(selectedPortfolio.split("\\s")[0]);
      StatusObject<FlexiblePortfolio> particularPortfolio =
              model.getParticularFlexiblePortfolio(user, portfolioId);
      if (particularPortfolio.statusCode < 0) {
        this.jViewPortfolioView.setFailureOutput(particularPortfolio.statusMessage);
        return;
      }
      StatusObject<List<List<String>>> portfolioDetails =
              model.getCompositionOfFlexiblePortfolioAsList(user,
                      particularPortfolio.returnedObject, date);
      if (portfolioDetails.statusCode < 0) {
        this.jViewPortfolioView.setFailureOutput(portfolioDetails.statusMessage);
        return;
      }
      for (List<String> stock : portfolioDetails.returnedObject) {
        this.jViewPortfolioView.addRowToTable(new String[]{stock.get(0), stock.get(1),
                stock.get(2), stock.get(3)});
      }
    } catch (Exception e) {
      this.jViewPortfolioView.setFailureOutput(e.getMessage());
    }
  }
}
