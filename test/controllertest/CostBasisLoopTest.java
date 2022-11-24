package controllertest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import controller.CostBasisLoop;
import model.User;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests to verify the functionality of CostBasisLoop which basically calculates the
 * cost basis of flexible portfolio.
 */
public class CostBasisLoopTest extends TestHelper {
  CostBasisLoop testCostBasisLoop;

  @Test
  public void testRunLoop() {
    try {
      User createdUser = this.createUserWith1FlexiblePortfoliosAndStocks();
      testCostBasisLoop = new CostBasisLoop(createdUser);
      String input = "1\n2022-05-05\n\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testCostBasisLoop.runLoop(model, in, view);
      String toAssertListFlexiblePortfolios = "Portfolio Name: onePortfolio\n"
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
      String toAssertGetCostBasisOfFlexiblePortfolioForDate = "First Name: One\n"
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
              + "Portfolio Name: onePortfolio\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 2020-01-01 100.0 \n"
              + "* aapl Apple Inc 2020-01-02 101.0 \n"
              + "\n"
              + "\n"
              + "2022-05-05";
      String[] loggedInput = log.toString().split("\\|\\|");
      assertEquals(toAssertListFlexiblePortfolios, loggedInput[loggedInput.length - 3].trim());
      assertEquals(toAssertGetParticularFlexiblePortfolio, loggedInput[loggedInput.length - 2]);
      assertEquals(toAssertGetCostBasisOfFlexiblePortfolioForDate,

              loggedInput[loggedInput.length - 1]);

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }


}
