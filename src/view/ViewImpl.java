package view;

import java.io.PrintStream;

import view.constants.MenuConstants;

import static view.constants.MenuConstants.CREATE_PORTFOLIO_MENU;
import static view.constants.MenuConstants.CREATE_USER_MENU;
import static view.constants.MenuConstants.CHOOSE_BETWEEN_RIGID_AND_FLEXIBLE;
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
 * This class is an implementation of the View Interface.
 * The methods associated with the class help perform operations of
 * the view.
 */

public class ViewImpl implements View {

  private final PrintStream out;

  /**
   * This is the constructor for ViewImpl. An Instance of ViewImpl is created with
   * the help of this constructor.
   *
   * @param out PrintStream to provide the ability to the view to print various data
   *            values conveniently.
   */

  public ViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void displayUserMenu() {
    out.println(USER_MENU);
  }

  @Override
  public void displayCreateUserMenu() {
    out.println(CREATE_USER_MENU);
  }

  @Override
  public void displayPortfolioMenu() {
    out.println(PORTFOLIO_MENU);
  }

  @Override
  public void displayPortfolioForTransaction() {
    out.println(PROMPT_PORTFOLIO_ID_FOR_TRANSACTION);
  }

  @Override
  public void displayCreatePortfolioMenu() {
    out.println(CREATE_PORTFOLIO_MENU);
  }

  @Override
  public void displayPromptForTransactionType() {
    out.println(PROMPT_TRANSACTION_TYPE);
  }


  @Override
  public void displayPromptForDate() {
    out.println(PROMPT_DATE);
  }

  @Override
  public void displayPromptForPortfolioIdCostBasis() {
    out.println(PROMPT_PORTFOLIO_ID_FOR_COST_BASIS);
  }

  @Override
  public void displayBForBackPrompt() {
    out.println(ENTER_B_FOR_BACK);
  }

  @Override
  public void displayPromptIfMoreStocksToBeAdded() {
    out.println(WANT_TO_ADD_MORE_STOCKS_TO_PORTFOLIO);
  }

  @Override
  public void displayUserIdPrompt() {
    out.println(USER_ID_PROMPT);
  }

  @Override
  public void displayPromptWhichTypeOfPortfolio() {
    out.println(CHOOSE_BETWEEN_RIGID_AND_FLEXIBLE);
  }

  @Override
  public void displayGetDateForValuation() {
    out.println(GET_DATE_FOR_VALUATION);
  }


  @Override
  public void displayCreateUserManuallyPrompt() {
    out.println(MenuConstants.CREATE_USER_MENU_CONTENT);
  }


  @Override
  public void displayPromptForStartDatePerformance() {
    out.println(MenuConstants.PROMPT_FOR_START_DATE);
  }

  @Override
  public void displayPromptForEndDatePerformance() {
    out.println(MenuConstants.PROMPT_FOR_END_DATE);
  }

  @Override
  public void promptForStockTicker() {
    out.println((MenuConstants.PROMPT_FOR_STOCK_TICKER));
  }

  @Override
  public void promptForStockQuantity() {
    out.println((MenuConstants.PROMPT_FOR_STOCK_QUANTITY));
  }

  @Override
  public void promptPortfolioName() {
    out.println(MenuConstants.PROMPT_PORTFOLIO_NAME);
  }

  @Override
  public void promptForStockPurchaseDate() {
    out.println((MenuConstants.PROMPT_FOR_STOCK_PURCHASE_DATE));
  }


  @Override
  public void displayPortfolioFromPortfolioIdPrompt() {
    out.println(MenuConstants.DISPLAY_SPECIFIC_PORTFOLIO_PROMPT);
  }


  @Override
  public void displayValueOfPortfolioPrompt() {
    out.println(MenuConstants.DISPLAY_VALUE_SPECIFIC_PORTFOLIO_PROMPT);
  }


  @Override
  public void displayMessage(String message) {
    out.println(message);
  }

  @Override
  public void displaySuccessMessage(String message) {
    out.println("\u001B[32m" + message + "\u001B[0m");
  }

  @Override
  public void displayFailureMessage(String message) {
    out.println("\u001B[31m" + message + "\u001B[0m");
  }

  @Override
  public void displayInvalidInputFailureMessageAndAskForPrompt() {
    out.println("\u001B[31mInvalid input, please try again\u001B[0m");
    this.askPromptToContinue();
  }

  @Override
  public void displayClear() {
    out.print("\033[H\033[2J");
    out.flush();
  }

  @Override
  public void promptForXmlFilePath() {
    out.println(MenuConstants.PROMPT_FOR_XML_FILE_PATH);
  }

  @Override
  public void askPromptToContinue() {
    out.println(MenuConstants.ASK_FOR_PROMPT_TO_CONTINUE);
  }


  @Override
  public void promptForCommissionChange(double currentCommission) {
    out.println(MenuConstants.COMMISSION_PROMPT + currentCommission + "\n");
  }
}
