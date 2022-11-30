package model.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.FlexiblePortfolio;
import model.RigidPortfolio;
import model.Stock;
import model.User;
import model.builder.FlexiblePortfolioBuilder;
import model.builder.RigidPortfolioBuilder;
import model.builder.StockBuilder;
import model.builder.UserBuilder;
import utils.Utils;

/**
 * A UserHandler class which is used to load the already present
 * XML files into the StockProgram. The user can load a portfolio directly and
 * this operation is processed by methods provided in this class.
 */
public class UserHandler extends DefaultHandler {

  Map<String, String> config = Utils.getConfigAndProgramState();

  private List<User> userList = null;

  private int userId;
  private int portfolioId;
  private double commission;
  private UserBuilder userBuilder = null;
  private List<RigidPortfolio> rigidPortfolioList = null;
  private RigidPortfolioBuilder rigidPortfolioBuilder = null;

  private List<FlexiblePortfolio> flexiblePortfolioList = null;
  private List<String> dcaPlans = null;
  private FlexiblePortfolioBuilder flexiblePortfolioBuilder = null;

  private List<Stock> stockList = null;
  private StockBuilder stockBuilder = null;
  private StringBuilder data = null;

  public List<User> getUserList() {
    return this.userList;
  }

  private boolean inRigidPortfolioList = false;

  boolean bFirstName = false;
  boolean bLastName = false;
  boolean bPortfolioName = false;

  boolean bStockName = false;
  boolean bStockQuantity = false;
  boolean bDate = false;
  boolean bDcaPlan = false;

  boolean bCommission = false;

  boolean validate = true;

  public UserHandler(boolean validate) {
    this.validate = validate;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
          throws SAXException {
    if (qName.equalsIgnoreCase("user")) {
      String id = attributes.getValue("userId");
      userBuilder = new UserBuilder();
      this.userId = Integer.parseInt(id);
      userBuilder.setUserId(userId);
      if (userList == null) {
        userList = new ArrayList<>();
      }
    } else if (qName.equalsIgnoreCase("rigidPortfolio")) {
      inRigidPortfolioList = true;
      String id = attributes.getValue("portfolioId");
      rigidPortfolioBuilder = new RigidPortfolioBuilder();
      rigidPortfolioBuilder.setPortfolioId(Integer.parseInt(id));
      if (rigidPortfolioList == null) {
        rigidPortfolioList = new ArrayList<>();
      }
    } else if (qName.equalsIgnoreCase("flexiblePortfolio")) {
      inRigidPortfolioList = false;
      String id = attributes.getValue("portfolioId");
      flexiblePortfolioBuilder = new FlexiblePortfolioBuilder();
      this.portfolioId = Integer.parseInt(id);
      flexiblePortfolioBuilder.setPortfolioId(portfolioId);
      if (flexiblePortfolioList == null) {
        flexiblePortfolioList = new ArrayList<>();
      }
      dcaPlans = new ArrayList<>();
    } else if (qName.equalsIgnoreCase("stock")) {
      String ticker = attributes.getValue("ticker");
      stockBuilder = new StockBuilder();
      stockBuilder.setTicker(ticker);
      if (stockList == null) {
        stockList = new ArrayList<>();
      }
    } else if (qName.equalsIgnoreCase("plans")) {
      dcaPlans = new ArrayList<>();
    } else if (qName.equalsIgnoreCase("dcaPlan")) {
      bDcaPlan = true;
    } else if (qName.equalsIgnoreCase("stocks")) {
      stockList = new ArrayList<>();
    } else if (qName.equalsIgnoreCase("rigidPortfolios")) {
      rigidPortfolioList = new ArrayList<>();
    } else if (qName.equalsIgnoreCase("flexiblePortfolios")) {
      flexiblePortfolioList = new ArrayList<>();
    } else if (qName.equalsIgnoreCase("portfolioName")) {
      bPortfolioName = true;
    } else if (qName.equalsIgnoreCase("stockName")) {
      bStockName = true;
    } else if (qName.equalsIgnoreCase("stockQuantity")) {
      bStockQuantity = true;
    } else if (qName.equalsIgnoreCase("commission")) {
      bCommission = true;
    } else if (qName.equalsIgnoreCase("date")) {
      if (inRigidPortfolioList) {
        throw new SAXException("Date cannot be present inside Rigid Portfolios");
      }
      bDate = true;
    } else if (qName.equalsIgnoreCase("firstName")) {
      bFirstName = true;
    } else if (qName.equalsIgnoreCase("lastName")) {
      bLastName = true;
    }
    data = new StringBuilder();
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (bPortfolioName) {
      if (inRigidPortfolioList) {
        rigidPortfolioBuilder.setPortfolioName(data.toString());
      } else {
        flexiblePortfolioBuilder.setPortfolioName(data.toString());
      }
      bPortfolioName = false;
    } else if (bStockName) {
      stockBuilder.setStockName(data.toString());
      bStockName = false;
    } else if (bStockQuantity) {
      stockBuilder.setStockQuantity(Double.parseDouble(data.toString()));
      bStockQuantity = false;
    } else if (bDate) {
      if (inRigidPortfolioList) {
        throw new SAXException("Date cannot be present inside Rigid Portfolios");
      }
      stockBuilder.setDate(data.toString());
      bDate = false;
    } else if (bFirstName) {
      userBuilder.setFirstName(data.toString());
      bFirstName = false;
    } else if (bLastName) {
      userBuilder.setLastName(data.toString());
      bLastName = false;
    } else if (bCommission) {
      commission = Double.parseDouble(data.toString());
      userBuilder.setCommission(commission);
      bCommission = false;
    } else if (bDcaPlan) {
      dcaPlans.add(data.toString());
      bDcaPlan = false;
    }
    if (qName.equalsIgnoreCase("plans")) {
      flexiblePortfolioBuilder.setDcaPlansAsString(dcaPlans);
    }
    if (qName.equalsIgnoreCase("stock")) {
      try {
        stockList.add(stockBuilder.build(this.validate));
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    if (qName.equalsIgnoreCase("stocks")) {
      if (inRigidPortfolioList) {
        rigidPortfolioBuilder.setListOfStocks(stockList);
      } else {
        flexiblePortfolioBuilder.setListOfStocks(stockList);
      }
    }
    if (qName.equalsIgnoreCase("rigidPortfolios")) {
      userBuilder.setListOfRigidPortfolios(rigidPortfolioList);
    }
    if (qName.equalsIgnoreCase("rigidPortfolio")) {
      rigidPortfolioList.add(rigidPortfolioBuilder.build());
      inRigidPortfolioList = false;
    }
    if (qName.equalsIgnoreCase("flexiblePortfolios")) {
      userBuilder.setListOfFlexiblePortfolios(flexiblePortfolioList);
    }
    if (qName.equalsIgnoreCase("flexiblePortfolio")) {
      String path = config.get("userData") + "/" + this.userId + "/" + this.portfolioId + ".csv";
      flexiblePortfolioBuilder.setPortfolioPath(path);
      flexiblePortfolioBuilder.setCommission(this.commission);
      flexiblePortfolioList.add(flexiblePortfolioBuilder.build());
    }
    if (qName.equalsIgnoreCase("user")) {
      userList.add(userBuilder.build());
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    data.append(new String(ch, start, length));
  }
}
