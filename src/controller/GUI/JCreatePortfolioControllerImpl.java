package controller.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.FlexiblePortfolio;
import model.Model;
import model.Stock;
import model.User;
import model.utils.StatusObject;
import view.GUI.JCreatePortfolioView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;

public class JCreatePortfolioControllerImpl implements JCreatePortfolioController {

  private Model model;
  private JView view;
  private JCreatePortfolioView createPortfolioView;
  private JPortfolioMenuView portfolioMenuView;
  private User user;

  private List<Stock> listOfStocks;

  public JCreatePortfolioControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.createPortfolioView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }

  @Override
  public void createPortfolio(String portfolioName, Map<String, String> dcaSetting, List<List<String>> tableData) {
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

  private void processDCACreation(FlexiblePortfolio portfolio, Map<String, String> dcaSetting, List<List<String>> tableData) {
    try {
      if (dcaSetting.get("startDate").equals("")) {
        throw new IllegalArgumentException("Start date cannot be empty");
      }
      if (dcaSetting.get("endDate").equals("")) {
        dcaSetting.put("endDate", "3000-01-01");
      }
      if (dcaSetting.get("startDate").equals("")) {
        throw new IllegalArgumentException("Start date cannot be empty");
      }
      if (dcaSetting.get("interval").equals("") | Integer.parseInt(dcaSetting.get("interval")) < 1) {
        throw new IllegalArgumentException("Interval has to be a positive integer");
      }
      if (dcaSetting.get("commission").equals("") | Double.parseDouble(dcaSetting.get("commission")) < 0) {
        throw new IllegalArgumentException("Commission has to be greater/equal to 0");
      }
      if (dcaSetting.get("dollarAmount").equals("") | Double.parseDouble(dcaSetting.get("dollarAmount")) < 0) {
        throw new IllegalArgumentException("Dollar amount has to be greater/equal to 0");
      }
      StatusObject<String> status = model.createDCAPlan(portfolio, dcaSetting.get("startDate"), dcaSetting.get("endDate"), dcaSetting.get("interval"),
              dcaSetting.get("dollarAmount"), dcaSetting.get("commission"), tableData);
      if (status.statusCode < 0) {
        throw new RuntimeException(status.statusMessage);
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Entered number does not follow the correct format\n"
              + "Interval -> Positive integer\n"
              + "Dollar amount -> Positive number\n"
              + "Commission -> Positive number");
    }
  }

//  private List<Stock> createStockListFlexiblePortfolio(String[][] stockList) {
//    List<Stock> listOfStocks = new ArrayList<>();
//    String ticker;
//    String date;
//    int quantity;
//    for (String[] stockDetails : stockList) {
//      ticker = stockDetails[0];
//      quantity = Integer.parseInt(stockDetails[1]);
//      date = stockDetails[2];
//      StatusObject<Stock> buyStatus = new StatusObject<Stock>("", -1, null);
//      for (Stock stock : listOfStocks) {
//        if (model.getStockTicker(stock).equalsIgnoreCase(ticker)
//                & model.getStockPurchaseDate(stock).equalsIgnoreCase(date)) {
//          buyStatus = model.addStockToUninitializedPortfolio(stock, quantity);
//        }
//      }
//      if (buyStatus.statusCode < 0) {
//        listOfStocks.add(model.createStock(ticker, quantity, date));
//      }
//    }
//    return listOfStocks;
//  }

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
      StatusObject<Stock> buyStatus = new StatusObject<Stock>("", -1, null);
      for (Stock stock : this.listOfStocks) {
        if (model.getStockTicker(stock).equalsIgnoreCase(ticker.trim())
                & model.getStockPurchaseDate(stock).equalsIgnoreCase(date.trim())) {
          buyStatus = model.addStockToUninitializedPortfolio(stock, Integer.parseInt(quantity));
        }
      }
      if (buyStatus.statusCode < 0) {
        this.listOfStocks.add(model.createStock(ticker.trim(), Integer.parseInt(quantity), date.trim()));
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
    this.view = jView;
    this.portfolioMenuView = this.view.getPortfolioMenuView();
    this.createPortfolioView = this.view.getCreatePortfolioView();
    this.createPortfolioView.addFeatures(this);
  }

  @Override
  public void monitorTable(List<String> weightsColumn) {
    double totalSum = 0;
    for (String weight: weightsColumn) {
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
