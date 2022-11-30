package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.utils.XmlFormat;

/**
 * This class is an implementation of the User Interface.
 * The methods associated with the class help perform operations on the User objects
 * such as getting user id, name, create and add portfolio.
 */

public class UserImpl implements User {
  private final int userId;
  private final String firstName;
  private final String lastName;
  private final List<RigidPortfolio> listOfRigidPortfolios;
  private final List<FlexiblePortfolio> listOfFlexiblePortfolios;
  private int rigidPortfolioId = 1;
  private int flexiblePortfolioId = 1;
  private double commission;

  /**
   * This is the constructor for UserImpl. An Instance of UserImpl is created
   * with the help of this constructor.
   * It creates a UserImpl by taking the following parameters.
   *
   * @param firstName firstName associated with a user.
   * @param lastName  lastName associated with a user.
   * @param userId    userId associated with a user.
   */

  public UserImpl(String firstName, String lastName, int userId, double commission) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userId = userId;
    this.commission = commission;
    this.listOfRigidPortfolios = new ArrayList<RigidPortfolio>();
    this.listOfFlexiblePortfolios = new ArrayList<FlexiblePortfolio>();
  }


  @Override
  public double getCommission() {
    return this.commission;
  }

  @Override
  public void setCommission(double commission) throws IllegalArgumentException {
    if (commission < 0) {
      throw new IllegalArgumentException("Commission cannot be negative");
    }
    this.commission = commission;
    for (FlexiblePortfolio portfolio : this.listOfFlexiblePortfolios) {
      portfolio.setCommission(this.commission);
    }
  }

  @Override
  public int getUserId() {
    return userId;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public int getNextFlexiblePortfolioId() {
    return this.flexiblePortfolioId;
  }

  @Override
  public void addToListOfRigidPortfolios(RigidPortfolio portfolio) {
    this.listOfRigidPortfolios.add(portfolio);
    this.rigidPortfolioId += 1;
  }

  @Override
  public void addToListOfFlexiblePortfolios(FlexiblePortfolio portfolio) {
    this.listOfFlexiblePortfolios.add(portfolio);
    this.flexiblePortfolioId += 1;
  }

  @Override
  public List<RigidPortfolio> getListOfRigidPortfolios() {
    return listOfRigidPortfolios;
  }

  @Override
  public List<FlexiblePortfolio> getListOfFlexiblePortfolios() {
    return listOfFlexiblePortfolios;
  }


  @Override
  public RigidPortfolio createRigidPortfolio(String portfolioName, List<Stock> listOfStocks) {
    RigidPortfolio portfolioToBeCreated = null;
    //    this.rigidPortfolioId += 1;
    portfolioToBeCreated = new RigidPortfolioImpl(portfolioName, this.rigidPortfolioId,
            listOfStocks);
    listOfRigidPortfolios.add(portfolioToBeCreated);
    return portfolioToBeCreated;
  }

  @Override
  public RigidPortfolio getRigidPortfolio(int portfolioId) {
    for (RigidPortfolio portfolio : this.listOfRigidPortfolios) {
      if (portfolio.getPortfolioId() == portfolioId) {
        return portfolio;
      }
    }
    return null;
  }

  //  @Override
  //  public FlexiblePortfolio createFlexiblePortfolio(String portfolioName, List<Stock>
  //                                                   listOfStocks,
  //                                                   String portfolioPath) {
  //    FlexiblePortfolio portfolioToBeCreated = null;
  //    this.flexiblePortfolioId += 1;
  //    portfolioToBeCreated = new FlexiblePortfolioImpl(portfolioName, this.flexiblePortfolioId,
  //            listOfStocks, this.commission, portfolioPath);
  //    listOfFlexiblePortfolios.add(portfolioToBeCreated);
  //    return portfolioToBeCreated;
  //  }
  @Override
  public FlexiblePortfolio createFlexiblePortfolio(String portfolioName, List<Stock> listOfStocks,
                                                   String portfolioPath) {
    FlexiblePortfolio portfolioToBeCreated = null;
    //    this.flexiblePortfolioId += 1;
    //    portfolioToBeCreated = new FlexiblePortfolioImpl(portfolioName, this.flexiblePortfolioId,
    //            listOfStocks, this.commission, portfolioPath);
    portfolioToBeCreated = new FlexiblePortfolioImpl(portfolioName, this.flexiblePortfolioId,
            new ArrayList<Stock>(), this.commission, portfolioPath);
    try {
      for (Stock stock: listOfStocks) {
        portfolioToBeCreated.buyStock(stock.getTicker(), stock.getDate(), stock.getStockQuantity());
      }
    } catch (IOException e) {
      return null;
    }
    listOfFlexiblePortfolios.add(portfolioToBeCreated);
    return portfolioToBeCreated;
  }

  @Override
  public FlexiblePortfolio getFlexiblePortfolio(int portfolioId) {
    for (FlexiblePortfolio portfolio : this.listOfFlexiblePortfolios) {
      if (portfolio.getPortfolioId() == portfolioId) {
        return portfolio;
      }
    }
    return null;
  }

  @Override
  public String xml() {
    int tabs = 0;
    StringBuilder toReturn = new StringBuilder();
    toReturn.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
    toReturn.append("<user userId=\"").append(this.getUserId()).append("\">\n");
    tabs += 1;
    toReturn.append("\t".repeat(tabs)).append("<firstName>").append(this.getFirstName())
            .append("</firstName>\n");
    toReturn.append("\t".repeat(tabs)).append("<lastName>").append(this.getLastName())
            .append("</lastName>\n");
    toReturn.append("\t".repeat(tabs)).append("<commission>").append(this.getCommission())
            .append("</commission>\n");
    toReturn.append("\t".repeat(tabs)).append("<rigidPortfolios>\n");
    tabs += 1;
    for (RigidPortfolio portfolio : this.listOfRigidPortfolios) {
      XmlFormat stockXml = portfolio.xml(tabs);
      toReturn.append(stockXml.xml);
      tabs = stockXml.tabs;
    }
    tabs -= 1;
    toReturn.append("\t".repeat(tabs)).append("</rigidPortfolios>\n");

    toReturn.append("\t".repeat(tabs)).append("<flexiblePortfolios>\n");
    tabs += 1;
    for (FlexiblePortfolio portfolio : this.listOfFlexiblePortfolios) {
      XmlFormat stockXml = portfolio.xml(tabs);
      toReturn.append(stockXml.xml);
      tabs = stockXml.tabs;
    }
    tabs -= 1;
    toReturn.append("\t".repeat(tabs)).append("</flexiblePortfolios>\n");

    tabs -= 1;
    toReturn.append("\t".repeat(tabs)).append("</user>\n");
    return toReturn.toString();
  }

  @Override
  public String toString() {
    StringBuilder toReturn = new StringBuilder();
    toReturn.append("First Name: ").append(this.firstName).append("\n");
    toReturn.append("Last Name: ").append(this.lastName).append("\n");
    toReturn.append("List of Rigid Portfolios:\n");
    for (Portfolio portfolio: this.listOfRigidPortfolios) {
      toReturn.append(portfolio.toString()).append("\n");
    }
    toReturn.append("List of Flexible Portfolios:\n");
    for (Portfolio portfolio: this.listOfFlexiblePortfolios) {
      toReturn.append(portfolio.toString()).append("\n");
    }
    return toReturn.toString();
  }

}
