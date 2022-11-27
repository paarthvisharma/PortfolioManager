package view.GUI;

import controller.GUI.JUserController;

public interface JCreateUserView {

  void addFeatures(JUserController jUserController);

  void clearAllUserInputs();

  void isVisible(boolean state);

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);

}
