package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JPortfolioController {

  void back();
  void createPortfolio();
  void valueOfPortfolio();
  void costBasisPortfolio();
  void loadPortfolio();
  void viewPortfolio();
  void performanceOfPortfolio();
  void transactInPortfolio();
  void setUser(User user);

//  void setView(JPortfolioMenuView jPortfolioMenuView);
  void setView(JView jView);
}
