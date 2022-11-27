package view.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.GUI.JCreatePortfolioController;

public class JCreatePortfolioViewImpl extends JFrame implements JCreatePortfolioView {

  private JPanel mainPanel;
  private JTextField stockTicker;
  private JTextField stockQuantity;
  private JTextField purchaseDate;
  private JTextField startDate;
  private JTextField endDate;
  private JTextField interval;
  private JTextField portfolioName;
  private JTextField dollarAmount;
  private JButton backButton;
  private JButton addStock;
  private JButton createPortfolio;
  private JTable portfolioTable;
  private JLabel logs;

  private DefaultTableModel portfolioTableModel = new DefaultTableModel(0, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return column == 3;
    }
  };

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
    portfolioTableModel.setColumnIdentifiers(new String[]{"Ticker", "Quantity", "Date of purchase",
            "Weight %"});
    portfolioTable = new JTable();

    portfolioTable.setModel(portfolioTableModel);
    stocksPanel.add(new JScrollPane(portfolioTable));

    mainPanel.add(stocksPanel);
  }

  private void placeDollarCostAveragingPanel() {
    JPanel dollarCostAveraging = new JPanel();
    dollarCostAveraging.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    startDate = new JTextField(10);
    startDate.setBorder(BorderFactory.createTitledBorder("Start Date"));
    c.gridx = 0;
    c.gridy = 0;
    dollarCostAveraging.add(startDate, c);
    startDate.setEnabled(false);

    endDate = new JTextField(10);
    endDate.setBorder(BorderFactory.createTitledBorder("End Date"));
    c.gridx = 1;
    c.gridy = 0;
    dollarCostAveraging.add(endDate, c);
    endDate.setEnabled(false);

    interval = new JTextField(10);
    interval.setBorder(BorderFactory.createTitledBorder("Interval (days)"));
    c.gridx = 2;
    c.gridy = 0;
    dollarCostAveraging.add(interval, c);
    interval.setEnabled(false);

    dollarAmount = new JTextField(10);
    dollarAmount.setBorder(BorderFactory.createTitledBorder("Dollar amount"));
    c.gridx = 0;
    c.gridy = 1;
    dollarCostAveraging.add(dollarAmount, c);
    dollarAmount.setEnabled(false);

    createPortfolio = new JButton();
    createPortfolio.setText("Create Portfolio");
    c.gridx = 2;
    c.gridy = 1;
    dollarCostAveraging.add(createPortfolio, c);
    mainPanel.add(dollarCostAveraging);
  }

  private void placeLogsPanel() {
    JPanel logsPanel  = new JPanel();
    logsPanel.setLayout(new GridLayout(1,1));
    logsPanel.setBorder(BorderFactory.createTitledBorder("Output logs"));
    logs = new JLabel();
    logsPanel.add(logs);

    mainPanel.add(logsPanel);
  }


  @Override
  public void addFeatures(JCreatePortfolioController jCreatePortfolioController) {
    addStock.addActionListener(evt -> jCreatePortfolioController.addStock(stockTicker.getText(), stockQuantity.getText(), purchaseDate.getText()));
    backButton.addActionListener(evt -> jCreatePortfolioController.back());
    createPortfolio.addActionListener(evt -> jCreatePortfolioController.createPortfolio(portfolioName.getText()));
    portfolioTableModel.addTableModelListener(new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent e) {
        ArrayList<String> weightsColumn = new ArrayList<>();
        for (int i=0; i < portfolioTableModel.getRowCount(); i++) {
          weightsColumn.add(String.valueOf(portfolioTableModel.getValueAt(i, 3)));
        }
        jCreatePortfolioController.monitorTable(weightsColumn);
      }
    });
  }

//  private String[][] getTableData() {
//    String[][] toReturn = new String[portfolioTableModel.getRowCount()][4];
//    for (int i=0; i < portfolioTableModel.getRowCount(); i++) {
//      toReturn[i][0] = (String) portfolioTableModel.getDataVector().elementAt(i).elementAt(0);
//      toReturn[i][1] = (String) portfolioTableModel.getDataVector().elementAt(i).elementAt(1);
//      toReturn[i][2] = (String) portfolioTableModel.getDataVector().elementAt(i).elementAt(2);
//      toReturn[i][3] = (String) portfolioTableModel.getDataVector().elementAt(i).elementAt(3);
//    }
//    return toReturn;
//  }

  @Override
  public void isVisible(boolean state) {
    this.setVisible(state);
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
    interval.setEnabled(state);
  }

  @Override
  public void clearUserInputs() {
    stockTicker.setText("");
    stockQuantity.setText("");
    purchaseDate.setText("");
    startDate.setText("");
    endDate.setText("");
    interval.setText("");
    dollarAmount.setText("");
    portfolioName.setText("");
    logs.setText("");
    for (int i = 0; i < portfolioTableModel.getRowCount(); i++) {
      portfolioTableModel.removeRow(0);
    }
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
