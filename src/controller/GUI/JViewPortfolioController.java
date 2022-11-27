package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JViewPortfolioController {

  void back();
  void setView(JView jView);

  void setUser(User user);

//  void resetStockList();
  void viewPortfolio(String date, String selectedPortfolio);
}
