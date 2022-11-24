package controller;

import java.util.Scanner;

import model.FlexiblePortfolio;
import model.Model;
import model.Portfolio;
import model.User;
import model.utils.StatusObject;

import view.View;

/**
 * This class contains static helper methods which are used by the Controller.
 */
public class Utils {

  /**
   * Static method to take the input.
   */
  public static String takeLineInput(Scanner in) throws InterruptedException {
    String input = in.nextLine();
    if (input.trim().equals("*")) {
      throw new InterruptedException("Terminating program due to interrupt");
    }
    return input;
  }

  /**
   * Static method to list rigid portfolio.
   */

  public static String listRigidPortfolios(User user, Model model) {
    StringBuilder toReturn = new StringBuilder();
    for (Portfolio portfolio : model.getRigidPortfoliosForUser(user)) {
      toReturn.append(model.getPortfolioId(portfolio)).append(" ")
              .append(model.getPortfolioName(portfolio)).append(" ").append("(")
              .append(model.getPortfolioType(portfolio)).append(")").append("\n");
    }
    return toReturn.toString();
  }

  /**
   * Static method to list flexible portfolio.
   */
  public static String listFlexiblePortfolios(User user, Model model) {
    StringBuilder toReturn = new StringBuilder();
    for (Portfolio portfolio : model.getFlexiblePortfoliosForUser(user)) {
      toReturn.append(model.getPortfolioId(portfolio)).append(" ")
              .append(model.getPortfolioName(portfolio)).append(" ").append("(")
              .append(model.getPortfolioType(portfolio)).append(")").append("\n");
    }
    return toReturn.toString();
  }

  /**
   * Static method to get ticker.
   */
  public static String getTicker(Scanner in, View view) throws InterruptedException {
    String stockTicker = null;
    view.displayClear();
    view.promptForStockTicker();
    stockTicker = takeLineInput(in).trim();
    return stockTicker;
  }

  /**
   * Static method to get date.
   */
  public static String getDate(Scanner in, View view) throws InterruptedException {
    view.displayClear();
    view.promptForStockPurchaseDate();
    String date = takeLineInput(in).trim();
    return date;
  }

  /**
   * Static method to get stock quantity.
   */
  public static int getStockQuantity(Scanner in, View view) throws InterruptedException {
    double stockQuantity;
    while (true) {
      try {
        view.displayClear();
        view.promptForStockQuantity();
        stockQuantity = Double.parseDouble(takeLineInput(in).trim());
        if (stockQuantity < 0 | stockQuantity - Math.round(stockQuantity) != 0) {
          throw new NumberFormatException();
        }
        break;
      } catch (NumberFormatException e) {
        view.displayFailureMessage("Quantity of a stock should be a positive integer");
        view.askPromptToContinue();
        takeLineInput(in);
      }
    }
    return (int) stockQuantity;
  }

  /**
   * Static method to update CSV file.
   */
  public static void updateFlexiblePortfolioCsv(Model model, Scanner in, View view,
                                                User user, FlexiblePortfolio portfolio)
          throws InterruptedException {
    StatusObject<String> updates = model.updateFlexiblePortfolioCsv(user, portfolio);
    if (updates.statusCode < 0) {
      view.displayFailureMessage(updates.statusMessage);
      view.askPromptToContinue();
      takeLineInput(in);
    }
  }

}
