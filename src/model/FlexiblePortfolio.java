package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.PriorityQueue;

import model.utils.DollarCostAveraging;
import model.utils.StatusObject;
import model.utils.Transaction;

/**
 * FlexiblePortfolio interface extends portfolio. This portfolio
 * supports buying or selling stocks once it's created.
 */
public interface FlexiblePortfolio extends Portfolio {
  void addDCAPlan(DollarCostAveraging dca);

  List<DollarCostAveraging> getDCAPlans();

  void executeDCAPlan(DollarCostAveraging dca);

  void setCommission(double commission);

  double getCommission(double commission);

  PriorityQueue<Transaction> getPreviousTransactions() throws FileNotFoundException;

  StatusObject<PriorityQueue<Transaction>> getValidatedTransactions(
          PriorityQueue<Transaction> transactions);

  String getPathToPortfolioTransactions();

  void buyStock(String ticker, String date, double quantity) throws IOException;

  void sellStock(String ticker, String date, double quantity) throws ParseException, IOException;

  PriorityQueue<Transaction> loadPastTransactions(User user) throws FileNotFoundException;

  List<Stock> getCompositionOfFlexiblePortfolio(String date)
          throws ParseException, FileNotFoundException;

  List<String[]> getCSVUptoDate(String date) throws FileNotFoundException, ParseException;
}
