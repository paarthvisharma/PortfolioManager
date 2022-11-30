package view.GUI;

import controller.GUI.JSetCommissionController;

public interface JSetCommissionForUserView {

  void addFeatures(JSetCommissionController jSetCommissionController);
  void isVisible(boolean state);
  void clearAllUserInputs();

  void setLogOutput(String message);

  void setInitialMessage(double commission);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);
}
