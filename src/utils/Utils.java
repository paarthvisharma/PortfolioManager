package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.constants.DirectoryStructureConstants;

import static model.constants.DirectoryStructureConstants.LISTED_STOCKS_FILE_PATH;
import static model.constants.DirectoryStructureConstants.STOCK_DATA_DIR_PATH;
import static model.constants.DirectoryStructureConstants.USER_DATA_DIR_PATH;

/**
 * This class contains static helper methods which can't be
 * concretely added to any of the packages.
 */
public class Utils {

  /**
   * Static method to get the config file.
   */
  public static Map<String, String> getConfigAndProgramState() {
    Map<String, String> config = getConfig();
    config.putAll(getProgramState());
    return config;
  }

  private static Map<String, String> getConfig() {
    Map<String, String> config = new HashMap<String, String>();
    config.put("listedStocks", LISTED_STOCKS_FILE_PATH);
    config.put("userData", USER_DATA_DIR_PATH);
    config.put("stockData", STOCK_DATA_DIR_PATH);
    return config;
  }

  private static Map<String, String> getProgramState() {
    File programState = new File(DirectoryStructureConstants.PROGRAM_STATE_FILE_PATH);
    Map<String, String> programStateMap = new HashMap<>();
    try {
      if (programState.createNewFile()) {
        FileWriter programStateWriter = new FileWriter(
                DirectoryStructureConstants.PROGRAM_STATE_FILE_PATH);
        programStateWriter.write("userId" + ":" + "1\n");
        programStateWriter.close();
      } else {
        Scanner myReader = new Scanner(programState);
        while (myReader.hasNextLine()) {
          String[] contents = myReader.nextLine().split(":");
          programStateMap.put(contents[0], contents[1]);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return programStateMap;
  }
}
