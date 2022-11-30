package controller.gui;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.FlexiblePortfolio;
import model.Model;
import model.User;
import model.utils.StatusObject;
import view.gui.JPerformanceView;
import view.gui.JPortfolioMenuView;
import view.gui.JView;
import view.gui.PerformanceGraph;

import static controller.Utils.getPresentDate;

public class JPerformanceControllerImpl implements JPerformanceController {
  private Model model;
  private User user;
  private JPortfolioMenuView jPortfolioMenuView;
  private JPerformanceView jPerformanceView;

  private FlexiblePortfolio selectedPortfolio;

  public JPerformanceControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void setView(JView jView) {
    this.jPerformanceView = jView.getPerformanceView();
    this.jPortfolioMenuView = jView.getPortfolioMenuView();
    this.jPerformanceView.addFeatures(this);
  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public void selectPortfolio(String portfolioIdAndName) {
    if (portfolioIdAndName == null) {
      this.jPerformanceView.setFailureOutput("Select one of the portfolio from the list shown");
      return;
    }
    try {
      int portfolioId = Integer.parseInt(portfolioIdAndName.split("\\s")[0]);
      StatusObject<FlexiblePortfolio> status =
              model.getParticularFlexiblePortfolio(user, portfolioId);
      if (status.statusCode < 0) {
        this.jPerformanceView.setFailureOutput(status.statusMessage);
      }
      selectedPortfolio = status.returnedObject;
      StatusObject<List<List<String>>> portfolioDetails =
              model.getCompositionOfFlexiblePortfolioAsList(user,
                      selectedPortfolio, getPresentDate());
      if (portfolioDetails.statusCode < 0) {
        this.jPerformanceView.setFailureOutput(portfolioDetails.statusMessage);
        return;
      }
      this.jPerformanceView.setSuccessOutput("Successfully selected "
              + selectedPortfolio.getPortfolioId());
    } catch (NumberFormatException e) {
      this.jPerformanceView.setFailureOutput("Portfolio ID is not an integer");
    }
  }

  @Override
  public void back() {
    this.jPerformanceView.isVisible(false);
    this.jPortfolioMenuView.isVisible(true);
  }

  @Override
  public void plotGraph(String startDate, String endDate) {
    try {
      StatusObject<List<String>> datesList = model.getDatesForPerformanceGraph(startDate, endDate, false);
      if (datesList.statusCode < 1) {
        this.jPerformanceView.setFailureOutput(datesList.statusMessage);
        return;
      }
      StatusObject<List<Double>> valuations = model.getValuationForDate(selectedPortfolio, datesList.returnedObject);
      if (valuations.statusCode < 1) {
        this.jPerformanceView.setFailureOutput(valuations.statusMessage);
        return;
      }
      double mini = Collections.min(valuations.returnedObject);
      List<Double> filteredValuation = valuations.returnedObject.stream().map(i -> i - mini).collect(Collectors.toList());
      int[] xyCoordinates = new int[filteredValuation.size()];
      for (int i=0; i < filteredValuation.size(); i++) {
        xyCoordinates[i] = filteredValuation.get(i).intValue();
      }
      String[] dates = new String[datesList.returnedObject.size()];
      for (int i=0; i< datesList.returnedObject.size(); i++) {
        dates[i] = datesList.returnedObject.get(i);
      }
      List<String> yAxisValues = model.getDollarAxisForGraph(valuations.returnedObject, 10);
      String[] dollarAmounts = new String[yAxisValues.size()];
      for (int i=0; i< yAxisValues.size(); i++) {
        dollarAmounts[i] = yAxisValues.get(i);
      }
      System.out.println(valuations.returnedObject);
      System.out.println(Arrays.toString(xyCoordinates));
      System.out.println(Arrays.toString(dates));
      System.out.println(Arrays.toString(dollarAmounts));
      PerformanceGraph graphing = new PerformanceGraph(xyCoordinates, dates, dollarAmounts);
      graphing.plot();
    } catch (Exception e) {
      this.jPerformanceView.setFailureOutput(e.getMessage());
    }
  }
}
