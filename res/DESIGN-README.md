# Design of stockPart3

## Changes from the first part of the project.

## Changes from stockPart1 to stockPart2:
* The Menu flow was changed.
  * Previously the User had to type the user ID to perform any operation. But with the new layout the user ID is only asked once.
  * In the new design the user logs in and later on does not have to enter the user ID.
* The controller was broken into multiple classes for easier management and testing.
  * Earlier he whole controller had to be tested as a whole. With the new changes each class can be tested individually.
* The earlier `portfolio` interface have been split into `Rigid` and `Flexible` portfolios.
  * Earlier we just had one interface for `portfolio`. That interface has continued, but now we have
  added two more interfaces which extend the parent interface (`portfolio`).
  * The `Rigid` and `Flexible` portfolios have class specific method signatures stored inside them.
  This was done to make handling portfolios easier and to use dynamic dispatch for the common functionalities.
* The `Userhandler` responsible for reading XML files and loading user was made to handle `Flexible` portfolios.
* The XML file format was changed to accommodate the commission rate, and two types of portfolios (`Flexible` and `Rigid`)
* A transaction related to the `Flexible` portfolio are being stored in a CSV file to help track and validate non-chronological
transactions.
* The design is now integrated with the Alphavantage API to fetch data from the Alphavantage API server.

## Changes from stockPart2 to stockPart3:
* Implemented a fully functional GUI and in order to accommodate that added gui packages in controller as well as view.
* Added functions to model to implement dollar cost averaging and stock performance line chart.

## Overview of the design.
* The whole project is split into 4 packages.
  * controller - contains the controller related classes according to the MVC model.
  * model - contains a wrapper class called model which delegates requests from the controller
  to the other classes present like - portfolioManager, user, portfolio, stock.
    * StockImpl and Stock - These are the classes and interface to represent a stock.
    * RigidPortfolio and RigidPortfolioImpl - These the classes and interface to represent a rigid portfolio.
    * FlexiblePortfolio and FlexiblePortfolioImpl - These the classes and interface to represent a flexible portfolio.
    * User and UserImpl - These are interface and classes to represent a user.
    * StockApi and AlphaVantageApi - These are interface and classes to represent a stock API.
    * PortfolioManager - This class manages the users. 
  * utils - contains the classes that are in general used by multiple packages.
  * view - contains the classes which are responsible for displaying user facing text to the User.


## Controller
* The controller takes in 3 parameters while being initialised.
  * input from `System.in` via a `Scanner` object.
  * An initialised model object
  * An initialised view object.

## Model
* The model takes in a single `HashMap` config file as an input.
* The `config` file consists of the directory structure where the `UserData`, `StockData` and `listedStock` are present.
* The model delegates appropriate tasks to respective classes and return a `StatusObject`.<br>
  * The status object contains a `StatusMessage` indicating the status as a text.
  * It also contains a `StatusCode` which can be positive for `Success` and negative for `failure`.
  * It then contains the object that was to be returned under `ReturnedObject`.


## View
* The view takes in an object of type `PrintStream`.

## ProgramManagerRunner
* This is contains the static main method which initialises the `view`, `controller` and `model`.
* This then goes on to call the controllers infinite loop which runs the program.

