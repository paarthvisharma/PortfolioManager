package view.gui;

import controller.gui.JPerformanceController;

/**
 * This is a view interface for view performance menu.
 */
public interface JPerformanceView {
  /**
   * Method to link the view to controller.
   *
   * @param jPerformanceController controller for view performance.
   */
  void addFeatures(JPerformanceController jPerformanceController);

  /**
   * Method to display the radio buttons.
   *
   * @param portfolios required portfolios.
   */
  void displayRadioButtonsForPortfolio(String portfolios);

  /**
   * Method to set the visibility of a view.
   */
  void isVisible(boolean state);

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
