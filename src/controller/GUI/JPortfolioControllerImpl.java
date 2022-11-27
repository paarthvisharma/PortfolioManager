package controller.GUI;

import model.Model;
import model.User;
import view.GUI.JCalculateCostBasisView;
import view.GUI.JCalculateCostBasisViewImpl;
import view.GUI.JCalculateValueView;
import view.GUI.JCreatePortfolioView;
import view.GUI.JCreateUserView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;

import static controller.Utils.listFlexiblePortfolios;

public class JPortfolioControllerImpl implements JPortfolioController {

  private Model model;
  private JView view;
  private JCreateUserView createUserView;
  private JPortfolioMenuView portfolioMenuView;
  private JCreatePortfolioView createPortfolioView;
  private JCalculateValueView calculateValueView;
  private JCalculateCostBasisView calculateCostBasisView;
  private JCreatePortfolioController createPortfolioController;
  private JCalculateValueController calculateValueController;
  private JCalculateCostBasisController calculateCostBasisController;
  private User user;

  public JPortfolioControllerImpl(Model model) {
    this.model = model;
    createPortfolioController = new JCreatePortfolioControllerImpl(model);
    calculateValueController = new JCalculateValueControllerImpl(model);
    calculateCostBasisController = new JCalculateCostBasisControllerImpl(model);

  }

  @Override
  public void back() {
    this.portfolioMenuView.isVisible(false);
    this.createUserView.isVisible(true);
  }

  @Override
  public void createPortfolio() {
    createPortfolioController.setUser(user);
    createPortfolioController.resetStockList();
    this.createPortfolioView.clearUserInputs();
    this.portfolioMenuView.isVisible(false);
    this.createPortfolioView.isVisible(true);
  }

  @Override
  public void valueOfPortfolio() {
    calculateValueController.setUser(user);
    this.calculateValueView.displayRadioButtonsForPortfolio(listFlexiblePortfolios(user, model));
    this.portfolioMenuView.isVisible(false);
    this.calculateValueView.isVisible(true);
  }

  @Override
  public void costBasisPortfolio() {
    calculateCostBasisController.setUser(user);
    this.calculateCostBasisView.displayRadioButtonsForPortfolio(listFlexiblePortfolios(user, model));
    this.portfolioMenuView.isVisible(false);
    this.calculateCostBasisView.isVisible(true);

  }

  @Override
  public void loadPortfolio() {

  }

  @Override
  public void viewPortfolio() {

  }

  @Override
  public void performanceOfPortfolio() {

  }

  @Override
  public void transactInPortfolio() {

  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.createUserView = this.view.getCreateUserView();
    this.portfolioMenuView = this.view.getPortfolioMenuView();
    this.createPortfolioView = this.view.getCreatePortfolioView();
    this.calculateValueView = this.view.getCalculateValueView();
    this.calculateCostBasisView = this.view.getCalculateCostBasisView();
    this.portfolioMenuView.addFeatures(this);
    this.createPortfolioController.setView(view);
    this.calculateValueController.setView(view);
    this.calculateCostBasisController.setView(view);
  }

}
