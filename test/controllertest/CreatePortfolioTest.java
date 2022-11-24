package controllertest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import controller.CreatePortfolio;
import model.User;

import static org.junit.Assert.assertEquals;

/**
 * Tests for verifying the functioning of CreatePortfolio loop.
 */
public class CreatePortfolioTest extends TestHelper {

  protected CreatePortfolio testCreatePortfolioLoop;

  @Test
  public void TestCreateRigidPortfolio() {
    try {
      String toAssert;
      User createdUser = this.createUserWith1RigidPortfoliosAndStocks();
      testCreatePortfolioLoop = new CreatePortfolio(createdUser);
      log.setLength(0);
      String input = "1\nTestCreation\namzn\n25\n\n1\ngoog\n35\n\n2\n\n3\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testCreatePortfolioLoop.runLoop(model, in, view);
      String[] output = log.toString().split("\\|\\|");
      assertEquals("amzn\n25.0\nnull", output[0]);
      assertEquals("goog\n35.0\nnull", output[2]);
      toAssert = "First Name: One\nLast Name: RigidPortfolio\nList of Rigid Portfolios:\n"
              + "Portfolio Name: onePortfolio\nPortfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 100.0 \n* aapl Apple Inc 101.0 \n"
              + "* adi Analog Devices Inc 102.0 \n\n\nList of Flexible Portfolios:\n"
              + "\nTestCreation";
      assertEquals(toAssert, output[3].trim());
      toAssert = "amzn Amazon.com Inc 25.0 null\ngoog Alphabet Inc - Class C 35.0 null\n"
              + "First Name: One\nLast Name: RigidPortfolio\n"
              + "List of Rigid Portfolios:\nPortfolio Name: onePortfolio\nPortfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* goog Alphabet Inc - Class C 100.0 \n* aapl Apple Inc 101.0 \n"
              + "* adi Analog Devices Inc 102.0 \n\n\nPortfolio Name: TestCreation\nPortfolio ID:"
              + " 1\nThese are the stocks present in the portfolio and their details:\n* amzn"
              + " Amazon.com Inc 25.0 \n* goog Alphabet Inc - Class C 35.0 \n\n\nList of"
              + " Flexible Portfolios:";
      assertEquals(toAssert, output[4].trim());
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void TestCreateFlexiblePortfolio() {
    try {
      String toAssert;
      User createdUser = this.createUserWith1RigidPortfoliosAndStocks();
      testCreatePortfolioLoop = new CreatePortfolio(createdUser);
      log.setLength(0);
      String input = "2\nTestCreation\namzn\n25\n2016-01-01\n\n1\ngoog\n35\n2018-01-01\n\n2\n\n3\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testCreatePortfolioLoop.runLoop(model, in, view);
      String[] output = log.toString().split("\\|\\|");
      assertEquals("amzn\n25.0\n2016-01-01", output[0]);
      assertEquals("amzn Amazon.com Inc 25.0 2016-01-01", output[1]);
      assertEquals("goog\n35.0\n2018-01-01", output[3]);
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
              + "List of Flexible Portfolios:\n"
              + "\n"
              + "TestCreation\n"
              + "amzn Amazon.com Inc 25.0 2016-01-01\n"
              + "goog Alphabet Inc - Class C 35.0 2018-01-01";
      assertEquals(toAssert, output[4].trim());
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
              + "List of Flexible Portfolios:\n"
              + "Portfolio Name: TestCreation\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* amzn Amazon.com Inc 2016-01-01 25.0 \n"
              + "* goog Alphabet Inc - Class C 2018-01-01 35.0";
      assertEquals(toAssert, output[5].trim());
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
              + "List of Flexible Portfolios:\n"
              + "Portfolio Name: TestCreation\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* amzn Amazon.com Inc 2016-01-01 25.0 \n"
              + "* goog Alphabet Inc - Class C 2018-01-01 35.0 \n"
              + "\n"
              + "\n"
              + "\n"
              + "Portfolio Name: TestCreation\n"
              + "Portfolio ID: 1\n"
              + "These are the stocks present in the portfolio and their details:\n"
              + "* amzn Amazon.com Inc 2016-01-01 25.0 \n"
              + "* goog Alphabet Inc - Class C 2018-01-01 35.0";
      assertEquals(toAssert, output[6].trim());
    } catch (IOException | InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
