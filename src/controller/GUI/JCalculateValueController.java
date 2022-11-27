package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JCalculateValueController {

  void back();

  void calculatePortfolioValue(String date, String selectedPortfolio);

  void setUser(User user);
  void setView(JView jView);
}
