package view.gui;

import controller.gui.JPerformanceController;

public interface JPerformanceView {

  void addFeatures(JPerformanceController jPerformanceController);

  void displayRadioButtonsForPortfolio(String portfolios);

  void isVisible(boolean state);

  void clearUserInputs();

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);
}
