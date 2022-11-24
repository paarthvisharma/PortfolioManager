package view.constants;

/**
 * This class contains constant values that are being used to simulate the
 * menu options displayed in the text-based interface.
 */
public class MenuConstants {

  public static String USER_MENU = "Main Menu:\n"
          + "1) Create User\n"
          + "2) Login using UserId\n"
          + "3) Exit\n";

  public static String CREATE_USER_MENU = "Create User Menu\n"
          + "1) Create User Manually\n"
          + "2) Create User from XML\n"
          + "3) Back\n";

  public static String PORTFOLIO_MENU = "Portfolio Menu\n"
          + "1) Set commission for user\n"
          + "2) Create Portfolio\n"
          + "3) View Portfolio\n"
          + "4) Value portfolio\n"
          + "5) Cost of portfolio\n"
          + "6) Transact in a portfolio. (Buy/Sell stocks)\n"
          + "7) Portfolio performance\n"
          + "8) Back\n";

  public static String CREATE_PORTFOLIO_MENU = "Create Portfolio Menu\n"
          + "1) Create rigid portfolio\n"
          + "2) Create flexible portfolio\n"
          + "3) Back\n";

  public static String WANT_TO_ADD_MORE_STOCKS_TO_PORTFOLIO =
          "Do you want to add more stocks to the portfolio?\n"
                  + "1) Yes\n"
                  + "2) No\n";

  public static String PROMPT_PORTFOLIO_ID_FOR_TRANSACTION =
          "Select a portfolio from the list of "
                  + "to perform transactions on\n";


  public static String PROMPT_TRANSACTION_TYPE =
          "Select the appropriate transaction type from the below list\n"
                  + "1) Buy stocks\n"
                  + "2) Sell stocks\n"
                  + "3) Back\n";

  public static String GENERIC_MENU_HEADER = "Portfolio Manager: ";

  public static String GENERIC_GET_PORTFOLIO_ID_MESSAGE = "Enter the Portfolio ID "
          + "which needs to be ";

  public static String CHOOSE_BETWEEN_RIGID_AND_FLEXIBLE = "Enter the Portfolio type on which "
          + "you want to perform the operation\n"
          + "1) Rigid Portfolio\n"
          + "2) Flexible Portfolio\n"
          + "3) Back";


  public static String CREATE_USER_MENU_CONTENT = "Create a User for whom portfolios can be"
          + " created and managed via a UserId\n"
          + "Enter First name and last name separated by a space.\n";

  public static String PROMPT_PORTFOLIO_ID_FOR_COST_BASIS = "Select a portfolio from the below "
          + "list for which the cost basis needs to be calculated.\n";

  public static String ENTER_B_FOR_BACK = "Enter 'b' to go to the previous menu\n";

  public static String PROMPT_DATE = "Enter the date (YYYY-MM-DD) for the operation.\n";


  public static String PROMPT_FOR_START_DATE =
          "Enter the start date (YYYY-MM-DD) for tracking the performance of a portfolio";

  public static String PROMPT_FOR_END_DATE =
          "Enter the end date (YYYY-MM-DD) for tracking the performance of a portfolio";

  public static String USER_ID_PROMPT = "Please enter the User ID";

  public static String COMMISSION_PROMPT =
          "Enter the new commission for the user. The current commission rate is: $";

  public static String PROMPT_PORTFOLIO_NAME = "Enter the name of the portfolio to be created";

  public static String PROMPT_FOR_STOCK_TICKER = GENERIC_MENU_HEADER + "Add stock to portfolio\n"
          + "Please enter the ticker symbol for the stock\n";

  public static String PROMPT_FOR_STOCK_QUANTITY = GENERIC_MENU_HEADER + "Add stock to portfolio\n"
          + "Please enter the stock quantity\n";

  public static String PROMPT_FOR_STOCK_PURCHASE_DATE = "Please enter the date at which the stocks"
          + " were purchased in the YYYY-MM-DD\n";

  public static String DISPLAY_SPECIFIC_PORTFOLIO_PROMPT = "Value portfolio\n"
          + GENERIC_GET_PORTFOLIO_ID_MESSAGE + "viewed.\n";


  public static String DISPLAY_VALUE_SPECIFIC_PORTFOLIO_PROMPT = "Valuation of portfolio\n"
          + GENERIC_GET_PORTFOLIO_ID_MESSAGE
          + "valuated.";

  public static String GET_DATE_FOR_VALUATION = "Enter the date for which the portfolio has"
          + " to be valuated in the YYYY-MM-DD format.\n";


  public static String PROMPT_FOR_XML_FILE_PATH = "Load User from XML file\n"
          + "Enter the path to the XML file.\n";

  public static String ASK_FOR_PROMPT_TO_CONTINUE = "Press any key to continue.";


}
