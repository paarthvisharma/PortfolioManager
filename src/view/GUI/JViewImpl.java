package view.GUI;

public class JViewImpl implements JView {

  private JCreateUserView createUserView;
  private JPortfolioMenuView portfolioMenuView;
  private JCreatePortfolioView createPortfolioView;
  private JCalculateValueViewImpl calculateValueView;
  private JCalculateCostBasisViewImpl calculateCostBasisView;


  public JViewImpl() {
    createUserView = new JCreateUserViewImpl();
    portfolioMenuView = new JPortfolioMenuViewImpl();
    createPortfolioView = new JCreatePortfolioViewImpl();
    calculateValueView = new JCalculateValueViewImpl();
    calculateCostBasisView = new JCalculateCostBasisViewImpl();
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
  public JCalculateValueViewImpl getCalculateValueView() {
    return calculateValueView;
  }

  @Override
  public JCalculateCostBasisViewImpl getCalculateCostBasisView() {
    return calculateCostBasisView ;
  }
}
