package view.gui;

import controller.gui.JViewPortfolioController;

/**
 * This is a view interface for view portfolio menu.
 */
public interface JViewPortfolioView {
  /**
   * Method to link the view to controller.
   *
   * @param jViewPortfolioController controller for portfolio menu.
   */
  void addFeatures(JViewPortfolioController jViewPortfolioController);

  /**
   * Method to set the visibility of a view.
   */
  void isVisible(boolean state);

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
   */
  void displayRadioButtonsForPortfolio(String portfolios);

  /**
   * Method to add row to portfolio table.
   *
   * @param row value.
   */
  void addRowToTable(String[] row);

  /**
   * Method to clear the user inputs.
   */
  void clearUserInputs();

  /**
   * Method to clear the table.
   */
  void clearTable();
}
