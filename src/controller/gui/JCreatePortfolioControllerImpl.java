package controller.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.FlexiblePortfolio;
import model.Model;
import model.Stock;
import model.User;
import model.utils.StatusObject;
import view.gui.JCreatePortfolioView;
import view.gui.JPortfolioMenuView;
import view.gui.JView;

import static controller.gui.GUIControllerHelper.processDCACreationHelper;

/**
 * This class implements the JCreatePortfolioController interface for Portfolio GUI which and
 * contains the methods which help display the create portfolio menu.
 */
public class JCreatePortfolioControllerImpl implements JCreatePortfolioController {

  private final Model model;
  private JCreatePortfolioView createPortfolioView;
  private JPortfolioMenuView portfolioMenuView;
  private User user;

  private List<Stock> listOfStocks = new ArrayList<>();

  /**
   * A constructor to initialize the model.
   *
   * @param model an object of type Model.
   */
  public JCreatePortfolioControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.createPortfolioView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }

  @Override
  public void createPortfolio(String portfolioName, Map<String, String> dcaSetting,
                              List<List<String>> tableData) {
    if (portfolioName.trim().equals("")) {
      this.createPortfolioView.setFailureOutput("Name of the portfolio cannot be blank");
      return;
    }
    if (this.listOfStocks.isEmpty() & dcaSetting.get("startDate").equals("")) {
      this.createPortfolioView.setFailureOutput("Portfolios must have at least one stock");
      return;
    }
    try {
      this.createPortfolioView.clearUserInputs();
      StatusObject<FlexiblePortfolio> createdPortfolio = model.createFlexiblePortfolio(
              user, portfolioName,
              this.listOfStocks);
      if (createdPortfolio.statusCode > 0) {
        if (!dcaSetting.get("startDate").equals("")) {
          try {
            processDCACreation(createdPortfolio.returnedObject, dcaSetting, tableData);
            model.updateUserFile(user);
            this.createPortfolioView.setSuccessOutput(createdPortfolio.statusMessage);
          } catch (RuntimeException e) {
            this.createPortfolioView.setFailureOutput(e.getMessage());
          }
        } else {
          model.updateUserFile(user);
          this.createPortfolioView.setSuccessOutput(createdPortfolio.statusMessage);
        }
      } else {
        this.createPortfolioView.setFailureOutput(createdPortfolio.statusMessage);
      }
      StatusObject<String> updates = model.updateFlexiblePortfolioCsv(user,
              createdPortfolio.returnedObject);
      if (updates.statusCode < 0) {
        this.createPortfolioView.setFailureOutput(updates.statusMessage);
      }
    } catch (IOException e) {
      this.createPortfolioView.setFailureOutput("User file could not be found. "
              + "Please check the config file.");
    }
  }

  private void processDCACreation(FlexiblePortfolio portfolio, Map<String, String> dcaSetting,
                                  List<List<String>> tableData) {
    processDCACreationHelper(portfolio, dcaSetting, tableData, model);
  }

  @Override
  public void addStock(String ticker, String quantity, String date) {
    if (ticker.trim().equals("")) {
      this.createPortfolioView.setFailureOutput("Ticker cannot be empty");
      return;
    }
    if (date.trim().equals("") | date.trim().equals("YYYY-MM-DD")) {
      this.createPortfolioView.setFailureOutput("Date cannot be empty");
      return;
    }
    if (quantity.trim().equals("")) {
      this.createPortfolioView.setFailureOutput("Quantity cannot be empty");
      return;
    }
    try {
      StatusObject<Stock> buyStatus = new StatusObject<>("", -1,
              null);
      for (Stock stock : this.listOfStocks) {
        if (model.getStockTicker(stock).equalsIgnoreCase(ticker.trim())
                & model.getStockPurchaseDate(stock).equalsIgnoreCase(date.trim())) {
          buyStatus = model.addStockToUninitializedPortfolio(stock, Integer.parseInt(quantity));
        }
      }
      if (buyStatus.statusCode < 0) {
        this.listOfStocks.add(model.createStock(ticker.trim(), Integer.parseInt(quantity),
                date.trim()));
      }
      this.createPortfolioView.addRowToTable(new String[]{ticker.trim(), quantity, date.trim()});
    } catch (IllegalArgumentException e) {
      this.createPortfolioView.setFailureOutput("Quantity should be a positive integer");
    } catch (Exception e) {
      this.createPortfolioView.setFailureOutput(e.getMessage());
    }
  }

  @Override
  public void setView(JView jView) {
    this.portfolioMenuView = jView.getPortfolioMenuView();
    this.createPortfolioView = jView.getCreatePortfolioView();
    this.createPortfolioView.addFeatures(this);
  }

  @Override
  public void monitorTable(List<String> weightsColumn) {
    double totalSum = 0;
    for (String weight : weightsColumn) {
      totalSum += Double.parseDouble(weight);
    }
    if (totalSum != 0 & totalSum != 100) {
      this.createPortfolioView.dollarCostAveragingEnabled(true);
      this.createPortfolioView.enableCreatePortfolioButton(false);
      this.createPortfolioView.setLogOutput("Sum of the weights should be 100");
    }
    if (totalSum == 0.0 | totalSum == 100) {
      this.createPortfolioView.enableCreatePortfolioButton(true);
    }
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void resetStockList() {
    this.listOfStocks = new ArrayList<>();
  }

  @Override
  public void addStockForDCA(String ticker) {
    if (model.validateTicker(ticker.trim())) {
      this.createPortfolioView.addRowToTable(new String[]{ticker.trim().toLowerCase(), "", ""});
    } else {
      this.createPortfolioView.setFailureOutput("Ticker is not valid");
    }
  }
}
