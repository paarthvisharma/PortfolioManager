package view.gui;

/**
 * This class implements the JView interface and contains the methods to help set up
 * main view.
 */
public class JViewImpl implements JView {

  private final JCreateUserView createUserView;
  private final JPortfolioMenuView portfolioMenuView;
  private final JCreatePortfolioView createPortfolioView;
  private final JCalculateValueView calculateValueView;
  private final JCalculateCostBasisViewImpl calculateCostBasisView;
  private final JViewPortfolioView viewPortfolioView;
  private final JLoadPortfolioView loadPortfolioView;
  private final JTransactionView transactionView;
  private final JSetCommissionForUserView commissionView;

  private final JPerformanceView performanceView;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JViewImpl() {
    createUserView = new JCreateUserView();
    portfolioMenuView = new JPortfolioMenuView();
    createPortfolioView = new JCreatePortfolioView();
    calculateValueView = new JCalculateValueView();
    calculateCostBasisView = new JCalculateCostBasisViewImpl();
    viewPortfolioView = new JViewPortfolioView();
    loadPortfolioView = new JLoadPortfolioView();
    transactionView = new JTransactionView();
    commissionView = new JSetCommissionForUserView();
    performanceView = new JPerformanceView();
  }

  @Override
  public JCreateUserView getCreateUserView() {
    return createUserView;
  }

  @Override
  public JPortfolioMenuView getPortfolioMenuView() {
    return portfolioMenuView;
  }

  @Override
  public JCreatePortfolioView getCreatePortfolioView() {
    return createPortfolioView;
  }

  @Override
  public JCalculateValueView getCalculateValueView() {
    return calculateValueView;
  }

  @Override
  public JCalculateCostBasisViewImpl getCalculateCostBasisView() {
    return calculateCostBasisView;
  }

  @Override
  public JViewPortfolioView getViewPortfolioView() {
    return viewPortfolioView;
  }

  @Override
  public JLoadPortfolioView getLoadPortfolioView() {
    return loadPortfolioView;
  }

  @Override
  public JTransactionView getTransactionView() {
    return transactionView;
  }

  @Override
  public JSetCommissionForUserView getCommissionView() {
    return commissionView;
  }

  @Override
  public JPerformanceView getPerformanceView() {
    return performanceView;
  }
}
