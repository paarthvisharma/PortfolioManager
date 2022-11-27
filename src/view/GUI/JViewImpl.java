package view.GUI;

public class JViewImpl implements JView {

  private JCreateUserView createUserView;
  private JPortfolioMenuView portfolioMenuView;
  private JCreatePortfolioView createPortfolioView;
  private JCalculateValueViewImpl calculateValueView;


  public JViewImpl() {
    createUserView = new JCreateUserViewImpl();
    portfolioMenuView = new JPortfolioMenuViewImpl();
    createPortfolioView = new JCreatePortfolioViewImpl();
    calculateValueView = new JCalculateValueViewImpl();
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
}
