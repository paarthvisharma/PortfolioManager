package controller.GUI;

import view.GUI.JView;

public interface JUserController {

  void createUser(String firstName, String lastName);

  void loginUser(String userId);

  void loadUser(String pathToUserFile);

//  void setView(JCreateUserView jCreateUserView);
  void setView(JView jView);
}
