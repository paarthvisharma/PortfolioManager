package controller.gui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.gui.JPortfolioMenuView;
import view.gui.JTransactionView;
import view.gui.JView;

import static controller.Utils.getPresentDate;
import static controller.gui.GUIControllerHelper.processDCACreationHelper;

/**
 * This class implements the JTransactionController interface for Transaction GUI which and
 * contains the methods which help display the create transaction menu.
 */

public class JTransactionControllerImpl implements JTransactionController {

  private final Model model;
  private User user;
  private JTransactionView transactionView;
  private JPortfolioMenuView portfolioMenuView;
  private FlexiblePortfolio selectedPortfolio;

  /**
   * Constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JTransactionControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void setView(JView jView) {
    this.transactionView = jView.getTransactionView();
    this.portfolioMenuView = jView.getPortfolioMenuView();
    this.transactionView.addFeatures(this);
  }

  @Override
  public void buyStock(String ticker, String quantity, String date) {
    try {
      StatusObject<FlexiblePortfolio> modifiedPortfolio = model.buyStockInFlexiblePortfolio(
              selectedPortfolio, ticker, date, Integer.parseInt(quantity));
      if (modifiedPortfolio.statusCode < 0) {
        this.transactionView.setFailureOutput(modifiedPortfolio.statusMessage);
        return;
      }
      this.transactionView.setSuccessOutput(modifiedPortfolio.statusMessage);
    } catch (NumberFormatException e) {
      this.transactionView.setFailureOutput("Stock quantity should be a positive integer");
    }
  }

  @Override
  public void sellStock(String ticker, String quantity, String date) {
    try {
      StatusObject<FlexiblePortfolio> modifiedPortfolio = model.sellStockInFlexiblePortfolio(
              selectedPortfolio, ticker, date, Integer.parseInt(quantity));
      if (modifiedPortfolio.statusCode < 0) {
        this.transactionView.setFailureOutput(modifiedPortfolio.statusMessage);
        return;
      }
      this.transactionView.setSuccessOutput(modifiedPortfolio.statusMessage);
    } catch (NumberFormatException e) {
      this.transactionView.setFailureOutput("Stock quantity should be a positive integer");
    }
  }

  @Override
  public void createDCAPlan(Map<String, String> dcaSetting, List<List<String>> tableData) {
    try {
      processDCACreation(selectedPortfolio, dcaSetting, tableData);
      model.updateUserFile(user);
      this.transactionView.setSuccessOutput("Added DCA plan to "
              + selectedPortfolio.getPortfolioId());
    } catch (RuntimeException e) {
      this.transactionView.setFailureOutput(e.getMessage());
    } catch (IOException e) {
      this.transactionView.setFailureOutput("User file could not be found. "
              + "Please check the config file.");
    }
  }

  private void processDCACreation(FlexiblePortfolio portfolio, Map<String,
          String> dcaSetting, List<List<String>> tableData) {
    processDCACreationHelper(portfolio, dcaSetting, tableData, model);
  }

  @Override
  public void displayBuySellView() {
    this.transactionView.buySellPanelIsVisible();
  }

  @Override
  public void displayDCAView() {
    this.transactionView.dcaPanelIsVisible();
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void selectPortfolioTransaction(String portfolioIdAndName) {
    this.transactionView.clearTable();
    if (portfolioIdAndName == null) {
      this.transactionView.setFailureOutput("Select one of the portfolio from the list shown");
      return;
    }
    try {
      int portfolioId = Integer.parseInt(portfolioIdAndName.split("\\s")[0]);
      StatusObject<FlexiblePortfolio> status =
              model.getParticularFlexiblePortfolio(user, portfolioId);
      if (status.statusCode < 0) {
        this.transactionView.setFailureOutput(status.statusMessage);
      }
      selectedPortfolio = status.returnedObject;
      StatusObject<List<List<String>>> portfolioDetails =
              model.getCompositionOfFlexiblePortfolioAsList(user,
                      selectedPortfolio, getPresentDate());
      if (portfolioDetails.statusCode < 0) {
        this.transactionView.setFailureOutput(portfolioDetails.statusMessage);
        return;
      }
      for (List<String> stock : portfolioDetails.returnedObject) {
        this.transactionView.addRowToDCATable(new String[]{stock.get(0),
                stock.get(1), stock.get(2), stock.get(3), "0"});
      }
      this.transactionView.setSuccessOutput("Successfully selected "
              + selectedPortfolio.getPortfolioId());
    } catch (NumberFormatException e) {
      this.transactionView.setFailureOutput("Portfolio ID is not an integer");
    }
  }

  @Override
  public void monitorTable(List<String> weightsColumn) {
    double totalSum = 0;
    for (String weight : weightsColumn) {
      totalSum += Double.parseDouble(weight);
    }
    if (totalSum != 100) {
      this.transactionView.enableAddPlanDCA(false);
      this.transactionView.setLogOutput("Sum of the weights should be 100");
    }
    if (totalSum == 100) {
      this.transactionView.enableAddPlanDCA(true);
    }
  }

  @Override
  public void addNewStockToDCATable(String ticker) {
    if (model.validateTicker(ticker.trim())) {
      this.transactionView.addRowToDCATable(new
              String[]{ticker.trim().toLowerCase(), "", "", "", "0"});
    } else {
      transactionView.setFailureOutput("Ticker is not valid");
    }
  }

  @Override
  public void back() {
    this.transactionView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }
}
