package controller;

import java.util.Scanner;

/**
 * The Controller Interface represents the Controller part of the Stocks Program out of the three
 * broad categories of model-view-controller (MVC) design.
 * This Controller Interface takes inputs from the user and
 * delegates responsibility - tells the model what to do and the view what to show.
 */
public interface Controller {

  void userMenu(Scanner in) throws InterruptedException;

  void runLoop();

}
