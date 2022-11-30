package controller.gui;

import java.util.List;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.gui.JCalculateCostBasisView;
import view.gui.JCalculateValueView;
import view.gui.JCreatePortfolioView;
import view.gui.JCreateUserView;
import view.gui.JLoadPortfolioView;
import view.gui.JPerformanceView;
import view.gui.JPortfolioMenuView;
import view.gui.JSetCommissionForUserView;
import view.gui.JTransactionView;
import view.gui.JView;
import view.gui.JViewPortfolioView;

import static controller.Utils.getPresentDate;
import static controller.Utils.listFlexiblePortfolios;

/**
 * This class implements the JPortfolioController interface Load Portfolio GUI and contains
 * the methods which help displays the portfolio menu.
 */
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
  private JPerformanceView performanceView;
  private JCreatePortfolioController createPortfolioController;
  private JCalculateValueController calculateValueController;
  private JCalculateCostBasisController calculateCostBasisController;
  private JViewPortfolioController viewPortfolioController;
  private JLoadPortfolioController loadPortfolioController;
  private JTransactionController transactionController;
  private JSetCommissionController commissionController;
  private  JPerformanceController performanceController;
  private User user;

  /**
   * Constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JPortfolioControllerImpl(Model model) {
    this.model = model;
    createPortfolioController = new JCreatePortfolioControllerImpl(model);
    calculateValueController = new JCalculateValueControllerImpl(model);
    calculateCostBasisController = new JCalculateCostBasisControllerImpl(model);
    viewPortfolioController = new JViewPortfolioControllerImpl(model);
    loadPortfolioController = new JLoadPortfolioControllerImpl(model);
    transactionController = new JTransactionControllerImpl(model);
    commissionController = new JSetCommissionControllerImpl(model);
    performanceController = new JPerformanceControllerImpl(model);

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
    this.calculateCostBasisView.displayRadioButtonsForPortfolio(listFlexiblePortfolios(user,
            model));
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
    performanceController.setUser(user);
    this.performanceView.displayRadioButtonsForPortfolio(listFlexiblePortfolios(user, model));
    this.portfolioMenuView.isVisible(false);
    this.performanceView.clearUserInputs();
    this.performanceView.isVisible(true);
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
    this.performanceView = this.view.getPerformanceView();
    this.portfolioMenuView.addFeatures(this);
    this.createPortfolioController.setView(view);
    this.calculateValueController.setView(view);
    this.calculateCostBasisController.setView(view);
    this.viewPortfolioController.setView(view);
    this.loadPortfolioController.setView(view);
    this.transactionController.setView(view);
    this.commissionController.setView(view);
    this.performanceController.setView(view);
  }

}
