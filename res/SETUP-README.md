# Setup Up of stockPart2

## Initial Checks
* Make sure the `JAR` file is present along with the files and directories mentioned below. (ideally this should be 
present in the res folder submitted). Unzip PDPResources.zip and place it next to the jar file.
  * Directory named PDPResources containing the following.
    * File: `listedStocks.csv` which contains the listed stocks ticker in a csv format.
    * File: `programState` this saves the state of the which is later used by the program to assign UserId to users.
    * Directory: `StockData`
      * This contains the `.csv` files for all the stocks listed in `listedStocks.csv`.
      * the filenames are in the <`TickerSymbol`>.csv format.
    * Directory: `UserData`
      * This is an empty directory (initially) but stores User.xml files as and when they are created.
      * This also stores the transaction CSV files in format <`PortfolioID`>.csv.
      * The xml files are in the <`UserID`>.xml format and the transaction csv is in <`PortfolioID`>.csv format.
* Running `java -jar stocksPart3.jar` does not give an error.

## Running the program
* As these are a complex number of steps to follow, please follow the prompts appearing on the screen.
* After initial check run the `jar` file using the following command `java -jar stocksPart3.jar`
* Follow the following steps to create a User having a portfolio and purchase stocks for the portfolio with a DCA in it.
  * Fill in the `Enter First Name` and `Enter Last Name` name in the UI and click `Create User`.
  * This should create a user. Note the User ID from the `ouput logs` on the screen.
  * To login enter the `User ID` in the `Enter User ID` and press `Login`. This should log you in and show the next menu.
  * Click on `Create Portfolio` button. This will take you to a different screen.
  * Enter the name of the protfolio in `Name of portfolio1` text field.
  * Enter in `Ticker Symbol` "goog", `Quantity` "10", `Date` "2020-05-05" and click `Add Stock`. This will purchase the stock once.
  * Now to add stock to DCA, enter the ticker symbol in `Ticker for DCA`. For now enter "aapl". Now click on `Add to DCA`.
  * Enter the ticker symbol in `Ticker for DCA`. For now enter "csco". Now click on `Add to DCA`.
  * Now lets set the weights for each aapl. Double click on the weight column for appl, set it to `50`.
  * Now lets set the weights for each csco. Double click on the weight column for csco, set it to `50`.
  * Click in a different box in the table to complete the weight entry.
  * Now you should be able to enter other details.
    * Start date `2020-01-01`, End date `2020-05-05`, Interval `30`, Dollar Amount `5000`, Commission `10`.
    * Click on `Create Portfolio` to create the portfolio. This will take some time to create the portfolio.
    * Press the back button.
  * Click on `View Portfolio`.
    * Select the created portfolio radio button.
    * Enter a date on which the portfolio needs to be viewed. `2020-06-01`.
    * Click on `View portfolio`. This displays the portfolio.
    * Click on `back`.
  * Click on `Plot performance`.
    * Select the created portfolio radio button and click `Load Portfolio`.
    * Select the date range for which the performance needs to be plotted.
    * Click `Plot graph`. This should plot the graph in the new window.
    * Close the window. click on `back`.
    * This should take you to the `Portfolio menu`.


Java docs are present under res/docs

# Sample XML file.
```XML
<?xml version="1.0" encoding="UTF-8" ?>
<user userId="2">
  <firstName>sdv</firstName>
  <lastName>saf</lastName>
  <commission>1.0</commission>
  <rigidPortfolios>
    <rigidPortfolio portfolioId="1">
      <portfolioName>Rigid Test 1</portfolioName>
      <stocks>
        <stock ticker="goog">
          <stockName>Alphabet Inc - Class C</stockName>
          <stockQuantity>100</stockQuantity>
        </stock>
      </stocks>
    </rigidPortfolio>
  </rigidPortfolios>
  <flexiblePortfolios>
    <flexiblePortfolio portfolioId="1">
      <portfolioName>Flexible Test 1</portfolioName>
      <stocks>
        <stock ticker="goog">
          <stockName>Alphabet Inc - Class C</stockName>
          <stockQuantity>40</stockQuantity>
          <date>2016-01-01</date>
        </stock>
        <stock ticker="amzn">
          <stockName>Amazon.com Inc</stockName>
          <stockQuantity>60</stockQuantity>
          <date>2016-06-06</date>
        </stock>
      </stocks>
      <plans></plans>
    </flexiblePortfolio>
  </flexiblePortfolios>
</user>
```

## Sample transaction CSV file
To be named as {UserID_PortfolioID}.csv when the Flexible portfolio is to be loaded.
The file should be present in the same directory as the Users XML File.
```csv
goog, 20, BUY, 2016-01-01, 1.0, true
amzn, 30, BUY, 2016-06-06, 1.0, true
```
## Supported list of stocks
```csv
['ATVI', 'ADBE', 'AMD', 'ALGN', 'ALXN', 'AMZN', 'AMGN', 'AAL', 'ADI', 'AAPL', 'AMAT', 'ASML', 'ADSK', 
'ADP', 'AVGO', 'BIDU', 'BIIB', 'BMRN', 'CDNS', 'CERN', 'CHKP', 'CHTR', 'TCOM', 'CTAS', 'CSCO', 'CTXS', 
'CMCSA', 'COST', 'CSX', 'CTSH', 'DLTR', 'EA', 'EBAY', 'EXC', 'EXPE', 'FAST', 'FB', 'FISV', 'GILD', 'GOOG', 
'GOOGL', 'HAS', 'HSIC', 'ILMN', 'INCY', 'INTC', 'INTU', 'ISRG', 'IDXX', 'JBHT', 'JD', 'KLAC', 'KHC',
'LRCX', 'LBTYA', 'LBTYK', 'LULU', 'MELI', 'MAR', 'MCHP', 'MDLZ', 'MNST', 'MSFT', 'MU', 'MXIM', 'MYL',
'NTAP', 'NFLX', 'NTES', 'NVDA', 'NXPI', 'ORLY', 'PAYX', 'PCAR', 'BKNG', 'PYPL', 'PEP', 'QCOM', 'REGN',
'ROST', 'SIRI', 'SWKS', 'SBUX', 'NLOK', 'SNPS', 'TTWO', 'TSLA', 'TXN', 'TMUS', 'ULTA', 'UAL', 'VRSN', 
'VRSK', 'VRTX', 'WBA', 'WDC', 'WDAY', 'WYNN', 'XEL', 'XLNX']
```