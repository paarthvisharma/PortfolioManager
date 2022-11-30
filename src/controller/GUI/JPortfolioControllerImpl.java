package controller.GUI;

import model.Model;
import model.User;
import view.GUI.JCalculateCostBasisView;
import view.GUI.JCalculateValueView;
import view.GUI.JCreatePortfolioView;
import view.GUI.JCreateUserView;
import view.GUI.JLoadPortfolioView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JSetCommissionForUserView;
import view.GUI.JTransactionView;
import view.GUI.JView;
import view.GUI.JViewPortfolioView;

import static controller.Utils.listFlexiblePortfolios;

public class JPortfolioControllerImpl implements JPortfolioController {

  private Model model;
  private JView view;
  private JCreateUserView createUserView;
  private JPortfolioMenuView portfolioMenuView;
  private JCreatePortfolioView createPortfolioView;
  private JCalculateValueView calculateValueView;
  private JCalculateCostBasisView calculateCostBasisView;
  private JViewPortfolioView viewPortfolioView;
  private JLoadPortfolioView loadPortfolioView;
  private JTransactionView transactionView;
  private JSetCommissionForUserView commissionView;
  private JCreatePortfolioController createPortfolioController;
  private JCalculateValueController calculateValueController;
  private JCalculateCostBasisController calculateCostBasisController;
  private JViewPortfolioController viewPortfolioController;
  private JLoadPortfolioController loadPortfolioController;
  private JTransactionController transactionController;
  private JSetCommissionController commissionController;
  private User user;

  public JPortfolioControllerImpl(Model model) {
    this.model = model;
    createPortfolioController = new JCreatePortfolioControllerImpl(model);
    calculateValueController = new JCalculateValueControllerImpl(model);
    calculateCostBasisController = new JCalculateCostBasisControllerImpl(model);
    viewPortfolioController = new JViewPortfolioControllerImpl(model);
    loadPortfolioController = new JLoadPortfolioControllerImpl(model);
    transactionController = new JTransactionControllerImpl(model);
    commissionController = new JSetCommissionControllerImpl(model);

  }

  @Override
  public void back() {
    this.portfolioMenuView.isVisible(false);
    this.createUserView.isVisible(true);
  }

  @Override
  public void setCommission() {
    commissionController.setUser(user);
    this.portfolioMenuView.isVisible(false);
    this.commissionView.isVisible(true);
    this.commissionView.setInitialMessage(user.getCommission());

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
    loadPortfolioController.setUser(user);
    this.portfolioMenuView.isVisible(false);
    this.loadPortfolioView.isVisible(true);
  }

  @Override
  public void viewPortfolio() {
    viewPortfolioController.setUser(user);
    this.viewPortfolioView.displayRadioButtonsForPortfolio(listFlexiblePortfolios(user, model));
    this.portfolioMenuView.isVisible(false);
    this.viewPortfolioView.clearUserInputs();
    this.viewPortfolioView.isVisible(true);
  }

  @Override
  public void performanceOfPortfolio() {

  }

  @Override
  public void transactInPortfolio() {
    transactionController.setUser(user);
    this.transactionView.displayRadioButtonsForPortfolio(listFlexiblePortfolios(user, model));
    this.portfolioMenuView.isVisible(false);
    this.transactionView.isVisible(true);
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
    this.loadPortfolioView = this.view.getLoadPortfolioView();
    this.viewPortfolioView = this.view.getViewPortfolioView();
    this.transactionView = this.view.getTransactionView();
    this.commissionView = this.view.getCommissionView();
    this.portfolioMenuView.addFeatures(this);
    this.createPortfolioController.setView(view);
    this.calculateValueController.setView(view);
    this.calculateCostBasisController.setView(view);
    this.viewPortfolioController.setView(view);
    this.loadPortfolioController.setView(view);
    this.transactionController.setView(view);
    this.commissionController.setView(view);
  }

}
