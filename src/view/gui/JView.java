package view.gui;

/**
 * This is a view interface for the main view. It contains the methods that are used to
 * display all the different views.
 */
public interface JView {
  /**
   * Method to get the CreateUser view.
   */
  JCreateUserView getCreateUserView();

  /**
   * Method to get the PortfolioMenuView view.
   */
  JPortfolioMenuView getPortfolioMenuView();

  /**
   * Method to get the CreatePortfolioView view.
   */
  JCreatePortfolioView getCreatePortfolioView();

  /**
   * Method to get the CalculateValueView view.
   */
  JCalculateValueView getCalculateValueView();

  /**
   * Method to get the CalculateValueView view.
   */
  JCalculateCostBasisViewImpl getCalculateCostBasisView();

  /**
   * Method to get the ViewPortfolioView view.
   */
  JViewPortfolioView getViewPortfolioView();

  /**
   * Method to get the LoadPortfolioView view.
   */
  JLoadPortfolioView getLoadPortfolioView();

  /**
   * Method to get the TransactionView view.
   */
  JTransactionView getTransactionView();

  /**
   * Method to get the CommissionView view.
   */
  JSetCommissionForUserView getCommissionView();

  /**
   * Method to get the PerformanceView view.
   */
  JPerformanceView getPerformanceView();
}
