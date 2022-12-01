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

public class JLoadPortfolioController {

  private final Model model;
  private User user;
  private JLoadPortfolioView loadPortfolioView;
  private JPortfolioMenuView portfolioMenuView;

  /**
   * A constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JLoadPortfolioController(Model model) {
    this.model = model;
  }

  /**
   * Method to implement back button functionality.
   */
  public void back() {
    this.loadPortfolioView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }

  /**
   * Method to load a portfolio.
   *
   * @param xmlPath path of the portfolio file.
   */
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

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */
  public void setView(JView jView) {
    this.loadPortfolioView = jView.getLoadPortfolioView();
    this.portfolioMenuView = jView.getPortfolioMenuView();
    this.loadPortfolioView.addFeatures(this);
  }

  /**
   * Method to set the user.
   *
   * @param user an object of type user.
   */
  public void setUser(User user) {
    this.user = user;
  }
}
