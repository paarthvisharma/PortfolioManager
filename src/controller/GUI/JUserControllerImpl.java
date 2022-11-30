package controller.GUI;

import java.io.IOException;

import model.Model;
import model.User;
import model.utils.StatusObject;
import view.GUI.JCreateUserView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;

/**
 * This class implements the JUserController interface for User GUI which and
 * contains the methods which help display the create user menu.
 */
public class JUserControllerImpl implements JUserController {

  private Model model;

  private JView view;
  private JCreateUserView createUserView;
  private JPortfolioMenuView portfolioMenuView;
  private JPortfolioController portfolioController;

  /**
   * Constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JUserControllerImpl(Model model) {
    this.model = model;
    portfolioController = new JPortfolioControllerImpl(model);
  }

  @Override
  public void createUser(String firstName, String lastName) {
    if (firstName.equals("") | lastName.equals("")) {
      this.createUserView.setFailureOutput("First Name and/or Last Name cannot be"
              + " left blank. Please try again");
      return;
    }
    try {
      StatusObject<User> response = model.createUser(firstName, lastName, 1);
      if (response.statusCode > 0) {
        model.updateUserFile(response.returnedObject);
        this.createUserView.setSuccessOutput(response.statusMessage);
        this.createUserView.clearAllUserInputs();
      } else {
        this.createUserView.setFailureOutput(response.statusMessage);
      }
    } catch (IOException e) {
      this.createUserView.setFailureOutput(e.getMessage());
    }
  }

  @Override
  public void loginUser(String userId) {
    if (userId.equals("")) {
      this.createUserView.setFailureOutput("UserId cannot be blank.");
      return;
    }
    try {
      User user = null;
      StatusObject<User> selectedUser = model.getUser(userId);
      if (selectedUser.statusCode > 0) {
        user = selectedUser.returnedObject;
        portfolioController.setUser(user);
        this.createUserView.clearAllUserInputs();
        this.createUserView.isVisible(false);
        this.portfolioMenuView.isVisible(true);
      } else {
        this.createUserView.setFailureOutput(selectedUser.statusMessage);
      }
    } catch (Exception e) {
      this.createUserView.setFailureOutput(e.getMessage());
    }
  }

  @Override
  public void loadUser(String pathToUserFile) {
    try {
      StatusObject<User> createdUser = model.createUserFromXML(pathToUserFile);
      if (createdUser.statusCode > 0) {
        model.updateUserFile(createdUser.returnedObject);
        this.createUserView.setSuccessOutput("Successfully loaded the User:\n"
                + ((createdUser.returnedObject)).getFirstName() + " "
                + ((createdUser.returnedObject)).getLastName());
        this.createUserView.clearAllUserInputs();
      } else {
        this.createUserView.setFailureOutput(createdUser.statusMessage);
      }
    } catch (IOException e) {
      this.createUserView.setFailureOutput(e.getMessage());
    }
  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.createUserView = this.view.getCreateUserView();
    this.portfolioMenuView = this.view.getPortfolioMenuView();
    this.createUserView.addFeatures(this);
    portfolioController.setView(view);
  }

}
