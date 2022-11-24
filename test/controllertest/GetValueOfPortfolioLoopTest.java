package controllertest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import controller.GetValueOfPortfolioLoop;

import model.User;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests to verify the functioning of GetValueOfPortfolioLoop which basically calculates the value
 * of rigid as well as flexible portfolio.
 */
public class GetValueOfPortfolioLoopTest extends TestHelper {

  protected GetValueOfPortfolioLoop testGetValueOfPortfolioLoop;

  @Test
  public void testRigidPortfolioValue() throws IOException {
    try {
      User createdUser = this.createUserWith1RigidPortfoliosAndStocks();
      testGetValueOfPortfolioLoop = new GetValueOfPortfolioLoop(createdUser);
      String input = "1\n1\n2022-05-05\n\n3\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testGetValueOfPortfolioLoop.runLoop(model, in, view);
      String toAssertListOfRigidPortfolio = "Portfolio Name: onePortfolio\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 100.0 \n"
              + "* aapl Apple Inc 101.0 \n"
              + "* adi Analog Devices Inc 102.0";
      String toAssertGetParticularRigidPortfolio = "First Name: One\n"
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
              + "List of Flexible Portfolios:\n"
              + "\n"
              + "1";

      String toAssertGetValueOfPortfolioForDate = "Portfolio Name: onePortfolio\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 100.0 \n"
              + "* aapl Apple Inc 101.0 \n"
              + "* adi Analog Devices Inc 102.0 \n"
              + "\n"
              + "\n"
              + "2022-05-05";
      String[] loggedInput = log.toString().split("\\|\\|");
      assertEquals(toAssertListOfRigidPortfolio, loggedInput[loggedInput.length - 3].trim());
      assertEquals(toAssertGetParticularRigidPortfolio, loggedInput[loggedInput.length - 2]);
      assertEquals(toAssertGetValueOfPortfolioForDate, loggedInput[loggedInput.length - 1]);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testFlexiblePortfolioValue() throws IOException {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      testGetValueOfPortfolioLoop = new GetValueOfPortfolioLoop(createdUser);
      String input = "2\n1\n2022-05-05\n\n3\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testGetValueOfPortfolioLoop.runLoop(model, in, view);
      String toAssertListOfFlexiblePortfolios = "Portfolio Name: onePortfolio\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
              + "* aapl Apple Inc 2020-01-02 101.0";

      String toAssertGetParticularFlexiblePortfolio = "First Name: One\n"
              + "Last Name: FlexiblePortfolio\n"
              + "List of Rigid Portfolios:\n"
              + "List of Flexible Portfolios:\n"
              + "Portfolio Name: onePortfolio\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
              + "* aapl Apple Inc 2020-01-02 101.0 \n"
              + "\n"
              + "\n"
              + "\n"
              + "1";


      String toAssertGetValueOfPortfolioForDate = "Portfolio Name: onePortfolio\n"
              + "Portfolio ID: 1n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
              + "* aapl Apple Inc 2020-01-02 101.0 \n"
              + "\n"
              + "\n"
              + "2022-05-05";
      System.out.println(log.toString());
      String[] loggedInput = log.toString().split("\\|\\|");
      assertEquals(toAssertListOfFlexiblePortfolios, loggedInput[loggedInput.length - 3].trim());
      assertEquals(toAssertGetParticularFlexiblePortfolio,
              loggedInput[loggedInput.length - 2].trim());
      assertEquals(toAssertGetValueOfPortfolioForDate, loggedInput[loggedInput.length - 1]);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }
}
