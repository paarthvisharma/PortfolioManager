package controller.gui;

import model.Model;
import model.User;
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
import static controller.Utils.listFlexiblePortfolios;

/**
 * This class implements the JPortfolioController interface Load Portfolio GUI and contains
 * the methods which help displays the portfolio menu.
 */
public class JPortfolioControllerImpl implements JPortfolioController {

  private final Model model;
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
  private final JCreatePortfolioController createPortfolioController;
  private final JCalculateValueController calculateValueController;
  private final JCalculateCostBasisController calculateCostBasisController;
  private final JViewPortfolioController viewPortfolioController;
  private final JLoadPortfolioController loadPortfolioController;
  private final JTransactionController transactionController;
  private final JSetCommissionController commissionController;
  private final JPerformanceController performanceController;
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
    this.createUserView = jView.getCreateUserView();
    this.portfolioMenuView = jView.getPortfolioMenuView();
    this.createPortfolioView = jView.getCreatePortfolioView();
    this.calculateValueView = jView.getCalculateValueView();
    this.calculateCostBasisView = jView.getCalculateCostBasisView();
    this.loadPortfolioView = jView.getLoadPortfolioView();
    this.viewPortfolioView = jView.getViewPortfolioView();
    this.transactionView = jView.getTransactionView();
    this.commissionView = jView.getCommissionView();
    this.performanceView = jView.getPerformanceView();
    this.portfolioMenuView.addFeatures(this);
    this.createPortfolioController.setView(jView);
    this.calculateValueController.setView(jView);
    this.calculateCostBasisController.setView(jView);
    this.viewPortfolioController.setView(jView);
    this.loadPortfolioController.setView(jView);
    this.transactionController.setView(jView);
    this.commissionController.setView(jView);
    this.performanceController.setView(jView);
  }

}
