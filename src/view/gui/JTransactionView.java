package view.gui;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.gui.JTransactionController;

/**
 * This class implements the JTransactionView interface and contains the methods to help display
 * Transaction menu.
 */
public class JTransactionView extends JFrame {

  private final DefaultTableModel portfolioTableModel = new DefaultTableModel(0, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 3;
    }
  };

  private final JPanel mainPanel;
  private final JPanel displayPortfoliosPanel;
  private JPanel buySellPanel;
  private JPanel dollarCostAveragingPanel;
  private JLabel outputLabel;
  private JButton backButton;
  private JRadioButton[] radioButtons;
  private JRadioButton selectBuySell;
  private JRadioButton selectDollarCostSaving;
  private JTextField tickerField;
  private JTextField quantityField;
  private JTextField dateField;
  private JTextField startDate;
  private JTextField endDate;
  private JTextField commissionAmount;
  private JTextField interval;
  private JTextField dollarAmount;
  private JTextField tickerFieldForDCA;
  private JButton buyButton;
  private JButton sellButton;
  private JButton addPlan;
  private final JButton loadPortfolio;
  private JButton addToDCA;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JTransactionView() {
    super("Transact in Portfolio");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.placeTopPanel();
    displayPortfoliosPanel = new JPanel();
    mainPanel.add(displayPortfoliosPanel);
    loadPortfolio = new JButton();
    loadPortfolio.setText("Load Portfolio");
    mainPanel.add(loadPortfolio);
    this.placeTransactionSelectorPanel();
    this.placeDCAPanel();
    this.placeBuySellPanel();
    this.placeLogOutput();
    add(mainPanel);
  }

  private void placeTopPanel() {
    JPanel topMostPanel = new JPanel();
    topMostPanel.setLayout(new FlowLayout());
    backButton = new JButton();
    backButton.setActionCommand("back");
    backButton.setText("Back");
    backButton.setHorizontalAlignment(SwingConstants.LEFT);
    JLabel topMostLabel = new JLabel();
    topMostLabel.setText("Select a portfolio from the selection and click \"Load Portfolio\"");
    topMostPanel.add(backButton);
    topMostPanel.add(topMostLabel);
    mainPanel.add(topMostPanel);
  }

  private void placeTransactionSelectorPanel() {
    JPanel transactionSelectorPanel = new JPanel();
    transactionSelectorPanel.setLayout(new GridLayout(1, 2, 2, 2));
    ButtonGroup transactionRadioButtonGroup = new ButtonGroup();

    selectBuySell = new JRadioButton("Buy/Sell");
    transactionRadioButtonGroup.add(selectBuySell);

    selectDollarCostSaving = new JRadioButton("Dollar Cost Averaging");

    transactionRadioButtonGroup.add(selectDollarCostSaving);
    transactionSelectorPanel.add(selectBuySell);
    transactionSelectorPanel.add(selectDollarCostSaving);
    selectBuySell.setSelected(true);
    mainPanel.add(transactionSelectorPanel);
  }

  private void placeDCAPanel() {
    dollarCostAveragingPanel = new JPanel();
    dollarCostAveragingPanel.setBorder(BorderFactory.createTitledBorder("Dollar Cost Averaging"));
    dollarCostAveragingPanel.setLayout(
            new BoxLayout(dollarCostAveragingPanel, BoxLayout.PAGE_AXIS));

    portfolioTableModel.setColumnIdentifiers(new String[]{"Ticker",
        "Stock Name", "Quantity", "Weight %"});
    JTable portfolioTable = new JTable();
    portfolioTable.setModel(portfolioTableModel);
    dollarCostAveragingPanel.add(new JScrollPane(portfolioTable));

    JPanel dollarCostAveragingButtonPanel = new JPanel();
    dollarCostAveragingButtonPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    tickerFieldForDCA = new JTextField(10);
    tickerFieldForDCA.setBorder(BorderFactory.createTitledBorder("Ticker for DCA"));
    c.gridx = 0;
    c.gridy = 0;
    dollarCostAveragingButtonPanel.add(tickerFieldForDCA, c);

    addToDCA = new JButton();
    addToDCA.setText("Add to DCA");
    c.gridx = 2;
    c.gridy = 0;
    dollarCostAveragingButtonPanel.add(addToDCA, c);

    startDate = new JTextField(10);
    startDate.setBorder(BorderFactory.createTitledBorder("Start Date"));
    c.gridx = 0;
    c.gridy = 1;
    dollarCostAveragingButtonPanel.add(startDate, c);

    endDate = new JTextField(10);
    endDate.setBorder(BorderFactory.createTitledBorder("End Date"));
    c.gridx = 1;
    c.gridy = 1;
    dollarCostAveragingButtonPanel.add(endDate, c);

    interval = new JTextField(10);
    interval.setBorder(BorderFactory.createTitledBorder("Interval (days)"));
    c.gridx = 2;
    c.gridy = 1;
    dollarCostAveragingButtonPanel.add(interval, c);

    dollarAmount = new JTextField(10);
    dollarAmount.setBorder(BorderFactory.createTitledBorder("Dollar amount"));
    c.gridx = 0;
    c.gridy = 2;
    dollarCostAveragingButtonPanel.add(dollarAmount, c);

    commissionAmount = new JTextField(10);
    commissionAmount.setBorder(BorderFactory.createTitledBorder("Commission"));
    c.gridx = 1;
    c.gridy = 2;
    dollarCostAveragingButtonPanel.add(commissionAmount, c);

    addPlan = new JButton();
    addPlan.setText("Add Plan");
    c.gridx = 2;
    c.gridy = 2;
    dollarCostAveragingButtonPanel.add(addPlan, c);
    addPlan.setEnabled(false);
    dollarCostAveragingPanel.add(dollarCostAveragingButtonPanel);
    dollarCostAveragingPanel.setVisible(false);
    mainPanel.add(dollarCostAveragingPanel);
  }

  /**
   * Method to set DCA pane.
   */
  public void enableAddPlanDCA(boolean state) {
    addPlan.setEnabled(state);
  }

  private void placeBuySellPanel() {
    buySellPanel = new JPanel();
    buySellPanel.setBorder(BorderFactory.createTitledBorder("Buy Sell Stocks"));
    buySellPanel.setLayout(new GridBagLayout());
    GridBagConstraints bs = new GridBagConstraints();

    tickerField = new JTextField(10);
    tickerField.setBorder(BorderFactory.createTitledBorder("Ticker"));
    bs.gridx = 0;
    bs.gridy = 0;
    buySellPanel.add(tickerField, bs);

    quantityField = new JTextField(10);
    quantityField.setBorder(BorderFactory.createTitledBorder("Quantity"));
    bs.gridx = 1;
    bs.gridy = 0;
    buySellPanel.add(quantityField, bs);

    dateField = new JTextField(10);
    dateField.setBorder(BorderFactory.createTitledBorder("Date"));
    bs.gridx = 2;
    bs.gridy = 0;
    buySellPanel.add(dateField, bs);

    buyButton = new JButton();
    buyButton.setText("Buy");
    bs.gridx = 0;
    bs.gridy = 1;
    buySellPanel.add(buyButton, bs);

    sellButton = new JButton();
    sellButton.setText("Sell");
    bs.gridx = 2;
    bs.gridy = 1;
    buySellPanel.add(sellButton, bs);

    mainPanel.add(buySellPanel);
  }

  private void placeLogOutput() {
    JPanel outputPanel = new JPanel();
    outputPanel.setBorder(BorderFactory.createTitledBorder("Output of operation performed"));
    outputLabel = new JLabel();
    outputPanel.add(outputLabel);
    mainPanel.add(outputPanel);
    add(mainPanel);
  }

  private String getSelectedButton() {
    for (JRadioButton r : radioButtons) {
      if (r.isSelected()) {
        return r.getText();
      }
    }
    return null;
  }

  /**
   * Method to link the view to controller.
   *
   * @param jTransactionController controller for transaction menu.
   */
  public void addFeatures(JTransactionController jTransactionController) {
    buyButton.addActionListener(
        evt -> jTransactionController.buyStock(
                    tickerField.getText(), quantityField.getText(), dateField.getText()));
    sellButton.addActionListener(
        evt -> jTransactionController.sellStock(
                    tickerField.getText(), quantityField.getText(), dateField.getText()));
    addPlan.addActionListener(
        evt -> jTransactionController.createDCAPlan(this.getDCASettings(), getTableData()));
    selectBuySell.addActionListener(
        evt -> jTransactionController.displayBuySellView());
    selectDollarCostSaving.addActionListener((
        evt -> jTransactionController.displayDCAView()));
    backButton.addActionListener(
        evt -> jTransactionController.back());
    loadPortfolio.addActionListener(
        evt -> jTransactionController.selectPortfolioTransaction(getSelectedButton()));
    addToDCA.addActionListener((
        evt -> jTransactionController.addNewStockToDCATable(tickerFieldForDCA.getText())));
    portfolioTableModel.addTableModelListener(new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent e) {
        ArrayList<String> weightsColumn = new ArrayList<>();
        for (int i = 0; i < portfolioTableModel.getRowCount(); i++) {
          weightsColumn.add(String.valueOf(portfolioTableModel.getValueAt(i, 3)));
        }
        jTransactionController.monitorTable(weightsColumn);
      }
    });
  }

  /**
   * Method to clear the user inputs.
   */
  public void clearUserInputs() {
    this.clearTable();
    this.dollarAmount.setText("");
    this.startDate.setText("YYYY-MM-DD");
    this.endDate.setText("YYYY-MM-DD");
    this.outputLabel.setText("");
    this.interval.setText("");
    this.commissionAmount.setText("");
    this.tickerField.setText("");
    this.quantityField.setText("");
    this.dateField.setText("YYYY-MM-DD");
    this.pack();
  }

  /**
   * Method to clear the table.
   */
  public void clearTable() {
    portfolioTableModel.setRowCount(0);
  }

  /**
   * Method to set the visibility of a view.
   */
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

  /**
   * Method to set dollar cost average pane visible.
   */
  public void dcaPanelIsVisible() {
    buySellPanel.setVisible(false);
    dollarCostAveragingPanel.setVisible(true);
    this.pack();
  }

  /**
   * Method to set buy and sell pane visible.
   */
  public void buySellPanelIsVisible() {
    dollarCostAveragingPanel.setVisible(false);
    buySellPanel.setVisible(true);
    this.pack();
  }

  /**
   * Method to set the log output.
   *
   * @param message required message.
   */
  public void setLogOutput(String message) {
    this.outputLabel.setForeground(Color.BLACK);
    this.outputLabel.setText(message);
  }

  /**
   * Method to set the success output.
   *
   * @param message required message.
   */
  public void setSuccessOutput(String message) {
    this.outputLabel.setForeground(new Color(0, 102, 0));
    this.outputLabel.setText(message);
  }

  /**
   * Method to set the failure output.
   *
   * @param message required message.
   */
  public void setFailureOutput(String message) {
    this.outputLabel.setForeground(Color.RED);
    this.outputLabel.setText(message);
  }

  /**
   * Method to display radio buttons.
   *
   * @param portfolios required portfolios.
   */
  public void displayRadioButtonsForPortfolio(String portfolios) {
    this.selectPortfolioPanel(portfolios);
  }

  /**
   * Method to add row to dollar cost average table.
   *
   * @param row value.
   */
  public void addRowToDCATable(String[] row) {
    portfolioTableModel.addRow(new String[]{row[0], row[1], row[2], "0"});
  }

  private java.util.List<java.util.List<String>> getTableData() {
    java.util.List<java.util.List<String>> tableData = new ArrayList<>();
    for (int i = 0; i < portfolioTableModel.getRowCount(); i++) {
      List<String> row = new ArrayList<>();
      for (int j = 0; j < portfolioTableModel.getColumnCount(); j++) {
        row.add(String.valueOf(portfolioTableModel.getValueAt(i, j)));
      }
      tableData.add(row);
    }
    return tableData;
  }

  private Map<String, String> getDCASettings() {
    HashMap<String, String> dcaSettings = new HashMap<>();
    dcaSettings.put("startDate", startDate.getText());
    dcaSettings.put("endDate", endDate.getText());
    dcaSettings.put("interval", interval.getText());
    dcaSettings.put("dollarAmount", dollarAmount.getText());
    dcaSettings.put("commission", commissionAmount.getText());
    return dcaSettings;
  }

  private void selectPortfolioPanel(String portfoliosText) {
    displayPortfoliosPanel.removeAll();
    ArrayList<String> portfolios = new ArrayList<>(Arrays.asList(portfoliosText.split("\n")));
    ButtonGroup rGroup = new ButtonGroup();
    JPanel radioPanel = new JPanel();
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    radioButtons = new JRadioButton[portfolios.size()];
    for (int i = 0; i < portfolios.size(); i++) {
      radioButtons[i] = new JRadioButton(portfolios.get(i));
      rGroup.add(radioButtons[i]);
      radioPanel.add(radioButtons[i]);
    }
    displayPortfoliosPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    JScrollPane scrollableRadioPanel = new JScrollPane(radioPanel);
    displayPortfoliosPanel.add(scrollableRadioPanel);
  }
}
