package model.builder;

import java.io.FileNotFoundException;
import java.util.Map;

import model.Stock;
import model.StockImpl;
import utils.Utils;

/**
 * A StockBuilder class that contains methods that assist in creating the
 * stock and fetching required information.
 */
public class StockBuilder {
  private String ticker;
  private String stockName;
  private double stockQuantity = 0;
  private String date;

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public double getStockQuantity() {
    return stockQuantity;
  }

  public void setStockQuantity(double stockQuantity) {
    this.stockQuantity = stockQuantity;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  /**
   * Processes the stock information to build stock object.
   *
   * @return returns an object of type Stock.
   * @throws FileNotFoundException in case any of the arguments inserted is missing.
   */

  public Stock build(boolean validate) throws FileNotFoundException {
    Map<String, String> config = Utils.getConfigAndProgramState();
    if (this.ticker != null & this.stockQuantity > 0) {
      return new StockImpl(this.ticker, this.stockQuantity, this.date, config, validate);
    } else {
      throw new IllegalArgumentException("One of the parameters required for "
              + "Stock have not been set");
    }
  }
}
