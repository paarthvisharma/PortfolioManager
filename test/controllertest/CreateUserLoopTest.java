package controllertest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import controller.CreateUserLoop;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests to verify the functioning of CreateUserLoop which basically creates the user for the
 * Stock Program.
 */
public class CreateUserLoopTest extends TestHelper {

  CreateUserLoop testCreateUserLoop;

  @Test
  public void testCreateUserManually() {
    try {
      testCreateUserLoop = new CreateUserLoop();
      String input = "1\nPrajwal Shenoy\n\n3\n";
      InputStream ip = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
      Scanner in = new Scanner(ip);
      testCreateUserLoop.runLoop(model, in, view);
      String toAssert = "First Name: Prajwal\n"
              + "Last Name: Shenoy\n"
              + "List of Rigid Portfolios:\n"
              + "List of Flexible Portfolios:\n";
      assertEquals("FirstName: Prajwal LastName: Shenoy", log.toString().split("\\|\\|")[0]);
      assertEquals(toAssert, log.toString().split("\\|\\|")[1]);
    } catch (InterruptedException e) {
      System.out.println("Ending the test");
    }
  }

}
