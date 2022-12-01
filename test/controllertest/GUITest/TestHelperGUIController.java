package controllertest.GUITest;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import controllertest.MockModel;
import model.FlexiblePortfolio;
import model.Model;
import model.RigidPortfolio;
import model.Stock;
import model.User;
import model.utils.StatusObject;

import static model.constants.DirectoryStructureConstants.PROGRAM_STATE_FILE_PATH;
import static utils.Utils.getConfigAndProgramState;

/**
 * This is a class which contains helper methods to verify the functioning of the controller.
 */
public class TestHelperGUIController {

  public Model model;
  public StringBuilder log = new StringBuilder();
  public Map<String, String> config = getConfigAndProgramState();

  /**
   * Method to set up the test environment.
   */
  @Before
  public void setup() {
    model = new MockModel(log);
  }

  /**
   * Method to purge directories after test has been completed.
   */
  @After
  public void purgeDirectory() {
    File userData = new File(config.get("userData"));
    deleteDirectory(userData);
  }

  /**
   * Method to purge directory.
   */
  public void deleteDirectory(File directory) {
    for (File file : Objects.requireNonNull(directory.listFiles())) {
      if (file.isDirectory()) {
        deleteDirectory(file);
      }
      file.delete();
    }
  }

  /**
   * Method to update program state file while testing.
   */
  public static void updateProgramState(Model model) {
    FileWriter programStateWriter = null;
    try {
      programStateWriter = new FileWriter(PROGRAM_STATE_FILE_PATH);
      programStateWriter.write("userId" + ":" + model.getNextUserId() + "\n");
      programStateWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method to create user with rigid portfolio for testing.
   */
  public User createUserWith1RigidPortfoliosAndStocks() throws IOException {
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
    TestHelperGUIController.updateProgramState(model);
    return temp.returnedObject;
  }

  /**
   * Method to create user with flexible portfolio for testing.
   */
  public User createUserWith1FlexiblePortfoliosAndStocks() throws IOException {
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
    System.out.println(tempPortfolio.statusMessage);
    model.updateUserFile(temp.returnedObject);
    TestHelperGUIController.updateProgramState(model);
    return temp.returnedObject;
  }

}
