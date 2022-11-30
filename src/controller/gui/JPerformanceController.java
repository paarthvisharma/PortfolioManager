package controller.gui;

import model.User;
import view.gui.JView;

public interface JPerformanceController {
  void setView(JView jView);

  void setUser(User user);

  void selectPortfolio(String portfolioIdAndName);

  void back();

  void plotGraph(String startDate, String EndDate);
}
