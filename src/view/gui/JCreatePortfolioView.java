package view.gui;

import controller.gui.JCreatePortfolioController;

/**
 * This is a view interface for create portfolio menu.
 */
public interface JCreatePortfolioView {
  /**
   * Method to link the view to controller.
   *
   * @param jCreatePortfolioController controller for portfolio menu.
   */
  void addFeatures(JCreatePortfolioController jCreatePortfolioController);

  /**
   * Method to set the visibility of a view.
   */
  void isVisible(boolean state);

  /**
   * Method to add rows to portfolio table.
   */
  void addRowToTable(String[] row);

  /**
   * Method to set dollar cost averaging.
   */
  void dollarCostAveragingEnabled(boolean state);

  /**
   * Method to set the state of create portfolio button.
   */
  void enableCreatePortfolioButton(boolean state);

  /**
   * Method to clear the user inputs.
   */
  void clearUserInputs();

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
}
