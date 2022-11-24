import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

import model.FlexiblePortfolio;
import model.Model;
import model.ModelImpl;
import model.Portfolio;
import model.PortfolioManager;
import model.RigidPortfolio;
import model.Stock;
import model.User;
import model.utils.StatusObject;
import model.utils.Transaction;


import static model.constants.DirectoryStructureConstants.CURRENT_DIRECTORY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static utils.Utils.getConfigAndProgramState;

/**
 * Tests to verify the working of Model.
 */

public class ModelTests {

  protected Model model;
  protected PortfolioManager portfolioManager;
  protected User user;
  protected RigidPortfolio rigidPortfolio;
  protected FlexiblePortfolio flexiblePortfolio;
  protected Stock stock;
  protected Map<String, String> config = getConfigAndProgramState();
  File userData = new File(config.get("userData"));


  User createUserWithNoPortfolios() throws IOException {
    StatusObject<User> temp = model.createUser("Paarthvi", "Sharma",
            1.0);
    model.updateUserFile(temp.returnedObject);
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }

  User createUserWithNoStocksInRigidPortfolio() throws IOException {
    StatusObject<User> temp = model.createUser("No", "Stocks", 1.0);
    model.createRigidPortfolio(temp.returnedObject, "NoStocks",
            new ArrayList<Stock>());
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }

  User createUserWithNoStocksInFlexiblePortfolio() throws IOException {
    StatusObject<User> temp = model.createUser("No", "Stocks", 1);
    model.createFlexiblePortfolio(temp.returnedObject, "NoStocks",
            new ArrayList<Stock>());
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }

  User createUserWith1RigidPortfoliosAndStocks() throws IOException {
    StatusObject<User> temp = model.createUser("One", "RigidPortfolio",
            1);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    stockList.add(model.createStock("goog", 100, null));
    stockList.add(model.createStock("aapl", 101, null));
    stockList.add(model.createStock("adi", 102, null));
    StatusObject<RigidPortfolio> tempPortfolio = model.createRigidPortfolio(temp.returnedObject,
            "onePortfolio",
            stockList);
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }

  User createUserWith1FlexiblePortfoliosAndStocks() throws IOException {
    StatusObject<User> temp = model.createUser("One", "FlexiblePortfolio",
            1.0);
    System.out.println(temp.statusMessage);
    model.updateUserFile(temp.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    stockList.add(model.createStock("goog", 100, "2020-01-01"));
    stockList.add(model.createStock("aapl", 101, "2020-01-02"));
    StatusObject<FlexiblePortfolio> tempPortfolio =
            model.createFlexiblePortfolio(temp.returnedObject,
                    "onePortfolio",
                    stockList);
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }

  User createUserWithUnlistedStock() throws IOException {
    StatusObject<User> temp = model.createUser("Unlisted", "stock", 1);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    stockList.add(model.createStock("orcl", 100, "2020-01-01"));
    StatusObject<RigidPortfolio> tempPortfolio = model.createRigidPortfolio(temp.returnedObject,
            "onePortfolio",
            stockList);
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }

  User createUserWithFlexiblePortfolioAndUnlistedStock() throws IOException {
    StatusObject<User> temp = model.createUser("Unlisted", "stock", 1);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    stockList.add(model.createStock("orcl", 100, "2020-01-01"));
    StatusObject<FlexiblePortfolio> tempPortfolio =
            model.createFlexiblePortfolio(temp.returnedObject,
                    "onePortfolio",
                    stockList);
    model.updateUserFile(temp.returnedObject);
    PortfolioManagerRunner.updateProgramState(model);
    return temp.returnedObject;
  }


  @Before
  public void initialSetUp() {
    model = new ModelImpl();
  }

  @After
  public void purgeDirectory() {
    File userData = new File(config.get("userData"));
    deleteDirectory(userData);
  }

  private void deleteDirectory(File directory) {
    for (File file : Objects.requireNonNull(directory.listFiles())) {
      if (file.isDirectory()) {
        deleteDirectory(file);
      }
      file.delete();
    }
  }

  @Test
  public void ValidateStockFunctionsWithValidInputs() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("goog", 10, "2022-05-02");

    assertEquals("goog", model.getStockTicker(stock));
    assertEquals("2022-05-02", model.getStockPurchaseDate(stock));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ValidateStockFunctionsWithInvalidDate() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("goog", 10, "-2022-05-02");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ValidateStockFunctionsWithInvalidTicker() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("orcl", 10, "2022-05-02");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ValidateStockFunctionsWithNegativeQuantity() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("goog", -10, "2022-05-02");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ValidateStockFunctionsWithFractionalQuantity() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("goog", 1.0 / 2, "2022-05-02");
  }

  @Test(expected = NullPointerException.class)
  public void ValidateStockFunctionsWithNullDate() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("goog", 1, null);
    assertEquals(null, stock.getDate().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ValidateStockFunctionsWithEmptyDate() throws IOException {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    model.updateUserFile(createdUser.returnedObject);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    Stock stock = model.createStock("goog", 1, " ");
  }

  @Test
  public void ValidateCreateUserMethodWithValidInputs() {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", 1.0);
    assertEquals(1, createdUser.statusCode);
    assertEquals("Created user successfully with UserId:1 with name as Paarthvi Sharma",
            createdUser.statusMessage);
    String toCheck = "First Name: Paarthvi\n"
            + "Last Name: Sharma\n"
            + "List of Rigid Portfolios:\n"
            + "List of Flexible Portfolios:\n";
    assertEquals(toCheck, createdUser.returnedObject.toString());
  }

  @Test
  public void ValidateCreateUserMethodWithInvalidCommission() {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "Sharma", -5);
    assertEquals(-1, createdUser.statusCode);
    assertEquals("User could not be created due to error: Commission cannot be "
                    + "negative, enter a positive number.",
            createdUser.statusMessage);
    assertEquals(null, createdUser.returnedObject);

  }

  @Test
  public void ValidateCreateUserMethodWithIntegerValues() {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "123", 1);
    assertEquals(1, createdUser.statusCode);
    assertEquals("Created user successfully with UserId:1 with name as Paarthvi 123",
            createdUser.statusMessage);
    String toCheck = "First Name: Paarthvi\n"
            + "Last Name: 123\n"
            + "List of Rigid Portfolios:\n"
            + "List of Flexible Portfolios:\n";
    assertEquals(toCheck, createdUser.returnedObject.toString());
  }

  @Test
  public void ValidateCreateUserMethodWithEmptyString() {
    StatusObject<User> createdUser = model.createUser("", "Sharma", 1);
    assertEquals(1, createdUser.statusCode);
    assertEquals("Created user successfully with UserId:1 with name as  Sharma",
            createdUser.statusMessage);
    String toAssert = "First Name: \n"
            + "Last Name: Sharma\n"
            + "List of Rigid Portfolios:\n"
            + "List of Flexible Portfolios:\n";
    assertEquals(toAssert, createdUser.returnedObject.toString());
  }

  @Test
  public void ValidateCreateUserMethodWithNull() {
    StatusObject<User> createdUser = model.createUser(null, null, 1);
    assertEquals(-1, createdUser.statusCode);
    assertEquals("User could not be created due to error: Null is not a valid input "
            + "for first/last name", createdUser.statusMessage);
    assertNull(createdUser.returnedObject);
  }

  @Test
  public void ValidateCreateUserMethodWithSpecialSymbols() {
    StatusObject<User> createdUser = model.createUser("Paarthvi",
            "05%", 1);
    assertEquals(1, createdUser.statusCode);
    assertEquals("Created user successfully with UserId:1 with name as Paarthvi 05%",
            createdUser.statusMessage);
    String toAssert = "First Name: Paarthvi\n" +
            "Last Name: 05%\n" +
            "List of Rigid Portfolios:\n" +
            "List of Flexible Portfolios:\n";
    assertEquals(toAssert, createdUser.returnedObject.toString());
  }

  @Test
  public void ValidateGetUserMethodWithValidUserID() throws IOException {
    StatusObject<User> temp = model.createUser("Paarthvi",
            "Sharma", 1);
    model.updateUserFile(temp.returnedObject);
    String currentDir = CURRENT_DIRECTORY;
    assertEquals("Retrieved user successfully", model.getUser("1").statusMessage);
    assertEquals(1, model.getUser("1").statusCode);
    String toAssert = "First Name: Paarthvi\n"
            + "Last Name: Sharma\n"
            + "List of Rigid Portfolios:\n"
            + "List of Flexible Portfolios:\n";
    assertEquals(toAssert, model.getUser("1").returnedObject.toString());
  }

  @Test
  public void ValidateGetUserMethodWithMissingFile() {
    assertEquals("User ID does not exist or there might be problems with "
            + "the config file", model.getUser("2").statusMessage);
    assertEquals(-1, model.getUser("2").statusCode);
    assertNull(model.getUser("2").returnedObject);
  }

  //getUser takes config file userData
  @Test
  public void ValidateGetUserMethodWithCorruptedFile() throws IOException {
    boolean created = new File("PDPResources/UserData/2").mkdirs();
    assertTrue(created);
    File fileName = new File("PDPResources/UserData/2/2.xml");
    created = fileName.createNewFile();
    assertTrue(created);
    assertEquals("XML file seems to be corrupted.",
            model.getUser("2").statusMessage);
    assertEquals(-1, model.getUser("2").statusCode);
    assertNull(model.getUser("2").returnedObject);
  }

  @Test
  public void ValidateCreateUserFromXMLMethodWithValidInputs() throws IOException {
    User tempUser = this.createUserWith1RigidPortfoliosAndStocks();
    File userData = new File(config.get("userData"));
    StatusObject<User> createdUser = model.createUserFromXML(config.get("userData")
            + "/1/1.xml");
    assertEquals("Successfully created the User Object with User ID 1",
            createdUser.statusMessage);
    assertEquals(1, createdUser.statusCode);
    String toAssert = "First Name: One\n"
            + "Last Name: RigidPortfolio\n"
            + "List of Rigid Portfolios:\n"
            + "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 100.0 \n"
            + "* aapl Apple Inc 101.0 \n"
            + "* adi Analog Devices Inc 102.0 \n"
            + "\n"
            + "\n"
            + "List of Flexible Portfolios:\n";
    assertEquals(toAssert, createdUser.returnedObject.toString());
  }

  @Test
  public void ValidateCreateUserFromXMLMethodWithCorruptedFile() throws IOException {
    File fileName = new File("PDPResources/TestResources/2/2.xml");
    fileName.createNewFile();
    StatusObject<User> createdUser =
            model.createUserFromXML("PDPResources/TestResources/2/2.xml");
    assertEquals("XML file seems to be corrupted.", createdUser.statusMessage);
    assertEquals(-1, createdUser.statusCode);
    assertNull(createdUser.returnedObject);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CreateInvalidStockForUser() throws IOException {
    this.createUserWithUnlistedStock();
  }


  @Test(expected = IllegalArgumentException.class)
  public void CreateStockInvalidDate() {
    StatusObject<User> temp = model.createUser("Unlisted", "stock", 1);
    ArrayList<Stock> stockList = new ArrayList<Stock>();
    stockList.add(model.createStock("goog", 100, "-2020-01-01"));
  }

  @Test
  public void ValidateCreateUserFromXMLMethodWithMissingFile() {
    StatusObject<User> createdUser = model.createUserFromXML(config.get("StockData")
            + "/500/500.xml");
    assertEquals("File directory does not exist or there might be problems with "
            + "the config file", createdUser.statusMessage);
    assertEquals(-1, createdUser.statusCode);
    assertNull(createdUser.returnedObject);
  }


  @Test
  public void ValidateUpdateUserFile() throws IOException {
    StatusObject<User> tempUser = model.createUser("Paarthvi", "Sharma", 1);
    model.updateUserFile(tempUser.returnedObject);
    System.out.printf(tempUser.statusMessage);
    File userData = new File(config.get("userData"));
    for (File file : userData.listFiles()) {
      System.out.printf(file.getName());
    }
    StatusObject<User> createdUser =
            model.createUserFromXML("PDPResources/TestResources/1/1.xml");
    assertEquals("Successfully created the User Object with User ID 1",
            createdUser.statusMessage);
    assertEquals(1, createdUser.statusCode);
    String toAssert = "First Name: Paarthvi\n"
            + "Last Name: Sharma\n"
            + "List of Rigid Portfolios:\n"
            + "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 100.0 \n"
            + "* aapl Apple Inc 101.0 \n"
            + "* adi Analog Devices Inc 102.0 \n"
            + "\n"
            + "\n"
            + "List of Flexible Portfolios:\n";
    assertEquals(toAssert, createdUser.returnedObject.toString());
  }

  @Test
  public void ValidateGetRigidPortfoliosForUserNullPortfolios() throws IOException {
    User user = this.createUserWithNoPortfolios();
    List<RigidPortfolio> empty = model.getRigidPortfoliosForUser(user);
    assertEquals(empty, new ArrayList<Portfolio>());
  }

  @Test
  public void ValidateGetFlexiblePortfoliosForUserNullPortfolios() throws IOException {
    User user = this.createUserWithNoPortfolios();
    List<FlexiblePortfolio> empty = model.getFlexiblePortfoliosForUser(user);
    assertEquals(empty, new ArrayList<Portfolio>());
  }

  @Test
  public void ValidateGetRigidPortfoliosForUserWithPortfoliosAndOtherPortfolioAttributes()
          throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 100.0 \n"
            + "* aapl Apple Inc 101.0 \n"
            + "* adi Analog Devices Inc 102.0 \n\n";
    User user1 = this.createUserWith1RigidPortfoliosAndStocks();
    List<RigidPortfolio> onePortfolio = model.getRigidPortfoliosForUser(user1);
    List<Stock> p1 = new ArrayList<Stock>();
    p1.add(model.createStock("goog", 100, null));
    p1.add(model.createStock("aapl", 101, null));
    p1.add(model.createStock("adi", 102, null));
    assertEquals(portfolioString, onePortfolio.get(0).toString());
    for (int i = 0; i < onePortfolio.get(0).getListOfStock().size(); i++) {
      assertEquals(p1.get(i).stockAsString(),
              onePortfolio.get(0).getListOfStock().get(i).stockAsString());
    }
    assertEquals(1, onePortfolio.get(0).getPortfolioId());
    assertEquals("onePortfolio", onePortfolio.get(0).getPortfolioName());
  }

  @Test
  public void ValidateGetFlexiblePortfoliosForUserWithPortfoliosAndOtherPortfolioAttributes()
          throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n\n";
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    List<FlexiblePortfolio> onePortfolio = model.getFlexiblePortfoliosForUser(user1);
    List<Stock> p1 = new ArrayList<Stock>();
    p1.add(model.createStock("goog", 100, "2020-01-01"));
    p1.add(model.createStock("aapl", 101, "2020-01-02"));
    assertEquals(portfolioString, onePortfolio.get(0).toString());
    for (int i = 0; i < onePortfolio.get(0).getListOfStock().size(); i++) {
      assertEquals(p1.get(i).stockAsString(),
              onePortfolio.get(0).getListOfStock().get(i).stockAsString());
    }
    assertEquals(1, onePortfolio.get(0).getPortfolioId());
    assertEquals("onePortfolio", onePortfolio.get(0).getPortfolioName());
  }

  @Test
  public void ValidateGetParticularRigidPortfolio() throws IOException {
    User user1 = this.createUserWith1RigidPortfoliosAndStocks();
    StatusObject<RigidPortfolio> onePortfolio = model.getParticularRigidPortfolio(user1,
            1);
    assertEquals(1, model.getPortfolioId(onePortfolio.returnedObject));
    assertEquals("Retrieved portfolio successfully", onePortfolio.statusMessage);
    assertEquals("onePortfolio", model.getPortfolioName(onePortfolio.returnedObject));
    assertEquals("rigid", model.getPortfolioType(onePortfolio.returnedObject));
  }


  @Test
  public void ValidateGetParticularFlexiblePortfolio() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> onePortfolio =
            model.getParticularFlexiblePortfolio(user1, 1);
    assertEquals(1, model.getPortfolioId(onePortfolio.returnedObject));
    assertEquals("onePortfolio", model.getPortfolioName(onePortfolio.returnedObject));
    assertEquals("flexible", model.getPortfolioType(onePortfolio.returnedObject));
  }

  @Test
  public void ValidateGetValueOfRigidPortfolioForDate() throws IOException {
    User user1 = this.createUserWith1RigidPortfoliosAndStocks();
    assertEquals(40164.0,
            model.getValueOfPortfolioForDate(user1.getRigidPortfolio(1),
                    "2022-10-28").returnedObject, 0);
  }

  @Test
  public void ValidateGetValueOfRigidPortfolioForDateWithInvalidInputs() throws IOException {
    User user1 = this.createUserWith1RigidPortfoliosAndStocks();
    assertEquals(null,
            model.getValueOfPortfolioForDate(user1.getRigidPortfolio(1),
                    "-2022-10-28").returnedObject);
  }

  @Test
  public void ValidateGetValueOfFlexiblePortfolioForDate() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();

    assertEquals("167072.35", model.getValueOfPortfolioForDate(
            user1.getFlexiblePortfolio(1), "2020-01-02").returnedObject.toString());
  }


  @Test
  public void ValidateGetNextUserId() throws IOException {
    User user1 = this.createUserWith1RigidPortfoliosAndStocks();
    assertEquals(2, user1.getUserId() + 1);
    User user2 = this.createUserWith1RigidPortfoliosAndStocks();
    assertEquals(3, user2.getUserId() + 1);
    User user3 = this.createUserWith1RigidPortfoliosAndStocks();
    assertEquals(4, user3.getUserId() + 1);
  }

  @Test
  public void ValidateBuyStockInFlexiblePortfolio() throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n"
            + "* goog Alphabet Inc - Class C 2020-09-09 10.0 \n\n";
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2020-09-09", 10);
    assertEquals(portfolioString, portfolio.returnedObject.toString());
  }

  @Test
  public void ValidateBuyValidButNonListedStockInFlexiblePortfolio() throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n"
            + "* goog Alphabet Inc - Class C 2020-09-09 10.0 \n\n";
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "1920-09-09", 10);
    assertEquals(null, portfolio.returnedObject);
    assertEquals("Can buy a stock before it was listed", portfolio.statusMessage);
  }

  @Test
  public void ValidateBuyStockInFlexiblePortfolioWithInvalidTicker() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "orcl", "2020-09-09", 10);
    assertNull(portfolio.returnedObject);
    assertEquals(-1, portfolio.statusCode);
    assertEquals("Ticker is invalid", portfolio.statusMessage);
  }

  @Test
  public void ValidateBuyStockInFlexiblePortfolioWithInvalidDate() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "-2020-09-09", 10);
    assertNull(portfolio.returnedObject);
    assertEquals(-1, portfolio.statusCode);
    assertEquals("Date does not follow the YYYY-MM-DD format.", portfolio.statusMessage);
  }

  @Test
  public void ValidateBuyStockInFlexiblePortfolioWithNegativeQuantity() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2020-09-09", -1);
    assertNull(portfolio.returnedObject);
    assertEquals(portfolio.statusCode, -1);
    assertEquals("The stock quantity should be a positive integer.",
            portfolio.statusMessage);
  }

  @Test
  public void ValidateBuyStockInFlexiblePortfolioWithFractionalQuantity() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2020-09-09", -2);
    assertEquals("The stock quantity should be a positive integer.",
            portfolio.statusMessage);
  }

  @Test
  public void ValidateBuyStockInFlexiblePortfolioWithInvalidDateFormatting() throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n"
            + "* goog Alphabet Inc - Class C 2020-9-9 10.0 \n\n";
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2020-9-9", 10);
    assertEquals(portfolioString, portfolio.returnedObject.toString());
  }

  @Test
  public void ValidateBuyStockBeforeListedDate() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "1880-05-05", 10);

    assertEquals("Please enter date in valid format YYYY-MM-DD that is "
            + "later than the listing date", portfolio.statusMessage);
  }

  @Test
  public void ValidateSellStockInFlexiblePortfolio() throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 2020-01-01 90.0 \n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n\n";
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2022-09-09", 10);
    assertEquals(portfolioString, portfolio.returnedObject.toString());
  }

  @Test
  public void ValidateSellStockInFlexiblePortfolioWithInvalidSymbol() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "orcl", "2022-09-09", 1);
    assertNull(portfolio.returnedObject);
    assertEquals(-1, portfolio.statusCode);
    assertEquals("The stock ticker is invalid.", portfolio.statusMessage);
  }

  @Test
  public void ValidateSellStockInFlexiblePortfolioWithInvalidDate() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "-2022-09-09", 1);
    assertEquals(null, portfolio.returnedObject);
    assertEquals(-1, portfolio.statusCode);
    assertEquals("Date does not follow the YYYY-MM-DD format.", portfolio.statusMessage);
  }

  @Test
  public void ValidateSellStockInFlexiblePortfolioWithNegativeQuantity() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2022-09-09", -500);
    assertEquals(null, portfolio.returnedObject);
    assertEquals("The stock quantity should be a positive integer.",
            portfolio.statusMessage);
  }

  @Test
  public void ValidateSellStockInFlexiblePortfolioWithFractionalQuantity() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2022-09-09", 1.0 / 2.0);
    assertEquals(-1, portfolio.statusCode);
    assertNull(portfolio.returnedObject);
    assertEquals("The stock quantity should be a positive integer.",
            portfolio.statusMessage);
  }

  @Test
  public void ValidateSellAllStocks() throws IOException {
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n\n";

    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2022-09-09", 100);
    assertEquals(portfolioString, portfolio.returnedObject.toString());

    String portfolioStringNew = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:";

    StatusObject<FlexiblePortfolio> portfolio2 =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "aapl", "2020-01-02", 101);
    assertEquals(portfolioStringNew, portfolio2.returnedObject.toString().trim());

  }

  @Test
  public void ValidateGetFlexiblePortfoliosPastTransactions() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    StatusObject<FlexiblePortfolio> portfolio =
            model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
                    "goog", "2022-09-09", 100);

    StatusObject<PriorityQueue<Transaction>> priorityQueue =
            model.getFlexiblePortfoliosPastTransactions(user1, portfolio.returnedObject);
    assertEquals("Loaded previous transactions successfully", priorityQueue.statusMessage);
    assertEquals(1, priorityQueue.statusCode);
  }

  @Test
  public void ValidateGetCostBasisOfFlexiblePortfolioForDate() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    assertEquals("164039.35", model.getCostBasisOfFlexiblePortfolioForDate(user1,
            user1.getFlexiblePortfolio(1), "2022-01-02").returnedObject.toString());
    //Add stocks and calculate cost basis.
    model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
            "goog", "2020-09-09", 10);
    assertEquals("179609.95", model.getCostBasisOfFlexiblePortfolioForDate(user1,
            user1.getFlexiblePortfolio(1), "2022-01-20").returnedObject.toString());
    //Add stocks and calculate cost basis.
    model.buyStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
            "goog", "2020-09-09", 100);
    assertEquals("335306.95", model.getCostBasisOfFlexiblePortfolioForDate(user1,
            user1.getFlexiblePortfolio(1), "2022-01-20").returnedObject.toString());
    //Sell stocks and calculate cost basis.
    model.sellStockInFlexiblePortfolio(user1.getFlexiblePortfolio(1),
            "goog", "2020-09-09", 100);
    assertEquals("335307.95", model.getCostBasisOfFlexiblePortfolioForDate(user1,
            user1.getFlexiblePortfolio(1), "2022-01-20").returnedObject.toString());
  }


  @Test
  public void ValidateGetCostBasisOfFlexibleWithInvalidDate() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    assertEquals(null, model.getCostBasisOfFlexiblePortfolioForDate(user1,
            user1.getFlexiblePortfolio(1), "-2022-01-02").returnedObject);
  }

  @Test
  public void ValidateViewCompositionOfFlexiblePortfolio() throws IOException {
    User user1 = this.createUserWith1FlexiblePortfoliosAndStocks();
    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 100.0 2020-01-01\n"
            + "* aapl Apple Inc 101.0 2020-01-02";
    assertEquals(portfolioString, model.viewCompositionOfFlexiblePortfolio(user1,
            user1.getFlexiblePortfolio(1),
            "2022-01-02").returnedObject.toString().trim());

  }

  @Test
  public void ValidateDeliverables() throws IOException {
    User user = this.createUserWith1FlexiblePortfoliosAndStocks();
    // Purchase 3 different stock in that portfolio at different dates
    // And then query the value and cost basis of that portfolio on two specific dates.

    // Buy stock and verify value and cost basis.
    model.buyStockInFlexiblePortfolio(user.getFlexiblePortfolio(1),
            "goog", "2022-10-21", 10);
    // goog 2020-01-01 = 1337.0200, 2022-10-21 = 101.4800
    // appl 2020-01-02 = 300.3500, 2022-10-20 = 143.3900
    // amzn 2022-10-19 = 115.0700

    // Value and Cost Basis before the stock was bought.
    assertEquals("0.0", model.getCostBasisOfFlexiblePortfolioForDate(user,
            user.getFlexiblePortfolio(1), "2019-01-20").returnedObject.toString());
    assertEquals("0.0", model.getValueOfPortfolioForDate(
            user.getFlexiblePortfolio(1), "2019-01-20").returnedObject.toString());

    // Value and Cost Basis after the stock was bought.
    assertEquals("165055.15", model.getCostBasisOfFlexiblePortfolioForDate(user,
            user.getFlexiblePortfolio(1), "2022-10-21").returnedObject.toString());
    assertEquals("26037.07", model.getValueOfPortfolioForDate(
            user.getFlexiblePortfolio(1), "2022-10-21").returnedObject.toString());


    model.buyStockInFlexiblePortfolio(user.getFlexiblePortfolio(1),
            "aapl", "2022-10-20", 10);

    // Value and Cost Basis on two different dates.

    assertEquals("133703.0", model.getCostBasisOfFlexiblePortfolioForDate(user,
            user.getFlexiblePortfolio(1), "2020-01-01").returnedObject.toString());
    assertEquals("133702.0", model.getValueOfPortfolioForDate(
            user.getFlexiblePortfolio(1), "2020-01-01").returnedObject.toString());


    assertEquals("166490.05", model.getCostBasisOfFlexiblePortfolioForDate(user,
            user.getFlexiblePortfolio(1), "2022-10-21").returnedObject.toString());
    assertEquals("27509.770000000004", model.getValueOfPortfolioForDate(
            user.getFlexiblePortfolio(1), "2022-10-21").returnedObject.toString());

    StatusObject<FlexiblePortfolio> portfolio =
            model.buyStockInFlexiblePortfolio(user.getFlexiblePortfolio(1),
                    "amzn", "2022-10-19", 10);

    assertEquals("167641.75", model.getCostBasisOfFlexiblePortfolioForDate(user,
            user.getFlexiblePortfolio(1), "2022-10-21").returnedObject.toString());
    assertEquals("28702.970000000005", model.getValueOfPortfolioForDate(
            user.getFlexiblePortfolio(1), "2022-10-21").returnedObject.toString());


    String portfolioString = "Portfolio Name: onePortfolio\n"
            + "Portfolio ID: 1\n"
            + "These are the stocks present in the portfolio and their details:\n"
            + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
            + "* aapl Apple Inc 2020-01-02 101.0 \n"
            + "* goog Alphabet Inc - Class C 2022-10-21 10.0 \n"
            + "* aapl Apple Inc 2022-10-20 10.0 \n"
            + "* amzn Amazon.com Inc 2022-10-19 10.0 \n\n";

    assertEquals(portfolioString, portfolio.returnedObject.toString());

  }

}
