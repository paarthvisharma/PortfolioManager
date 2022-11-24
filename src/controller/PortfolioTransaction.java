package controller;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import model.utils.Transaction;
import view.View;

import static controller.Utils.getDate;
import static controller.Utils.getStockQuantity;
import static controller.Utils.getTicker;
import static controller.Utils.listFlexiblePortfolios;
import static controller.Utils.takeLineInput;
import static controller.Utils.updateFlexiblePortfolioCsv;

/**
 * This class implements the ControllerCommand Interface and runs the loop that is used to
 * perform transactions on a portfolio.
 */

public class PortfolioTransaction implements ControllerCommand {

  User user;

  FlexiblePortfolio portfolio;

  /**
   * This is the constructor for PortfolioTransaction class.
   */
  public PortfolioTransaction(User user) {
    this.user = user;
  }

  @Override
  public void runLoop(Model model, Scanner in, View view) throws InterruptedException {
    view.displayPortfolioForTransaction();
    view.displayMessage(listFlexiblePortfolios(user, model));
    int portfolioId = Integer.parseInt(takeLineInput(in).trim());
    StatusObject<FlexiblePortfolio> selectedPortfolio = model.getParticularFlexiblePortfolio(user,
            portfolioId);
    if (selectedPortfolio.statusCode < 0) {
      view.displayFailureMessage(selectedPortfolio.statusMessage);
      view.askPromptToContinue();
      takeLineInput(in);
      return;
    }
    portfolio = selectedPortfolio.returnedObject;
    view.displaySuccessMessage(selectedPortfolio.statusMessage);
    StatusObject<PriorityQueue<Transaction>> previousTransactions =
            model.getFlexiblePortfoliosPastTransactions(user, portfolio);
    if (previousTransactions.statusCode < 0) {
      view.displayFailureMessage(previousTransactions.statusMessage);
      view.askPromptToContinue();
      takeLineInput(in);
      return;
    }
    view.askPromptToContinue();
    takeLineInput(in);
    while (true) {
      view.displayClear();
      try {
        view.displayPromptForTransactionType();
        String option = takeLineInput(in).trim();
        switch (option) {
          case "1":
            previousTransactions.returnedObject =
                    buyStockLoop(model, in, view, previousTransactions.returnedObject);
            break;
          case "2":
            previousTransactions.returnedObject =
                    sellStockLoop(model, in, view, previousTransactions.returnedObject);
            break;
          case "3":
            view.displayClear();
            updateFlexiblePortfolioCsv(model, in, view, user, portfolio);
            return;
          default:
            view.displayInvalidInputFailureMessageAndAskForPrompt();
            takeLineInput(in);
            view.displayClear();
        }
      } catch (InterruptedException e) {
        updateFlexiblePortfolioCsv(model, in, view, user, portfolio);
        throw new InterruptedException(e.getMessage());
      } catch (Exception e) {
        view.displayFailureMessage(e.getMessage());
      }
    }
  }

  private PriorityQueue<Transaction> buyStockLoop(
          Model model, Scanner in, View view, PriorityQueue<Transaction> transactions)
          throws InterruptedException {
    while (true) {
      int stockQuantity;
      String stockTicker;
      String date;
      try {
        stockTicker = getTicker(in, view);
        stockQuantity = getStockQuantity(in, view);
        date = getDate(in, view);
        PriorityQueue<Transaction> transactionsCopy = new PriorityQueue<>(transactions);
        transactionsCopy.add(new Transaction(new String[]{stockTicker,
                String.valueOf(stockQuantity), "BUY", date,
                String.valueOf(user.getCommission())}, false));
        StatusObject<PriorityQueue<Transaction>> response =
                model.getValidatedTransactions(portfolio, transactionsCopy);
        if (response.statusCode < 0) {
          view.displayFailureMessage(response.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
          return transactions;
        }
        transactions = transactionsCopy;
        StatusObject<FlexiblePortfolio> buyStatus = model.buyStockInFlexiblePortfolio(portfolio,
                stockTicker, date, stockQuantity);
        checkStatusOfTransaction(in, view, buyStatus);
        model.updateUserFile(user);
        return transactions;
      } catch (InterruptedException e) {
        throw new InterruptedException(e.getMessage());
      } catch (IllegalArgumentException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private PriorityQueue<Transaction> sellStockLoop(
          Model model, Scanner in, View view, PriorityQueue<Transaction> transactions)
          throws InterruptedException {
    while (true) {
      int stockQuantity;
      String stockTicker;
      String date;
      try {
        stockTicker = getTicker(in, view);
        stockQuantity = getStockQuantity(in, view);
        date = getDate(in, view);
        PriorityQueue<Transaction> transactionsCopy = new PriorityQueue<>(transactions);
        transactionsCopy.add(new Transaction(new String[]{stockTicker,
                String.valueOf(stockQuantity), "BUY", date,
                String.valueOf(user.getCommission())}, false));
        StatusObject<PriorityQueue<Transaction>> response =
                model.getValidatedTransactions(portfolio, transactionsCopy);
        if (response.statusCode < 0) {
          view.displayFailureMessage(response.statusMessage);
          view.askPromptToContinue();
          takeLineInput(in);
          return transactions;
        }
        transactions = transactionsCopy;
        StatusObject<FlexiblePortfolio> buyStatus = model.sellStockInFlexiblePortfolio(portfolio,
                stockTicker, date, stockQuantity);
        checkStatusOfTransaction(in, view, buyStatus);
        model.updateUserFile(user);
        return transactions;
      } catch (InterruptedException e) {
        throw new InterruptedException(e.getMessage());
      } catch (IllegalArgumentException e) {
        view.displayFailureMessage(e.getMessage());
        view.askPromptToContinue();
        takeLineInput(in);
        return transactions;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void checkStatusOfTransaction(
          Scanner in, View view, StatusObject<FlexiblePortfolio> buyStatus)
          throws InterruptedException {
    if (buyStatus.statusCode < 0) {
      view.displayFailureMessage(buyStatus.statusMessage);
      view.askPromptToContinue();
      takeLineInput(in);
      return;
    }
    view.displaySuccessMessage(buyStatus.statusMessage);
    view.askPromptToContinue();
    takeLineInput(in);
  }
}
