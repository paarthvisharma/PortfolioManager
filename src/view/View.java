package view;

/**
 * The View Interface represents the view part of the three
 * broad categories of model-view-controller (MVC) design.
 * This Interface represents operations that the controller
 * would need to invoke on the view.
 */
public interface View {
  /**
   * Method to display user menu.
   */

  void displayUserMenu();

  /**
   * Method to display portfolio menu.
   */

  void displayPortfolioMenu();

  /**
   * Method to display portfolio ID menu.
   */
  void displayPortfolioForTransaction();

  /**
   * Method to display different menu options available while creating the portfolio.
   */

  void displayCreatePortfolioMenu();

  /**
   * Method to display different transaction options available.
   */
  void displayPromptForTransactionType();

  /**
   * Method to return the string that needs to be displayed while requesting a date.
   */
  void displayPromptForDate();

  /**
   * Method to return the string that needs to be displayed while requesting a portfolio ID
   * for calculating cost basis.
   */
  void displayPromptForPortfolioIdCostBasis();

  /**
   * Method to return string that needs to be displayed for back option.
   */
  void displayBForBackPrompt();

  /**
   * Method to return the string that needs to be displayed while
   * requesting for more stocks to be added.
   */
  void displayPromptIfMoreStocksToBeAdded();

  /**
   * Method to return the string that needs to be displayed while requesting
   * for User ID.
   */
  void displayUserIdPrompt();

  /**
   * Method to return the string that needs to be displayed while giving different
   * portfolio options.
   */
  void displayPromptWhichTypeOfPortfolio();

  /**
   * Method to return the string that needs to be displayed while requesting for date
   * for valuation calculation.
   */
  void displayGetDateForValuation();


  /**
   * Method to return the string that needs to be displayed while creating a user.
   */
  void displayCreateUserMenu();

  /**
   * Method to return the string that needs to be displayed while creating a user manually.
   */
  void displayCreateUserManuallyPrompt();

  /**
   * Method that prompts to ask the start date for which portfolio performance needs to
   * be displayed.
   */
  void displayPromptForStartDatePerformance();

  /**
   * Method that prompts to ask the end date for which portfolio performance needs to be displayed.
   */
  void displayPromptForEndDatePerformance();

  /**
   * Method to return the string that needs to be displayed while entering a ticker symbol.
   * Prompts to enter the ticker symbol for the stock.
   */
  void promptForStockTicker();

  /**
   * Method to return the string that needs to be displayed while entering the stock quantity.
   * Prompts to enter the stock quantity.
   */
  void promptForStockQuantity();

  /**
   * Method to return the string that needs to be displayed while entering the portfolio name.
   * Prompts to enter the name of the portfolio.
   */
  void promptPortfolioName();

  /**
   * Method to return the string that needs to be displayed while entering the stock purchase date.
   * Prompts to enter the date of purchase of stock in format YYYY-MM-DD.
   */
  void promptForStockPurchaseDate();

  /**
   * Method that prompts the PortfolioId that needs to be displayed.
   */
  void displayPortfolioFromPortfolioIdPrompt();


  /**
   * Method that prompts the PortfolioId that needs to be valuated.
   */
  void displayValueOfPortfolioPrompt();


  /**
   * Method to print the message.
   *
   * @param message takes String value of the message.
   */
  void displayMessage(String message);

  /**
   * Method to print the Success message in green colour.
   *
   * @param message takes String value of the message.
   */
  void displaySuccessMessage(String message);

  /**
   * Method to print the Failure message in red colour.
   *
   * @param message takes String value of the message.
   */
  void displayFailureMessage(String message);

  /**
   * Method to display entry of invalid input.
   */

  void displayInvalidInputFailureMessageAndAskForPrompt();

  /**
   * Method to clear the interface.
   */
  void displayClear();

  /**
   * Method that prompts to enter the path to the XML file which needs to be loaded.
   */
  void promptForXmlFilePath();

  /**
   * Method prompts to press any key to continue.
   */
  void askPromptToContinue();

  /**
   * Method to display commission change value.
   *
   * @param currentCommission current commission value.
   */
  void promptForCommissionChange(double currentCommission);
}
