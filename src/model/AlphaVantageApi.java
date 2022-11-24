package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static utils.Utils.getConfigAndProgramState;

/**
 * This is a class for AlphaVantage Api. The methods associated with this class are being used
 * to pull stock related information for the StockProgram.
 */

public class AlphaVantageApi implements StockApi {
  String apiKey = "7YFZ9O7RFHZXQGV2";
  URL url = null;

  Map<String, String> config = getConfigAndProgramState();

  private StringBuilder stringBuilderHelper() {
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No data found");
    }
    return output;
  }

  @Override
  public void fetchAndCreateStockData(String ticker) {

    String stockSymbol = ticker;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    StringBuilder output = this.stringBuilderHelper();

    String filename = config.get("stockData") + "/" + ticker.toLowerCase() + ".csv";
    File f = new File(filename);
    String absolute = f.getAbsolutePath();
    try {
      Files.writeString(Path.of(absolute), output.toString(),
              StandardCharsets.UTF_8);
    } catch (IOException ex) {
      //
    }

  }

  @Override
  public void updateListedStocks() throws IOException {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=LISTING_STATUS"
              + "&state=active"
              + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    StringBuilder output = this.stringBuilderHelper();

    File f = new File(this.config.get("listedStocks.csv"));
    String absolute = f.getAbsolutePath();
    Files.writeString(Path.of(absolute), output.toString(),
            StandardCharsets.UTF_8);
  }
}



