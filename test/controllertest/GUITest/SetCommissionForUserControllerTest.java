package controllertest.GUITest;

import org.junit.Test;

import controller.gui.JSetCommissionController;
import model.User;
import view.gui.JView;
import view.gui.JViewImpl;

import static org.junit.Assert.assertEquals;

public class SetCommissionForUserControllerTest extends TestHelperGUIController {

  private JSetCommissionController jSetCommissionController;
  private JView mockView = new JViewImpl();

  @Test
  public void testMain() {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      jSetCommissionController = new JSetCommissionController(model);
      jSetCommissionController.setView(mockView);
      jSetCommissionController.setUser(createdUser);
      jSetCommissionController.setCommission("5");
      assertEquals(createdUser.getCommission(), 5.0, 0);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }
}
