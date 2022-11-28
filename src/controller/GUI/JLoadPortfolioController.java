package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JLoadPortfolioController {

  void back();

  void loadPortfolio(String xmlPath);

  void setView(JView jView);

  void setUser(User user);
}
