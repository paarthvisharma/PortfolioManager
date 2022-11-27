package view.GUI;

import controller.GUI.JCreatePortfolioController;

public interface JCreatePortfolioView {

  void addFeatures(JCreatePortfolioController jCreatePortfolioController);

  void isVisible(boolean state);

  void addRowToTable(String[] row);

  void dollarCostAveragingEnabled(boolean state);

  void clearUserInputs();
}
