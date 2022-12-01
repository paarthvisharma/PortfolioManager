package model.utils;

import java.util.ArrayList;
import java.util.List;

import static model.utils.Utils.validateForLegalDate;

/**
 * Class to incorporate the dollar cost averaging feature into the stock program.
 */
public class DollarCostAveraging {

  private String startDate;
  private String endDate;
  private String lastTransaction;
  private int interval;
  private double dollarAmount;
  private double commission;
  private List<List<String>> dcaData;

  public DollarCostAveraging(String startDate, String endDate, String interval,
                             String dollarAmount, String commission,
                             List<List<String>> dcaData, String lastTransaction) {

    validateForLegalDate(startDate);
    this.startDate = startDate;
    validateForLegalDate(endDate);
    this.endDate = endDate;
    this.interval = Integer.parseInt(interval);
    this.dollarAmount = Double.parseDouble(dollarAmount);
    this.commission = Double.parseDouble(commission);
    this.dcaData = dcaData;
    this.lastTransaction = lastTransaction;
  }

  public DollarCostAveraging(String plan) {
    String[] splitPlan = plan.split(",");
    validateForLegalDate(splitPlan[0]);
    this.startDate = splitPlan[0];
    validateForLegalDate(splitPlan[1]);
    this.endDate = splitPlan[1];
    this.interval = Integer.parseInt(splitPlan[2]);
    this.dollarAmount = Double.parseDouble(splitPlan[3]);
    this.commission = Double.parseDouble(splitPlan[4]);
    this.dcaData = new ArrayList<>();
    for (String stockData : splitPlan[5].substring(
            1, splitPlan[5].length() - 1).split("\\|\\|")) {
      List<String> individualStock = new ArrayList<>();
      individualStock.add(stockData.split("\\|")[0]);
      individualStock.add(stockData.split("\\|")[1]);
      this.dcaData.add(individualStock);
    }
    this.lastTransaction = splitPlan[6];
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public int getInterval() {
    return interval;
  }

  public double getDollarAmount() {
    return dollarAmount;
  }

  public List<List<String>> getDcaData() {
    return dcaData;
  }

  public double getCommission() {
    return commission;
  }

  public String getLastTransaction() {
    return lastTransaction;
  }

  public void setLastTransaction(String lastTransaction) {
    this.lastTransaction = lastTransaction;
  }

  @Override
  public String toString() {
    StringBuilder toReturn = new StringBuilder();
    toReturn.append(startDate).append(",");
    toReturn.append(endDate).append(",");
    toReturn.append(interval).append(",");
    toReturn.append(dollarAmount).append(",");
    toReturn.append(commission).append(",");
    for (List<String> stockDetails : dcaData) {
      toReturn.append("|").append(stockDetails.get(0)).append("|");
      toReturn.append(stockDetails.get(3)).append("|");
    }
    toReturn.append(",").append(lastTransaction);
    return toReturn.toString();
  }


}
