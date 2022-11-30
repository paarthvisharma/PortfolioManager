package view.gui;

import controller.gui.JCalculateValueController;

/**
 * This is a view interface for calculate Value menu.
 */
public interface JCalculateValueView {
  /**
   * Method to link the view to controller.
   *
   * @param jCalculateValueController controller for calculate value Menu.
   */
  void addFeatures(JCalculateValueController jCalculateValueController);

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
   * Method to display the radio buttons.
   *
   * @param portfolios portfolios string.
   */
  void displayRadioButtonsForPortfolio(String portfolios);
}
