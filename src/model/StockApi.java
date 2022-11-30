package model;

import java.io.IOException;

/**
 * This is a generic interface for API that will be used to fetch the stock data.
 */
public interface StockApi {
  /**
   * Method to fetch and populate stock data.
   *
   * @param ticker symbol of a company.
   */
  void fetchAndCreateStockData(String ticker);

  /**
   * Method to update the listed stocks.
   *
   * @throws IOException in case of issues with file.
   */
  void updateListedStocks() throws IOException;
}
