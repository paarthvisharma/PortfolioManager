package view.gui;

public interface JView {

  JCreateUserView getCreateUserView();
  JPortfolioMenuView getPortfolioMenuView();

  JCreatePortfolioView getCreatePortfolioView();

  JCalculateValueView getCalculateValueView();
  JCalculateCostBasisView getCalculateCostBasisView();

  JViewPortfolioView getViewPortfolioView();

  JLoadPortfolioView getLoadPortfolioView();

  JTransactionView getTransactionView();

  JSetCommissionForUserView getCommissionView();
}
