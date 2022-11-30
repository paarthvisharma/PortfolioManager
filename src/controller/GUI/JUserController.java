package controller.GUI;

import view.GUI.JView;

/**
 * This is a controller interface for User Menu GUI which displays the
 * main User menu.
 */
public interface JUserController {
  /**
   * Method to display create user menu option.
   *
   * @param firstName first name associated with a user.
   * @param lastName  last name associated with a user.
   */
  void createUser(String firstName, String lastName);

  /**
   * Method to display login menu option.
   *
   * @param userId ID associated with a user.
   */
  void loginUser(String userId);

  /**
   * Method to load the user menu option.
   *
   * @param pathToUserFile path of the file.
   */
  void loadUser(String pathToUserFile);

  //  void setView(JCreateUserView jCreateUserView);

  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */
  void setView(JView jView);

}
