package controller;

import java.util.Scanner;

import model.Model;
import view.View;

/**
 * General interface which is used to implement the controller command design pattern.
 */
public interface ControllerCommand {

  /**
   * Method to continue running the controller loop.
   */
  void runLoop(Model model, Scanner in, View view) throws InterruptedException;

}
