package controller.GUI;

import java.util.List;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.GUI.JPortfolioMenuView;
import view.GUI.JTransactionView;
import view.GUI.JView;

import static controller.Utils.getPresentDate;

public class JTransactionControllerImpl implements JTransactionController {

  private Model model;
  private JView view;
  private User user;
  private JTransactionView transactionView;
  private JPortfolioMenuView portfolioMenuView;
  private FlexiblePortfolio selectedPortfolio;

  public JTransactionControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
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
  public void createDCAPlan(String startDate, String endDate, String interval, String dollarAmount, String commission) {
    System.out.println(startDate + " " + endDate + " " + interval + " " + dollarAmount + " " + commission);
    this.transactionView.setLogOutput(startDate + " " + endDate + " " + interval + " " + dollarAmount + " " + commission);
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
      StatusObject<FlexiblePortfolio> status = model.getParticularFlexiblePortfolio(user, portfolioId);
      if (status.statusCode < 0) {
        this.transactionView.setFailureOutput(status.statusMessage);
      }
      selectedPortfolio = status.returnedObject;
      StatusObject<List<List<String>>> portfolioDetails =
              model.getCompositionOfFlexiblePortfolioAsList(user, selectedPortfolio, getPresentDate());
      if (portfolioDetails.statusCode < 0) {
        this.transactionView.setFailureOutput(portfolioDetails.statusMessage);
        return;
      }
      for (List<String> stock: portfolioDetails.returnedObject) {
        this.transactionView.addRowToDCATable(new String[]{stock.get(0), stock.get(2), stock.get(3), "0"});
      }
      this.transactionView.setSuccessOutput("Successfully selected " + selectedPortfolio.getPortfolioId());
    } catch (NumberFormatException e) {
      this.transactionView.setFailureOutput("Portfolio ID is not an integer");
    }
  }

  @Override
  public void back() {
    this.transactionView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }
}
