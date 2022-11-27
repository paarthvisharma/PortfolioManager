package view.GUI;

import controller.GUI.JCalculateValueController;

public interface JCalculateValueView {

  void addFeatures(JCalculateValueController jCalculateValueController);

  void isVisible(boolean state);

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);

  void displayRadioButtonsForPortfolio(String portfolios);
}
