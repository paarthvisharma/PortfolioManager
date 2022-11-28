package view.GUI;

import controller.GUI.JLoadPortfolioController;

public interface JLoadPortfolioView {

  void addFeatures(JLoadPortfolioController jLoadPortfolioController);

  void clearAllUserInputs();

  void isVisible(boolean state);

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);

}
