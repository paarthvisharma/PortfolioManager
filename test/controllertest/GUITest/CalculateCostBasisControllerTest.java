package controllertest.GUITest;

import org.junit.Test;

import controller.gui.JCalculateCostBasisControllerImpl;
import model.User;
import view.gui.JView;
import view.gui.JViewImpl;

import static controllertest.GUITest.AssertConstants.CALCULATE_COST_BASIS_CONTROLLER_TEST;
import static org.junit.Assert.assertEquals;

public class CalculateCostBasisControllerTest extends TestHelperGUIController {

  private JCalculateCostBasisControllerImpl jCalculateCostBasisController;
  private JView mockView = new JViewImpl();

  @Test
  public void testMain() {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      jCalculateCostBasisController = new JCalculateCostBasisControllerImpl(model);
      jCalculateCostBasisController.setView(mockView);
      jCalculateCostBasisController.setUser(createdUser);
      jCalculateCostBasisController.calculatePortfolioCostBasis("2022-01-01", "1 onePortfolio");
      assertEquals(CALCULATE_COST_BASIS_CONTROLLER_TEST, log.toString());
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

}
