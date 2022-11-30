package view.gui;

import controller.gui.JLoadPortfolioController;

/**
 * This is a view interface for view portfolio menu.
 */
public interface JLoadPortfolioView {
  /**
   * Method to link the view to controller.
   *
   * @param jLoadPortfolioController controller for view portfolio.
   */
  void addFeatures(JLoadPortfolioController jLoadPortfolioController);

  /**
   * Method to clear the user inputs.
   */
  void clearAllUserInputs();

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

}
