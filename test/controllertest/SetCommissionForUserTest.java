package controllertest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import controller.SetCommissionForUser;
import model.PortfolioManager;
import model.User;

import static org.junit.Assert.assertEquals;
import static utils.Utils.getConfigAndProgramState;

/**
 * Test to verify the functioning of SetCommissionForUser loop which basically sets the
 * user commission on transactions.
 */
public class SetCommissionForUserTest extends TestHelper {

  protected SetCommissionForUser testSetCommissionForUser;
  protected User user;
  protected PortfolioManager portfolioManager;
  protected Map<String, String> config = getConfigAndProgramState();

  @Test
  public void TestRunLoop() {
    try {
      String toAssert;
      User createdUser = this.createUserWith1RigidPortfoliosAndStocks();
      testSetCommissionForUser = new SetCommissionForUser(createdUser);
      log.setLength(0);
      String input = "5\n\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testSetCommissionForUser.runLoop(model, in, view);
      String[] output = log.toString().split("\\|\\|");
      toAssert = "First Name: One\n"
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
              + "List of Flexible Portfolios:";
      assertEquals(toAssert, output[0].trim());
      assertEquals(5.0, createdUser.getCommission(), 0);
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }

}
