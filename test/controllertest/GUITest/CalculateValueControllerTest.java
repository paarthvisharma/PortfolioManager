package controllertest.GUITest;

import org.junit.Test;

import controller.gui.JCalculateValueController;
import controller.gui.JCalculateValueControllerImpl;
import controllertest.MockGUIView.MockView;
import model.User;
import view.gui.JView;

import static controllertest.MockGUIView.AssertConstants.CALCULATE_VALUE_CONTROLLER_TEST;
import static org.junit.Assert.assertEquals;

public class CalculateValueControllerTest extends TestHelperGUIController {

  private JCalculateValueController jCalculateValueController;
  private JView mockView = new MockView();

  @Test
  public void testMain() {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      jCalculateValueController = new JCalculateValueControllerImpl(model);
      jCalculateValueController.setView(mockView);
      jCalculateValueController.setUser(createdUser);
      jCalculateValueController.calculatePortfolioValue("2022-01-01", "1 onePortfolio");
      assertEquals(CALCULATE_VALUE_CONTROLLER_TEST, log.toString());
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

}
