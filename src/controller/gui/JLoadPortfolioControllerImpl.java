package controller.gui;

import model.Model;
import model.Portfolio;
import model.User;
import model.utils.StatusObject;
import view.gui.JLoadPortfolioView;
import view.gui.JPortfolioMenuView;
import view.gui.JView;

/**
 * This class implements the JLoadPortfolioController interface for Load Portfolio GUI and contains
 * the methods which help displays the load portfolio menu.
 */

public class JLoadPortfolioControllerImpl implements JLoadPortfolioController {

  private Model model;
  private User user;
  private JLoadPortfolioView loadPortfolioView;
  private JPortfolioMenuView portfolioMenuView;

  /**
   * A constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
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
    this.loadPortfolioView = jView.getLoadPortfolioView();
    this.portfolioMenuView = jView.getPortfolioMenuView();
    this.loadPortfolioView.addFeatures(this);
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }
}
