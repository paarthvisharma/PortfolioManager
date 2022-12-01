package view.gui;

import controller.gui.JTransactionController;

/**
 * This is a view interface for create portfolio menu.
 */
public interface JTransactionView {
  /**
   * Method to set DCA pane.
   */
  void enableAddPlanDCA(boolean state);

  /**
   * Method to link the view to controller.
   *
   * @param jTransactionController controller for transaction menu.
   */
  void addFeatures(JTransactionController jTransactionController);

  /**
   * Method to clear the user inputs.
   */
  void clearUserInputs();

  /**
   * Method to clear the table.
   */
  void clearTable();

  /**
   * Method to set the visibility of a view.
   */
  void isVisible(boolean state);

  /**
   * Method to set dollar cost average pane visible.
   */
  void dcaPanelIsVisible();

  /**
   * Method to set buy and sell pane visible.
   */
  void buySellPanelIsVisible();

  /**
   * Method to set the log output.
   *
   * @param message required message.
   */
  void setLogOutput(String message);

  /**
   * Method to set the success output.
   *
   * @param message required message.
   */
  void setSuccessOutput(String message);

  /**
   * Method to set the failure output.
   *
   * @param message required message.
   */
  void setFailureOutput(String message);

  /**
   * Method to display radio buttons.
   *
   * @param portfolios required portfolios.
   */
  void displayRadioButtonsForPortfolio(String portfolios);

  /**
   * Method to add row to dollar cost average table.
   *
   * @param row value.
   */
  void addRowToDCATable(String[] row);
}
