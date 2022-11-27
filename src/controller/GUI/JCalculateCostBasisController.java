package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JCalculateCostBasisController {
  void back();

  void calculatePortfolioCostBasis(String date, String selectedPortfolio);

  void setUser(User user);
  void setView(JView jView);
}
