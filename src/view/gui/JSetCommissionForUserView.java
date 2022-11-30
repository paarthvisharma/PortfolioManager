package view.gui;

import controller.gui.JSetCommissionController;

/**
 * This is a view interface for set commission menu.
 */
public interface JSetCommissionForUserView {
  /**
   * Method to link the view to controller.
   *
   * @param jSetCommissionController controller for set commission for user.
   */
  void addFeatures(JSetCommissionController jSetCommissionController);

  /**
   * Method to set the visibility of a view.
   */
  void isVisible(boolean state);

  /**
   * Method to clear the user inputs.
   */
  void clearAllUserInputs();

  /**
   * Method to set the log output.
   *
   * @param message required message.
   */
  void setLogOutput(String message);

  /**
   * Method to set initial output.
   *
   * @param commission amount of commission.
   */
  void setInitialMessage(double commission);

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
