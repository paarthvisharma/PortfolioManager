package view.GUI;

public interface JView {

  JCreateUserView getCreateUserView();
  JPortfolioMenuView getPortfolioMenuView();

  JCreatePortfolioView getCreatePortfolioView();

  JCalculateValueViewImpl getCalculateValueView();
}
