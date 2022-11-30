package controller.GUI;

import java.util.List;
import java.util.Map;

import model.User;
import view.GUI.JView;

public interface JCreatePortfolioController {

  void back();
  void createPortfolio(String portfolioName, Map<String, String> dcaSetting, List<List<String>> tableData);
  void addStock(String ticker, String quantity, String date);
  void setView(JView jView);
  void monitorTable(List<String> weightsColumn);

  void setUser(User user);

  void resetStockList();

  void addStockForDCA(String ticker);
}
