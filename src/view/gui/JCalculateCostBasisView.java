package view.gui;

import controller.gui.JCalculateCostBasisController;


/**
 * This is a view interface for Cost Basis GUI.
 */

public interface JCalculateCostBasisView {
  /**
   * Method to link the view to controller.
   *
   * @param jCalculateCostBasisController controller for Cost Basis Menu.
   */
  void addFeatures(JCalculateCostBasisController jCalculateCostBasisController);

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
