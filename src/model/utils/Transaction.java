package model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The transaction class is an object that stores a transaction made in flexible portfolio.
 */

public class Transaction implements Comparable<Transaction> {

  private String ticker;
  private double quantity;
  private String type;
  private String date;
  private double commission;
  private boolean completed;

  /**
   * Constructor for Transaction class. The constructor takes the following parameters to create
   * an instance of the Transaction class.
   *
   * @param transaction string of transaction.
   * @param completed   boolean status value.
   */
  public Transaction(String[] transaction, boolean completed) {
    this.ticker = transaction[0].trim();
    this.quantity = Double.parseDouble(transaction[1].trim());
    this.type = transaction[2].trim();
    this.date = transaction[3].trim();
    this.commission = Double.parseDouble(transaction[4].trim());
    this.completed = completed;
  }

  @Override
  public String toString() {
    return this.ticker + " " + this.quantity + " " + this.type + " " + this.date
            + " " + this.commission + " " + completed;
  }

  public String toCSV() {
    return this.ticker + ", " + this.quantity + ", " + this.type + ", " + this.date
            + ", " + this.commission + ", " + completed;
  }

  @Override
  public int compareTo(Transaction o) {
    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return dateParser.parse(this.date).compareTo(dateParser.parse(o.date));
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Getter method to fetch ticker.
   * @return ticker.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Setter method to set ticker.
   * @param ticker stock ticker.
   */
  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  /**
   * Getter method to fetch stock quantity.
   * @return quantity.
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * Setter method to set quantity of stock.
   * @param quantity quantity of stock.
   */
  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  /**
   * Getter method to fetch type of transaction.
   * @return type of transaction.
   */
  public String getType() {
    return type;
  }

  /**
   * Setter method to set transaction type.
   * @param type transaction type.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Getter method to fetch date of transaction.
   * @return date of transaction.
   */
  public String getDate() {
    return date;
  }

  /**
   * Setter method to set date of transaction.
   * @param date date of transaction.
   */
  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Getter method to fetch commission on transaction.
   * @return commission.
   */
  public double getCommission() {
    return commission;
  }

  /**
   * Setter method to set commission for transaction.
   * @param commission commission.
   */
  public void setCommission(double commission) {
    this.commission = commission;
  }

  /**
   * Getter method to fetch status of transaction.
   * @return status.
   */
  public boolean isCompleted() {
    return completed;
  }

  /**
   * Setter method to set status of the transaction.
   * @param completed status.
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }
}