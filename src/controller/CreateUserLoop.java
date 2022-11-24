package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Model;
import model.User;
import model.utils.StatusObject;
import view.View;

import static controller.Utils.takeLineInput;

/**
 * This class implements the ControllerCommand Interface and provides method to run the loop to
 * create a user.
 */
public class CreateUserLoop implements ControllerCommand {

  /**
   * This is the constructor for CreateUserLoop. An Instance of User is created with
   * the help of this constructor.
   */
  public CreateUserLoop() {
    //User Loop.
  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      try {
        view.displayClear();
        view.displayCreateUserMenu();
        String option = takeLineInput(in);
        switch (option.trim()) {
          case "1":
            this.createUserManually(model, in, view);
            break;
          case "2":
            this.loadUserFromXML(model, in, view);
            break;
          case "3":
            view.displayClear();
            return;
          default:
            view.displayInvalidInputFailureMessageAndAskForPrompt();
            takeLineInput(in);
            view.displayClear();
        }
      } catch (InterruptedException e) {
        throw new InterruptedException(e.getMessage());
      } catch (Exception e) {
        view.displayFailureMessage(e.getMessage());
      }
    }
  }

  private void createUserManually(Model model, Scanner in, View view) throws InterruptedException {
    view.displayClear();
    while (true) {
      try {
        view.displayClear();
        view.displayCreateUserManuallyPrompt();
        String createUserInput = takeLineInput(in).trim();
        String firstName = createUserInput.trim().split("\\s", 2)[0];
        String lastName = createUserInput.trim().split("\\s", 2)[1];
        StatusObject<User> response = model.createUser(firstName, lastName, 1);
        if (response.statusCode > 0) {
          model.updateUserFile(response.returnedObject);
          view.displaySuccessMessage(response.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
          return;
        } else {
          view.displayFailureMessage(response.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
        }
      } catch (IndexOutOfBoundsException e) {
        view.displayFailureMessage("Please enter the First name and Last name "
                + "seperated by space.");
        view.askPromptToContinue();
        takeLineInput(in);
        return;
      } catch (IOException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
        return;
      }
    }
  }

  private void loadUserFromXML(Model model, Scanner in, View view) throws InterruptedException {
    view.displayClear();
    while (true) {
      try {
        view.displayClear();
        view.promptForXmlFilePath();
        String xmlPath = takeLineInput(in).trim();
        StatusObject<User> createdUser = model.createUserFromXML(xmlPath);
        if (createdUser.statusCode > 0) {
          model.updateUserFile(createdUser.returnedObject);
          view.displaySuccessMessage("Successfully loaded the User:\n"
                  + ((createdUser.returnedObject)).getFirstName() + " "
                  + ((createdUser.returnedObject)).getLastName());
          view.askPromptToContinue();
          takeLineInput(in);
          return;
        } else {
          view.displayFailureMessage(createdUser.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
        }
      } catch (IOException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
        return;
      }
    }
  }
}
