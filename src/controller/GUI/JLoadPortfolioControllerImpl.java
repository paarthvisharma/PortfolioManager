package controller.GUI;

import model.Model;
import model.Portfolio;
import model.User;
import model.utils.StatusObject;
import view.GUI.JLoadPortfolioView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;

public class JLoadPortfolioControllerImpl implements JLoadPortfolioController {

  private Model model;
  private User user;
  private JView view;
  private JLoadPortfolioView loadPortfolioView;
  private JPortfolioMenuView portfolioMenuView;

  public JLoadPortfolioControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.loadPortfolioView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }

  @Override
  public void loadPortfolio(String xmlPath) {
    if (xmlPath.trim().equals("")) {
      this.loadPortfolioView.setFailureOutput("Choose a Portfolio file to load");
      return;
    }
    try {
      StatusObject<Portfolio> status = this.model.createPortfolioFromXML(user, xmlPath);
      if (status.statusCode < 0) {
        this.loadPortfolioView.setFailureOutput(status.statusMessage);
      } else {
        this.loadPortfolioView.setSuccessOutput(status.statusMessage);
      }
    } catch (Exception e) {
      this.loadPortfolioView.setFailureOutput(e.getMessage());
    }
  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.loadPortfolioView = this.view.getLoadPortfolioView();
    this.portfolioMenuView = this.view.getPortfolioMenuView();
    this.loadPortfolioView.addFeatures(this);
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }
}
