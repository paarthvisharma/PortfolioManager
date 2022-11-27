package controller.GUI;

import java.util.ArrayList;
import java.util.List;

import model.User;
import view.GUI.JView;

public interface JCreatePortfolioController {

  void back();
  void createPortfolio();
  void addStock();
  void setView(JView jView);
  void monitorTable(List<String> weightsColumn);

  void setUser(User user);
}
