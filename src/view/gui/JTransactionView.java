package view.gui;

import controller.gui.JTransactionController;

public interface JTransactionView {

  void enableAddPlanDCA(boolean state);

  void addFeatures(JTransactionController jTransactionController);

  void clearUserInputs();

  void clearTable();

  void isVisible(boolean state);

  void dcaPanelIsVisible();

  void buySellPanelIsVisible();

  void setLogOutput(String message);

  void setSuccessOutput(String message);

  void setFailureOutput(String message);

  void displayRadioButtonsForPortfolio(String portfolios);

  void addRowToDCATable(String[] row);
}
