package view.GUI;

public class JViewImpl implements JView {

  private JCreateUserView createUserView;
  private JPortfolioMenuView portfolioMenuView;
  private JCreatePortfolioView createPortfolioView;
  private JCalculateValueView calculateValueView;
  private JCalculateCostBasisView calculateCostBasisView;
  private JViewPortfolioView viewPortfolioView;
  private JLoadPortfolioView loadPortfolioView;
  private JTransactionView transactionView;
  private JSetCommissionForUserView commissionView;


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
    return calculateCostBasisView ;
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
  public JSetCommissionForUserView getCommissionView(){
    return commissionView;
  }
}
