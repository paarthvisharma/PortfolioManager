package view.GUI;

import controller.GUI.JCalculateCostBasisController;

public interface JCalculateCostBasisView {

  void addFeatures(JCalculateCostBasisController jCalculateCostBasisController);

  void isVisible(boolean state);

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);

  void displayRadioButtonsForPortfolio(String portfolios);
}
