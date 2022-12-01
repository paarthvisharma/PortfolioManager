package controllertest.GUITest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.gui.JCreatePortfolioController;
import controller.gui.JCreatePortfolioControllerImpl;
import controllertest.MockGUIView.MockView;
import model.User;
import view.gui.JView;

import static org.junit.Assert.assertEquals;

public class CreatePortfolioControllerTest extends TestHelperGUIController {

  private JCreatePortfolioController jCreatePortfolioController;
  private JView mockView = new MockView();

  @Test
  public void testAddStockForDCA() {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      jCreatePortfolioController = new JCreatePortfolioControllerImpl(model);
      jCreatePortfolioController.setView(mockView);
      jCreatePortfolioController.setUser(createdUser);
      jCreatePortfolioController.addStockForDCA("goog");
      String[] toAssert = log.toString().split("\\|\\|");
      assertEquals("goog", toAssert[toAssert.length - 1]);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  @Test
  public void testCreatePortfolio() {
    try {
      User createdUser = this.createUserWith1RigidPortfoliosAndStocks();
      jCreatePortfolioController = new JCreatePortfolioControllerImpl(model);
      jCreatePortfolioController.setView(mockView);
      jCreatePortfolioController.setUser(createdUser);
      List<List<String>> tableData = new ArrayList<>();
      tableData.add(List.of(new String[]{"goog", "", "", "50"}));
      tableData.add(List.of(new String[]{"aapl", "", "", "50"}));
      HashMap<String, String> dcaSetting = new HashMap<>();
      dcaSetting.put("startDate", "2021-01-01");
      dcaSetting.put("endDate", "2022-01-01");
      dcaSetting.put("interval", "30");
      dcaSetting.put("dollarAmount", "2000");
      dcaSetting.put("commission", "5");
      jCreatePortfolioController.createPortfolio("Test creation", dcaSetting, tableData);
      String[] toAssert = log.toString().split("\n");
      assertEquals("[2021-01-01,2022-01-01,30,2000.0,5.0,|goog|50||aapl|50|,2021-12-27]", toAssert[toAssert.length - 2]);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

}
