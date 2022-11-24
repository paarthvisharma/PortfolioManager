package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.FlexiblePortfolio;
import model.Model;
import model.RigidPortfolio;
import model.Stock;
import model.User;
import model.utils.StatusObject;
import view.View;

import static controller.Utils.getDate;
import static controller.Utils.getStockQuantity;
import static controller.Utils.getTicker;
import static controller.Utils.takeLineInput;
import static controller.Utils.updateFlexiblePortfolioCsv;

/**
 * This class implements the ControllerCommand Interface and provides method to run the loop to
 * create a portfolio.
 */
public class CreatePortfolio implements ControllerCommand {

  private final User user;

  /**
   * This is the constructor for CreatePortfolio. An Instance of Portfolio is created with
   * the help of this constructor by taking the following parameters:
   *
   * @param user an object of type User.
   */

  public CreatePortfolio(User user) {
    this.user = user;
  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    while (true) {
      view.displayClear();
      try {
        view.displayCreatePortfolioMenu();
        String option = takeLineInput(in);
        switch (option.trim()) {
          case "1":
            createRigidPortfolio(model, in, view);
            break;
          case "2":
            createFlexiblePortfolio(model, in, view);
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

  private String getPortfolioName(Scanner in, View view) throws InterruptedException {
    view.displayClear();
    while (true) {
      try {
        view.promptPortfolioName();
        String portfolioName = takeLineInput(in).trim();
        if (portfolioName.equals("")) {
          throw new IllegalArgumentException("Portfolio name cannot be blank");
        }
        return portfolioName;
      } catch (IllegalArgumentException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
      }
    }
  }

  private void createRigidPortfolio(Model model, Scanner in, View view)
          throws InterruptedException {
    try {
      view.displayClear();
      String portfolioName = getPortfolioName(in, view);
      StatusObject<RigidPortfolio> createdPortfolio = model.createRigidPortfolio(
              user, portfolioName,
              this.createStockListRigidPortfolio(model, in, view));
      if (createdPortfolio.statusCode > 0) {
        model.updateUserFile(user);
        view.displaySuccessMessage(createdPortfolio.statusMessage);
        view.askPromptToContinue();
        takeLineInput(in);
        view.displayClear();
      } else {
        view.displayFailureMessage(createdPortfolio.statusMessage);
        view.askPromptToContinue();
        takeLineInput(in);
      }
    } catch (IOException e) {
      view.displayFailureMessage("User file could not be found. Please check the config file.");
      view.askPromptToContinue();
      takeLineInput(in);
    }
  }

  private void createFlexiblePortfolio(Model model, Scanner in, View view)
          throws InterruptedException {
    try {
      view.displayClear();
      String portfolioName = getPortfolioName(in, view);
      StatusObject<FlexiblePortfolio> createdPortfolio = model.createFlexiblePortfolio(
              user, portfolioName,
              this.createStockListFlexiblePortfolio(model, in, view));
      if (createdPortfolio.statusCode > 0) {
        model.updateUserFile(user);
        view.displaySuccessMessage(createdPortfolio.statusMessage);
        view.askPromptToContinue();
        takeLineInput(in);
        view.displayClear();
      } else {
        view.displayFailureMessage(createdPortfolio.statusMessage);
        view.askPromptToContinue();
        takeLineInput(in);
      }
      updateFlexiblePortfolioCsv(model, in, view, user, createdPortfolio.returnedObject);
    } catch (IOException e) {
      view.displayFailureMessage("User file could not be found. Please check the config file.");
      view.askPromptToContinue();
      takeLineInput(in);
    }
  }

  private List<Stock> createStockListRigidPortfolio(Model model, Scanner in, View view) throws
          InterruptedException {
    view.displayClear();
    List<Stock> listOfStocks = new ArrayList<>();
    while (true) {
      int stockQuantity;
      String stockTicker;
      try {
        stockTicker = getTicker(in, view);
        stockQuantity = getStockQuantity(in, view);


        StatusObject<Stock> buyStatus = new StatusObject<>("",
                -1, null);
        for (Stock stock : listOfStocks) {
          if (model.getStockTicker(stock).equalsIgnoreCase(stockTicker)) {
            buyStatus = model.addStockToUninitializedPortfolio(stock, stockQuantity);
          }
        }
        if (buyStatus.statusCode < 0) {
          listOfStocks.add(model.createStock(stockTicker, stockQuantity, null));
        }


        view.displaySuccessMessage(stockQuantity + " of " + stockTicker
                + " has been added to the portfolio.\n");
        view.askPromptToContinue();
        takeLineInput(in);
        boolean exit = false;
        while (!exit) {
          view.displayPromptIfMoreStocksToBeAdded();
          String option = takeLineInput(in).trim();
          switch (option) {
            case "1":
              exit = true;
              view.displayClear();
              break;
            case "2":
              return listOfStocks;
            default:
              view.displayInvalidInputFailureMessageAndAskForPrompt();
              takeLineInput(in);
              view.displayClear();
          }
        }
      } catch (IllegalArgumentException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
      }
    }
  }

  private List<Stock> createStockListFlexiblePortfolio(Model model, Scanner in, View view) throws
          InterruptedException {
    view.displayClear();
    List<Stock> listOfStocks = new ArrayList<>();
    while (true) {
      int stockQuantity;
      String stockTicker;
      String date;
      try {
        stockTicker = getTicker(in, view);
        stockQuantity = getStockQuantity(in, view);
        date = getDate(in, view);


        StatusObject<Stock> buyStatus = new StatusObject<Stock>("",
                -1, null);
        for (Stock stock : listOfStocks) {
          if (model.getStockTicker(stock).equalsIgnoreCase(stockTicker)
                  & model.getStockPurchaseDate(stock).equalsIgnoreCase(date)) {
            buyStatus = model.addStockToUninitializedPortfolio(stock, stockQuantity);
          }
        }
        if (buyStatus.statusCode < 0) {
          listOfStocks.add(model.createStock(stockTicker, stockQuantity, date));
        }

        view.displaySuccessMessage(stockQuantity + " of " + stockTicker
                + " has been added to the portfolio.\n");
        view.askPromptToContinue();
        takeLineInput(in);
        boolean exit = false;
        while (!exit) {
          view.displayPromptIfMoreStocksToBeAdded();
          String option = takeLineInput(in).trim();
          switch (option) {
            case "1":
              exit = true;
              view.displayClear();
              break;
            case "2":
              return listOfStocks;
            default:
              view.displayInvalidInputFailureMessageAndAskForPrompt();
              takeLineInput(in);
              view.displayClear();
          }
        }
      } catch (IllegalArgumentException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
      }
    }
  }
}
