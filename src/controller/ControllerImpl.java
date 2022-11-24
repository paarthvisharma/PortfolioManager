package controller;

import java.io.InputStream;
import java.util.Scanner;

import model.Model;
import view.View;

import static controller.Utils.takeLineInput;

/**
 * This class is an implementation of the Controller Interface.
 * The methods associated with the class help perform operations of
 * the controller which is to delegate responsibilities.
 */
public class ControllerImpl implements Controller {

  private Scanner in;
  private View view;
  private Model model;

  /**
   * This is the constructor for ControllerImpl. An Instance of ControllerImpl is created with
   * the help of this constructor.
   * It creates a ControllerImpl by taking the following parameters.
   *
   * @param model an object of type model.
   * @param in    InputStream.
   * @param view  an object of type View.
   */

  public ControllerImpl(Model model, InputStream in, View view) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
  }

  @Override
  public void userMenu(Scanner in) throws InterruptedException {
    view.displayClear();
    ControllerCommand cmd = null;
    while (true) {
      try {
        view.displayUserMenu();
        String option = takeLineInput(in);
        switch (option.trim()) {
          case "1":
            cmd = new CreateUserLoop();
            break;
          case "2":
            cmd = new PortfolioLoop();
            break;
          case "3":
            return;
          default:
            view.displayInvalidInputFailureMessageAndAskForPrompt();
            takeLineInput(in);
            view.displayClear();
        }
        if (cmd != null) {
          cmd.runLoop(model, in, view);
          cmd = null;
        }
      } catch (InterruptedException e) {
        throw new InterruptedException(e.getMessage());
      }
    }
  }

  @Override
  public void runLoop() {
    try {
      userMenu(this.in);
    } catch (InterruptedException e) {
      return;
    } catch (Exception e) {
      System.out.println("Error");
    }
  }

}
