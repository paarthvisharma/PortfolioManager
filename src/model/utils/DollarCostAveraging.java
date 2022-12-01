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

  /**
   * Constructor that takes in the following parameters and creates an instance.
   *
   * @param startDate       start date string.
   * @param endDate         end date string.
   * @param interval        time interval.
   * @param dollarAmount    total amount to be invested.
   * @param commission      amount.
   * @param dcaData         data string.
   * @param lastTransaction last transaction string.
   */
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

  /**
   * Constructor that takes in the plan and further processes it.
   *
   * @param plan string value.
   */
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

  /**
   * Method to get the start date of DCA plan.
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * Method to get the end date of DCA plan.
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * Method to get the interval of DCA plan.
   */
  public int getInterval() {
    return interval;
  }

  /**
   * Method to get the dollar amount of DCA plan.
   */
  public double getDollarAmount() {
    return dollarAmount;
  }

  /**
   * Method to get the DCA data to create the DCA plan.
   */
  public List<List<String>> getDcaData() {
    return dcaData;
  }

  /**
   * Method to get the commission for DCA plan.
   */
  public double getCommission() {
    return commission;
  }

  /**
   * Method to get the last transaction of DCA plan.
   */
  public String getLastTransaction() {
    return lastTransaction;
  }

  /**
   * Method to set the last transaction of DCA plan.
   */
  public void setLastTransaction(String lastTransaction) {
    this.lastTransaction = lastTransaction;
  }

  /**
   * Method to override the toString method.
   */
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
