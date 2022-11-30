package view.gui;

import controller.gui.JUserController;

/**
 * This is a view interface for create user menu.
 */
public interface JCreateUserView {
  /**
   * Method to link the view to controller.
   *
   * @param jUserController controller for create user menu.
   */
  void addFeatures(JUserController jUserController);

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
