package controllertest.GUITest;

import org.junit.Test;

import controller.gui.JCalculateValueController;
import controller.gui.JCalculateValueControllerImpl;
import controller.gui.JSetCommissionController;
import controller.gui.JSetCommissionControllerImpl;
import controllertest.MockGUIView.MockView;
import model.User;
import view.gui.JView;

import static controllertest.MockGUIView.AssertConstants.CALCULATE_VALUE_CONTROLLER_TEST;
import static org.junit.Assert.assertEquals;

public class SetCommissionForUserControllerTest extends TestHelperGUIController {

  private JSetCommissionController jSetCommissionController;
  private JView mockView = new MockView();

  @Test
  public void testMain() {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      jSetCommissionController = new JSetCommissionControllerImpl(model);
      jSetCommissionController.setView(mockView);
      jSetCommissionController.setUser(createdUser);
      jSetCommissionController.setCommission("5");
      assertEquals(createdUser.getCommission(), 5.0, 0);
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }
}
