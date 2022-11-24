package viewtest;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import view.View;
import view.ViewImpl;

import static org.junit.Assert.assertEquals;
import static view.constants.MenuConstants.CHOOSE_BETWEEN_RIGID_AND_FLEXIBLE;
import static view.constants.MenuConstants.CREATE_PORTFOLIO_MENU;
import static view.constants.MenuConstants.CREATE_USER_MENU;
import static view.constants.MenuConstants.ENTER_B_FOR_BACK;
import static view.constants.MenuConstants.GET_DATE_FOR_VALUATION;
import static view.constants.MenuConstants.PORTFOLIO_MENU;
import static view.constants.MenuConstants.PROMPT_DATE;
import static view.constants.MenuConstants.PROMPT_PORTFOLIO_ID_FOR_COST_BASIS;
import static view.constants.MenuConstants.PROMPT_PORTFOLIO_ID_FOR_TRANSACTION;
import static view.constants.MenuConstants.PROMPT_TRANSACTION_TYPE;
import static view.constants.MenuConstants.USER_ID_PROMPT;
import static view.constants.MenuConstants.USER_MENU;
import static view.constants.MenuConstants.WANT_TO_ADD_MORE_STOCKS_TO_PORTFOLIO;

/**
 * Tests to verify the working of the View.
 */

public class ViewTests {

  protected View view;
  protected ByteArrayOutputStream out;


  @Before
  public void setUp() {
    this.out = new ByteArrayOutputStream();
    this.view = new ViewImpl(new PrintStream(this.out));
  }

  @Test
  public void ValidateDisplayUserMenu() {
    this.view.displayUserMenu();
    assertEquals(USER_MENU + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayCreateUserMenu() {
    this.view.displayCreateUserMenu();
    assertEquals(CREATE_USER_MENU + "\n", out.toString());
  }

  @Test
  public void ValidatedDisplayPortfolioMenu() {
    this.view.displayPortfolioMenu();
    assertEquals(PORTFOLIO_MENU + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPortfolioForTransaction() {
    this.view.displayPortfolioForTransaction();
    assertEquals(PROMPT_PORTFOLIO_ID_FOR_TRANSACTION + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayCreatePortfolioMenu() {
    this.view.displayCreatePortfolioMenu();
    assertEquals(CREATE_PORTFOLIO_MENU + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPromptForTransactionType() {
    this.view.displayPromptForTransactionType();
    assertEquals(PROMPT_TRANSACTION_TYPE + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPromptForDate() {
    this.view.displayPromptForDate();
    assertEquals(PROMPT_DATE + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPromptForPortfolioIdCostBasis() {
    this.view.displayPromptForPortfolioIdCostBasis();
    assertEquals(PROMPT_PORTFOLIO_ID_FOR_COST_BASIS + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayBForBackPrompt() {
    this.view.displayBForBackPrompt();
    assertEquals(ENTER_B_FOR_BACK + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPromptIfMoreStocksToBeAdded() {
    this.view.displayPromptIfMoreStocksToBeAdded();
    assertEquals(WANT_TO_ADD_MORE_STOCKS_TO_PORTFOLIO + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayUserIdPrompt() {
    this.view.displayUserIdPrompt();
    assertEquals(USER_ID_PROMPT + "\n", out.toString());

  }

  @Test
  public void ValidateDisplayPromptWhichTypeOfPortfolio() {
    this.view.displayPromptWhichTypeOfPortfolio();
    assertEquals(CHOOSE_BETWEEN_RIGID_AND_FLEXIBLE + "\n", out.toString());

  }

  @Test
  public void ValidateDisplayGetDateForValuation() {
    this.view.displayGetDateForValuation();
    assertEquals(GET_DATE_FOR_VALUATION + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayCreateUserManuallyPrompt() {
    this.view.displayCreateUserManuallyPrompt();
    assertEquals(MenuConstants.CREATE_USER_MENU_CONTENT + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPromptForStartDatePerformance() {
    this.view.displayPromptForStartDatePerformance();
    assertEquals(MenuConstants.PROMPT_FOR_START_DATE + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPromptForEndDatePerformance() {
    this.view.displayPromptForEndDatePerformance();
    assertEquals(MenuConstants.PROMPT_FOR_END_DATE + "\n", out.toString());
  }

  @Test
  public void ValidatePromptForStockTicker() {
    this.view.promptForStockTicker();
    assertEquals(MenuConstants.PROMPT_FOR_STOCK_TICKER + "\n", out.toString());
  }

  @Test
  public void ValidatePromptForStockQuantity() {
    this.view.promptForStockQuantity();
    assertEquals(MenuConstants.PROMPT_FOR_STOCK_QUANTITY + "\n", out.toString());
  }

  @Test
  public void ValidatePromptPortfolioName() {
    this.view.promptPortfolioName();
    assertEquals(MenuConstants.PROMPT_PORTFOLIO_NAME + "\n", out.toString());
  }

  @Test
  public void ValidatePromptForStockPurchaseDate() {
    this.view.promptForStockPurchaseDate();
    assertEquals(MenuConstants.PROMPT_FOR_STOCK_PURCHASE_DATE + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayPortfolioFromPortfolioIdPrompt() {
    this.view.displayPortfolioFromPortfolioIdPrompt();
    assertEquals(MenuConstants.DISPLAY_SPECIFIC_PORTFOLIO_PROMPT + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayValueOfPortfolioPrompt() {
    this.view.displayValueOfPortfolioPrompt();
    assertEquals(MenuConstants.DISPLAY_VALUE_SPECIFIC_PORTFOLIO_PROMPT
            + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayMessage() {
    String message = "123";
    this.view.displayMessage(message);
    assertEquals("123" + "\n", out.toString());
  }

  @Test
  public void ValidateDisplaySuccessMessage() {
    String message = "Success message";
    this.view.displaySuccessMessage(message);
    assertEquals("\u001B[32mSuccess message\u001B[0m" + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayFailureMessage() {
    String message = "Failure message";
    this.view.displayFailureMessage(message);
    assertEquals("\u001B[31mFailure message\u001B[0m" + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayInvalidInputFailureMessageAndAskForPrompt() {
    this.view.displayInvalidInputFailureMessageAndAskForPrompt();
    assertEquals("\u001B[31mInvalid input, please try again\u001B[0m\n" +
            "Press any key to continue." + "\n", out.toString());
  }

  @Test
  public void ValidateDisplayClear() {
    this.view.displayClear();
    assertEquals("\033[H\033[2J", out.toString());
  }

  @Test
  public void ValidatePromptForXmlFilePath() {
    this.view.promptForXmlFilePath();
    assertEquals(MenuConstants.PROMPT_FOR_XML_FILE_PATH + "\n", out.toString());
  }

  @Test
  public void ValidateAskPromptToContinue() {
    this.view.askPromptToContinue();
    assertEquals(MenuConstants.ASK_FOR_PROMPT_TO_CONTINUE + "\n", out.toString());
  }

  @Test
  public void ValidatePromptForCommissionChange() {
    double currentCommission = 2.0;
    this.view.promptForCommissionChange(currentCommission);
    assertEquals(MenuConstants.COMMISSION_PROMPT + currentCommission,
            out.toString().trim());

  }


}
