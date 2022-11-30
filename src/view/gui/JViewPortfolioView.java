package view.gui;

import controller.gui.JViewPortfolioController;

public interface JViewPortfolioView {
  void addFeatures(JViewPortfolioController jViewPortfolioController);

  void isVisible(boolean state);

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);

  void displayRadioButtonsForPortfolio(String portfolios);

  void addRowToTable(String[] row);

  void clearUserInputs();

  void clearTable();
}
