package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JTransactionController {

  void setView(JView jView);

  void buyStock(String ticker, String quantity, String date);
  void sellStock(String ticker, String quantity, String date);
  void createDCAPlan(String startDate, String endDate, String interval, String dollarAmount, String commission);

  void displayBuySellView();
  void displayDCAView();

  void setUser(User user);

  void selectPortfolioTransaction(String portfolioIdAndName);

  void back();
}
