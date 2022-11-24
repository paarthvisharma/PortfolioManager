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
* Running `java -jar stocksPart2.jar` does not give an error.

## Running the program
* As these are a complex number of steps to follow, please follow the prompts appearing on the screen.
* After initial check run the `jar` file using the following command `java -jar stocksPart2.jar`
* Follow the following steps to create a User having a portfolio and purchase stocks for the portfolio
* of 3 different companies on different dates and then query cost basis and value.
  * Press `1\n` in the Main Menu. (Selects the Create User Option).
  * When prompted in the User Menu, press `1\n`. (Selects the Create User Manually Option).
  * When prompted enter the FirstName and Lastname seperated with a `Space` `FirstName LastName\n`.
    <br> note the UserId returned by the display and then press any key to continue.
  * Press `3\n` to go back to the Main Menu.
  * Now you can login with the user created by entering `2\n`.(Selects Login using UserId Option).
  * Enter userId created : `1\n` (for first user). It will display success message of Retrieved user successfully. 
  * Press any key to continue.
  * Press `2\n` to create a portfolio.
  * Press `2\n` to create a flexible portfolio. (Flexible portfolios support buying and selling of stocks).
  * Enter the name of the portfolio that you want to create. Let's enter `Education\n` for this example.
  * Now lets enter the ticker symbol of the stock. For this example lets enter `goog`.
  * Lets now add the quantity of stock. For this example lets enter `10`.
  * Lets now add the date of purchase of stock. For this example lets enter `2020-01-01`.
  * Press any key to continue.
  * Press `2\n`. This will successfully create a portfolio with 10 stocks of goog. Press any key to continue.
  * Press `3\n` to go back to Portfolio menu.
  * Now let's buy stocks for your portfolio.
  * Press `6\n` option to Buy/Sell stocks.
  * Enter the portfolio ID. In this case it would be `1\n` as we created one flexible portfolio. Press any key to continue.
  * Press `1\n` to buy stock.
  * Now lets enter the ticker symbol of the stock. For this example lets enter `amzn`.
  * Lets now add the quantity of stock. For this example lets enter `20`.
  * Lets now add the date of purchase of stock. For this example lets enter `2022-02-02`. This should give a success message for buying 20 stocks of amzn.
  * Press any key to continue.
  * Again press `1\n` to buy stock.
  * Now lets enter the ticker symbol of the stock. For this example lets enter `csco`.
  * Lets now add the quantity of stock. For this example lets enter `30`.
  * Lets now add the date of purchase of stock. For this example lets enter `2022-02-05`. This should give a success message for buying 30 stocks of csco.
  * Press any key to continue.
  * Again press `1\n` to buy the third company's stock.
  * Now lets enter the ticker symbol of the stock. For this example lets enter `adi`.
  * Lets now add the quantity of stock. For this example lets enter `5`.
  * Lets now add the date of purchase of stock. For this example lets enter `2021-02-05`. This should give a success message for buying 5 stocks of adi.
  * Press any key to continue.
  * Press `3\n` to go back to Portfolio menu.
  * Now let's query the cost basis of the Education portfolio we just created. Press `5\n` to get the Cost basis.
  * Select the portfolio ID for Education portfolio so enter `1\n`.
  * Enter the date required in the following format : `2022-10-05\n`.
  * This should display the cost basis on the specific date entered. Now press any key to continue.
  * Now let's query the cost basis on a different date. To do this, we will follow the same steps.
  * Enter `5\n`, then enter the portfolio ID `1\n` and date `2021-10-05\n`.
  * Press any key to continue.
  * Now to calculate the Value, Press `4\n`.
  * Press `2\n` to select the flexible portfolio type.
  * Enter the portfolio ID, which is `1\n` in this case.
  * Enter the date on which you want to query the value in the format `2022-03-05\n`.
  * Press any key to continue.
  * To view value on another date, select `2\n` for flexible portfolio type again.
  * Again enter `1\n` to select the education portfolio and then enter date on which you want to query the value in format `2000-03-05\n`.
  * This should display the value. Please note value for flexible portfolio would be 0 if checked on any date before purchase of stocks. 
  * Press any key to continue.
  * Press `3\n` to go back.
  * Press `8\n` to go back.
  * Press `3\n` to exit.


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