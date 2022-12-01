package controllertest;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

import model.FlexiblePortfolio;
import model.Model;
import model.ModelImpl;
import model.Portfolio;
import model.RigidPortfolio;
import model.Stock;
import model.User;
import model.utils.StatusObject;
import model.utils.Transaction;

/**
 * A mock model that will be used for controller testing.
 */
public class MockModel implements Model {

  StringBuilder log;
  Model testModel;

  public MockModel(StringBuilder log) {
    this.log = log;
    testModel = new ModelImpl();
  }


  @Override
  public boolean validateTicker(String ticker) {
    log.append(ticker).append("||");
    return testModel.validateTicker(ticker);
  }

  @Override
  public StatusObject<User> createUser(String firstName, String lastName, double commission) {
    log.append("FirstName: ").append(firstName).append(" LastName: ").append(lastName).append("||");
    return testModel.createUser(firstName, lastName, commission);
  }

  @Override
  public StatusObject<User> createUserFromXML(String xmlPath) {
    log.append(xmlPath).append("||");
    return testModel.createUserFromXML(xmlPath);
  }

  @Override
  public StatusObject<Portfolio> createPortfolioFromXML(User user, String xmlPath) {
    log.append(user.toString()).append("\n");
    log.append(xmlPath).append("||");
    return testModel.createPortfolioFromXML(user, xmlPath);
  }

  @Override
  public void updateUserFile(User user) throws IOException {
    log.append(user.toString()).append("||");
    testModel.updateUserFile(user);
  }

  @Override
  public StatusObject<User> getUser(String userId) {
    log.append(userId).append("||");
    return testModel.getUser(userId);
  }

  @Override
  public StatusObject<RigidPortfolio> createRigidPortfolio(User user, String portfolioName,
                                                           List<Stock> listOfStocks) {
    log.append(user.toString()).append("\n").append(portfolioName).append("\n").append("||");
    for (Stock stock : listOfStocks) {
      log.append(stock.stockAsString()).append("\n");
    }
    return testModel.createRigidPortfolio(user, portfolioName, listOfStocks);
  }

  @Override
  public List<RigidPortfolio> getRigidPortfoliosForUser(User user) {
    log.append(user.toString()).append("||");
    return testModel.getRigidPortfoliosForUser(user);
  }

  @Override
  public List<FlexiblePortfolio> getFlexiblePortfoliosForUser(User user) {
    log.append(user.toString()).append("||");
    return testModel.getFlexiblePortfoliosForUser(user);
  }

  @Override
  public StatusObject<RigidPortfolio> getParticularRigidPortfolio(User user, int portfolioId) {
    log.append(user.toString()).append("\n").append(portfolioId).append("||");
    return testModel.getParticularRigidPortfolio(user, portfolioId);
  }

  @Override
  public StatusObject<FlexiblePortfolio> getParticularFlexiblePortfolio(User user,
                                                                        int portfolioId) {
    log.append(user.toString()).append("\n").append(portfolioId).append("||");
    return testModel.getParticularFlexiblePortfolio(user, portfolioId);
  }

  @Override
  public StatusObject<String> createDCAPlan(
          FlexiblePortfolio portfolio, String startDate, String endDate, String interval,
          String dollarAmount, String commission, List<List<String>> dcaData) {
    log.append(portfolio.toString()).append("\n");
    log.append(startDate).append("\n");
    log.append(endDate).append("\n");
    log.append(interval).append("\n");
    log.append(dollarAmount).append("\n");
    log.append(commission).append("\n");
    log.append(dcaData).append("||");
    return testModel.createDCAPlan(portfolio, startDate, endDate, interval,
            dollarAmount, commission, dcaData);
  }

  @Override
  public int getPortfolioId(Portfolio portfolio) {
    log.append(portfolio.toString()).append("||");
    return testModel.getPortfolioId(portfolio);
  }

  @Override
  public String getPortfolioName(Portfolio portfolio) {
    log.append(portfolio.toString()).append("||");
    return testModel.getPortfolioName(portfolio);
  }

  @Override
  public String getPortfolioType(Portfolio portfolio) {
    log.append(portfolio.toString()).append("||");
    return testModel.getPortfolioType(portfolio);
  }

  @Override
  public StatusObject<FlexiblePortfolio> createFlexiblePortfolio(User user, String portfolioName,
                                                                 List<Stock> listOfStocks) {
    log.append(user.toString()).append("\n").append(portfolioName).append("\n");
    for (Stock stock : listOfStocks) {
      log.append(stock.toString()).append("\n");
    }
    log.append("||");
    return testModel.createFlexiblePortfolio(user, portfolioName, listOfStocks);
  }

  @Override
  public StatusObject<Double> getValueOfPortfolioForDate(Portfolio portfolio, String date) {
    log.append(portfolio.toString()).append("\n").append(date).append("||");
    return testModel.getValueOfPortfolioForDate(portfolio, date);
  }

  @Override
  public int getNextUserId() {
    return testModel.getNextUserId();
  }

  @Override
  public Stock createStock(String ticker, double quantity, String date) {
    log.append(ticker).append("\n").append(quantity).append("\n").append(date).append("||");
    return testModel.createStock(ticker, quantity, date);
  }

  @Override
  public String getStockTicker(Stock stock) {
    log.append(stock.toString()).append("||");
    return testModel.getStockTicker(stock);
  }

  @Override
  public StatusObject<Stock> addStockToUninitializedPortfolio(Stock stock, double quantity) {
    log.append(stock.toString()).append("\n").append(quantity).append("||");
    return testModel.addStockToUninitializedPortfolio(stock, quantity);
  }

  @Override
  public String getStockPurchaseDate(Stock stock) {
    log.append(stock.toString()).append("||");
    return testModel.getStockPurchaseDate(stock);
  }

  @Override
  public StatusObject<PriorityQueue<Transaction>> getValidatedTransactions(
          FlexiblePortfolio portfolio, PriorityQueue<Transaction> transactions) {
    log.append(portfolio.toString()).append("\n");
    PriorityQueue<Transaction> forLogging = new PriorityQueue<>(transactions);
    while (!forLogging.isEmpty()) {
      log.append(forLogging.poll().toString()).append("\n");
    }
    log.append("||");
    return testModel.getValidatedTransactions(portfolio, transactions);
  }

  @Override
  public StatusObject<FlexiblePortfolio> buyStockInFlexiblePortfolio(
          FlexiblePortfolio portfolio, String ticker, String date, double quantity) {
    log.append(portfolio.toString())
            .append("\n").append(ticker).append("\n").append(date).append("\n")
            .append(quantity).append("||");
    return testModel.buyStockInFlexiblePortfolio(portfolio, ticker, date, quantity);
  }

  @Override
  public StatusObject<FlexiblePortfolio> sellStockInFlexiblePortfolio(
          FlexiblePortfolio portfolio, String ticker, String date, double quantity) {
    log.append(portfolio.toString()).append("\n").append(ticker).append("\n")
            .append(date).append("\n").append(quantity).append("||");
    return testModel.sellStockInFlexiblePortfolio(portfolio, ticker, date, quantity);
  }

  @Override
  public StatusObject<PriorityQueue<Transaction>> getFlexiblePortfoliosPastTransactions(
          User user, FlexiblePortfolio portfolio) {
    log.append(user.toString()).append("\n");
    log.append(portfolio.toString()).append("\n").append("||");
    return testModel.getFlexiblePortfoliosPastTransactions(user, portfolio);
  }

  @Override
  public StatusObject<String> updateFlexiblePortfolioCsv(User user, FlexiblePortfolio portfolio) {
    log.append(user.toString()).append("\n");
    log.append(portfolio.toString()).append("\n").append("||");
    return testModel.updateFlexiblePortfolioCsv(user, portfolio);
  }

  @Override
  public StatusObject<String> viewCompositionOfFlexiblePortfolio(
          User user, FlexiblePortfolio portfolio, String date) {
    log.append(user.toString()).append("\n");
    log.append(portfolio.toString()).append("\n");
    log.append(date).append("||");
    return testModel.viewCompositionOfFlexiblePortfolio(user, portfolio, date);
  }

  @Override
  public StatusObject<List<List<String>>> getCompositionOfFlexiblePortfolioAsList(
          User user, FlexiblePortfolio portfolio, String date) {
    log.append(user.toString()).append("\n");
    log.append(portfolio.toString()).append("\n");
    log.append(date).append("||");
    return testModel.getCompositionOfFlexiblePortfolioAsList(user, portfolio, date);
  }

  @Override
  public StatusObject<Double> getCostBasisOfFlexiblePortfolioForDate(User user,
                                                                     FlexiblePortfolio portfolio,
                                                                     String date) {
    log.append(user.toString()).append("\n");
    log.append(portfolio.toString()).append("\n");
    log.append(date).append("||");
    return testModel.getCostBasisOfFlexiblePortfolioForDate(user, portfolio, date);
  }

  @Override
  public StatusObject<String> performanceOfPortfolioOverTime(User user, Portfolio portfolio,
                                                             String startDate, String endDate) {
    log.append(user.toString()).append("\n");
    log.append(portfolio.toString()).append("\n");
    log.append(startDate).append("\n");
    log.append(endDate).append("\n").append("||");
    return testModel.performanceOfPortfolioOverTime(user, portfolio, startDate, endDate);
  }

  @Override
  public StatusObject<List<String>> getDatesForPerformanceGraph(
          String startDate, String endDate, boolean includeRemainderDate) {
    log.append(startDate).append("\n");
    log.append(endDate).append("\n");
    log.append(includeRemainderDate).append("||");
    return testModel.getDatesForPerformanceGraph(startDate, endDate, includeRemainderDate);
  }

  @Override
  public List<String> getDollarAxisForGraph(List<Double> valuations, int length) {
    log.append(valuations.toString()).append("\n");
    log.append(length).append("||");
    return testModel.getDollarAxisForGraph(valuations, length);
  }

  @Override
  public StatusObject<List<Double>> getValuationForDate(Portfolio portfolio, List<String> dates) {
    log.append(portfolio.toString()).append("\n");
    log.append(dates).append("||");
    return testModel.getValuationForDate(portfolio, dates);
  }
}
