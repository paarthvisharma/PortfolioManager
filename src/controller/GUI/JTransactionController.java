package controller.GUI;

import java.util.List;
import java.util.Map;

import model.User;
import view.GUI.JView;

public interface JTransactionController {

  void setView(JView jView);

  void buyStock(String ticker, String quantity, String date);
  void sellStock(String ticker, String quantity, String date);
  void createDCAPlan(Map<String, String> dcaSetting, List<List<String>> tableData);

  void displayBuySellView();
  void displayDCAView();

  void setUser(User user);

  void selectPortfolioTransaction(String portfolioIdAndName);

  void monitorTable(List<String> weightsColumn);

  void addNewStockToDCATable(String ticker);

  void back();
}
