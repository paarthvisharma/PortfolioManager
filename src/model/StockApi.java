package model;

import java.io.IOException;

/**
 * This is a generic interface for API that will be used to fetch the stock data.
 */
public interface StockApi {
  void fetchAndCreateStockData(String ticker);

  void updateListedStocks() throws IOException;
}
