package controller.GUI;

import java.util.List;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;
import view.GUI.JViewPortfolioView;

public class JViewPortfolioControllerImpl implements JViewPortfolioController {

  private Model model;
  private JView view;
  private User user;
  private JViewPortfolioView jViewPortfolioView;
  private JPortfolioMenuView jPortfolioMenuView;

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
    this.view = jView;
    this.jPortfolioMenuView = view.getPortfolioMenuView();
    this.jViewPortfolioView = view.getViewPortfolioView();
    this.jViewPortfolioView.addFeatures(this);
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

//  @Override
//  public void resetStockList() {
//
//  }

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
              model.getCompositionOfFlexiblePortfolioAsList(user, particularPortfolio.returnedObject, date);
      if (portfolioDetails.statusCode < 0) {
        this.jViewPortfolioView.setFailureOutput(portfolioDetails.statusMessage);
        return;
      }
      for (List<String> stock: portfolioDetails.returnedObject) {
        this.jViewPortfolioView.addRowToTable(new String[]{stock.get(0), stock.get(1), stock.get(2), stock.get(3)});
      }
    } catch (Exception e) {
      this.jViewPortfolioView.setFailureOutput(e.getMessage());
    }
  }
}
