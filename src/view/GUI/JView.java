package view.GUI;

public interface JView {

  JCreateUserView getCreateUserView();
  JPortfolioMenuView getPortfolioMenuView();

  JCreatePortfolioView getCreatePortfolioView();

  JCalculateValueView getCalculateValueView();
  JCalculateCostBasisView getCalculateCostBasisView();

  JViewPortfolioView getViewPortfolioView();
}
