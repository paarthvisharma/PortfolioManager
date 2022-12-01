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
  private final JCalculateCostBasisView calculateCostBasisView;
  private final JViewPortfolioView viewPortfolioView;
  private final JLoadPortfolioView loadPortfolioView;
  private final JTransactionView transactionView;
  private final JSetCommissionForUserView commissionView;

  private final JPerformanceView performanceView;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JViewImpl() {
    createUserView = new JCreateUserViewImpl();
    portfolioMenuView = new JPortfolioMenuViewImpl();
    createPortfolioView = new JCreatePortfolioViewImpl();
    calculateValueView = new JCalculateValueViewImpl();
    calculateCostBasisView = new JCalculateCostBasisViewImpl();
    viewPortfolioView = new JViewPortfolioViewImpl();
    loadPortfolioView = new JLoadPortfolioViewImpl();
    transactionView = new JTransactionViewImpl();
    commissionView = new JSetCommissionForUserViewImpl();
    performanceView = new JPerformanceViewImpl();
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
  public JCalculateCostBasisView getCalculateCostBasisView() {
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
