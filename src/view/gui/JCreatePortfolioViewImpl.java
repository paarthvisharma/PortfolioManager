package view.gui;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import controller.gui.JCreatePortfolioController;

/**
 * This class implements the JCreatePortfolioView interface and contains the methods to create
 * portfolio menu.
 */
public class JCreatePortfolioViewImpl extends JFrame implements JCreatePortfolioView {

  private final JPanel mainPanel;
  private JTextField stockTicker;
  private JTextField stockQuantity;
  private JTextField purchaseDate;
  private JTextField startDate;
  private JTextField endDate;
  private JTextField interval;
  private JTextField portfolioName;
  private JTextField dollarAmount;
  private JTextField commissionAmount;
  private JTextField tickerFieldForDCA;
  private JButton backButton;
  private JButton addStock;
  private JButton createPortfolio;
  private JButton addToDCA;
  private JLabel logs;

  private final DefaultTableModel portfolioTableModel = new DefaultTableModel(0, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 3;
    }
  };

  /**
   * Constructor for the class to set up the initial view.
   */
  public JCreatePortfolioViewImpl() {
    super("Create Portfolio");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    this.placeTopPanel();
    this.placePortfolioNamePanel();
    this.placeStockSelectionPanel();
    this.placeDollarCostAveragingPanel();
    this.placeLogsPanel();
    add(mainPanel);
    //    pack();
  }

  private void placePortfolioNamePanel() {
    JPanel portfolioNamePanel = new JPanel();
    portfolioName = new JTextField(20);
    portfolioName.setBorder(BorderFactory.createTitledBorder("Name of portfolio"));
    portfolioNamePanel.add(portfolioName);
    mainPanel.add(portfolioNamePanel);
  }

  private void placeTopPanel() {
    JPanel topMostPanel = new JPanel();
    topMostPanel.setLayout(new FlowLayout());
    backButton = new JButton();
    backButton.setActionCommand("back");
    backButton.setText("Back");
    backButton.setHorizontalAlignment(SwingConstants.LEFT);
    JLabel topMostLabel = new JLabel();
    topMostLabel.setText("Fill in the appropriate information");
    topMostPanel.add(backButton);
    topMostPanel.add(topMostLabel);
    mainPanel.add(topMostPanel);
  }

  private void placeStockSelectionPanel() {
    JPanel individualStockPanel = new JPanel();
    stockTicker = new JTextField(10);
    stockTicker.setBorder(BorderFactory.createTitledBorder("Ticker symbol"));

    stockQuantity = new JTextField(10);
    stockQuantity.setBorder(BorderFactory.createTitledBorder("Quantity"));

    purchaseDate = new JTextField(10);
    purchaseDate.setText("YYYY-MM-DD");
    purchaseDate.setBorder(BorderFactory.createTitledBorder("Date"));

    addStock = new JButton();
    addStock.setText("Add Stock");
    addStock.setActionCommand("add stock");

    individualStockPanel.add(stockTicker);
    individualStockPanel.add(stockQuantity);
    individualStockPanel.add(purchaseDate);
    individualStockPanel.add(addStock);

    JPanel stocksPanel = new JPanel();
    stocksPanel.setLayout(new BoxLayout(stocksPanel, BoxLayout.PAGE_AXIS));
    stocksPanel.add(individualStockPanel);
    portfolioTableModel.setColumnIdentifiers(
            new String[]{"Ticker", "Quantity", "Date of purchase", "Weight %"});
    JTable portfolioTable = new JTable();

    portfolioTable.setModel(portfolioTableModel);
    stocksPanel.add(new JScrollPane(portfolioTable));

    mainPanel.add(stocksPanel);
  }

  private void placeDollarCostAveragingPanel() {
    JPanel dollarCostAveraging = new JPanel();
    dollarCostAveraging.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    tickerFieldForDCA = new JTextField(10);
    tickerFieldForDCA.setBorder(BorderFactory.createTitledBorder("Ticker for DCA"));
    c.gridx = 0;
    c.gridy = 0;
    dollarCostAveraging.add(tickerFieldForDCA, c);

    addToDCA = new JButton();
    addToDCA.setText("Add to DCA");
    c.gridx = 2;
    c.gridy = 0;
    dollarCostAveraging.add(addToDCA, c);

    startDate = new JTextField(10);
    startDate.setBorder(BorderFactory.createTitledBorder("Start Date"));
    c.gridx = 0;
    c.gridy = 1;
    dollarCostAveraging.add(startDate, c);
    startDate.setEnabled(false);

    endDate = new JTextField(10);
    endDate.setBorder(BorderFactory.createTitledBorder("End Date"));
    c.gridx = 1;
    c.gridy = 1;
    dollarCostAveraging.add(endDate, c);
    endDate.setEnabled(false);

    interval = new JTextField(10);
    interval.setBorder(BorderFactory.createTitledBorder("Interval (days)"));
    c.gridx = 2;
    c.gridy = 1;
    dollarCostAveraging.add(interval, c);
    interval.setEnabled(false);

    dollarAmount = new JTextField(10);
    dollarAmount.setBorder(BorderFactory.createTitledBorder("Dollar amount"));
    c.gridx = 0;
    c.gridy = 2;
    dollarCostAveraging.add(dollarAmount, c);
    dollarAmount.setEnabled(false);

    commissionAmount = new JTextField(10);
    commissionAmount.setBorder(BorderFactory.createTitledBorder("Commission"));
    c.gridx = 1;
    c.gridy = 2;
    dollarCostAveraging.add(commissionAmount, c);
    commissionAmount.setEnabled(false);

    createPortfolio = new JButton();
    createPortfolio.setText("Create Portfolio");
    c.gridx = 2;
    c.gridy = 2;
    dollarCostAveraging.add(createPortfolio, c);
    mainPanel.add(dollarCostAveraging);
  }

  private void placeLogsPanel() {
    JPanel logsPanel = new JPanel();
    logsPanel.setLayout(new GridLayout(1, 1));
    logsPanel.setBorder(BorderFactory.createTitledBorder("Output logs"));
    logs = new JLabel();
    logsPanel.add(logs);

    mainPanel.add(logsPanel);
  }


  @Override
  public void addFeatures(JCreatePortfolioController jCreatePortfolioController) {
    addStock.addActionListener(
        evt -> jCreatePortfolioController.addStock(stockTicker.getText(),
              stockQuantity.getText(), purchaseDate.getText()));
    backButton.addActionListener(evt -> jCreatePortfolioController.back());
    createPortfolio.addActionListener(
        evt -> jCreatePortfolioController.createPortfolio(portfolioName.getText(),
                    this.getDCASettings(), getTableData()));
    addToDCA.addActionListener(
        evt -> jCreatePortfolioController.addStockForDCA(tickerFieldForDCA.getText()));
    portfolioTableModel.addTableModelListener(new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent e) {
        ArrayList<String> weightsColumn = new ArrayList<>();
        for (int i = 0; i < portfolioTableModel.getRowCount(); i++) {
          weightsColumn.add(String.valueOf(portfolioTableModel.getValueAt(i, 3)));
        }
        jCreatePortfolioController.monitorTable(weightsColumn);
      }
    });
  }

  private List<List<String>> getTableData() {
    List<List<String>> tableData = new ArrayList<>();
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

  @Override
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

  @Override
  public void addRowToTable(String[] row) {
    portfolioTableModel.addRow(new String[]{row[0], row[1], row[2], "0"});
  }

  @Override
  public void dollarCostAveragingEnabled(boolean state) {
    startDate.setEnabled(state);
    endDate.setEnabled(state);
    dollarAmount.setEnabled(state);
    commissionAmount.setEnabled(state);
    interval.setEnabled(state);
  }

  @Override
  public void enableCreatePortfolioButton(boolean state) {
    createPortfolio.setEnabled(state);
  }

  @Override
  public void clearUserInputs() {
    stockTicker.setText("");
    stockQuantity.setText("");
    purchaseDate.setText("");
    startDate.setText("YYYY-MM-DD");
    endDate.setText("YYYY-MM-DD");
    interval.setText("");
    dollarAmount.setText("");
    portfolioName.setText("");
    commissionAmount.setText("");
    logs.setText("");
    portfolioTableModel.setRowCount(0);
  }

  @Override
  public void setLogOutput(String message) {
    this.logs.setForeground(Color.BLACK);
    this.logs.setText(message);
  }

  @Override
  public void setSuccessOutput(String message) {
    this.logs.setForeground(new Color(0, 102, 0));
    this.logs.setText(message);
  }

  @Override
  public void setFailureOutput(String message) {
    this.logs.setForeground(Color.RED);
    this.logs.setText(message);
  }

}
