package model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The transaction class is an object that stores a transaction made in flexible portfolio.
 */

public class Transaction implements Comparable<Transaction> {

  public String ticker;
  public double quantity;
  public String type;
  public String date;
  public double commission;
  public boolean completed;

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
}