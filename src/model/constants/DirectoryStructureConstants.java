package model.constants;

import java.io.IOException;

/**
 * This class stores the constant values for directory paths where
 * program related files are being stored.
 */
public class DirectoryStructureConstants {


  public static final String CURRENT_DIRECTORY;

  static {
    try {
      CURRENT_DIRECTORY = new java.io.File(".").getCanonicalPath();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static final String PROGRAM_STATE_FILE_PATH = CURRENT_DIRECTORY
          + "/PDPResources/programState";
  public static final String LISTED_STOCKS_FILE_PATH = CURRENT_DIRECTORY
          + "/PDPResources/listedStocks.csv";
  public static final String USER_DATA_DIR_PATH = CURRENT_DIRECTORY
          + "/PDPResources/UserData";
  public static final String STOCK_DATA_DIR_PATH = CURRENT_DIRECTORY
          + "/PDPResources/StockData";

}
