package controller.GUI;

import model.User;
import view.GUI.JView;

public interface JSetCommissionController {
  void back();

  void setCommission(String commission);

  void setUser(User user);
  void setView(JView jView);
}
