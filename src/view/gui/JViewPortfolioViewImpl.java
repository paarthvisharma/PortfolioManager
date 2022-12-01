package view.gui;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

import controller.gui.JViewPortfolioController;

/**
 * This class implements the JViewPortfolioView interface and contains the methods to help display
 * view portfolio menu.
 */
public class JViewPortfolioViewImpl extends JFrame implements JViewPortfolioView {

  private DefaultTableModel portfolioTableModel = new DefaultTableModel(0, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };

  private JPanel mainPanel;
  private JPanel displayPortfoliosPanel;
  private JTextField dateOfViewing;
  private JButton backButton;
  private JButton viewPortfolioButton;
  private JRadioButton[] radioButtons;
  private JLabel outputLabel;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JViewPortfolioViewImpl() {
    super("View Portfolio");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    this.placeTopPanel();
    displayPortfoliosPanel = new JPanel();
    mainPanel.add(displayPortfoliosPanel);
    this.placeDateAndButtonPanel();

    portfolioTableModel.setColumnIdentifiers(new String[]{"Ticker",
        "Stock Name", "Quantity", "Date of purchase"});
    JTable portfolioTable = new JTable();
    portfolioTable.setModel(portfolioTableModel);

    mainPanel.add(new JScrollPane(portfolioTable));
    this.placeLogOutput();
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

  private void placeDateAndButtonPanel() {
    JPanel dateAndButtonPanel = new JPanel();
    dateAndButtonPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JPanel datePanel = new JPanel();
    dateOfViewing = new JTextField(10);
    dateOfViewing.setText("YYYY-MM-DD");
    dateOfViewing.setBorder(BorderFactory.createTitledBorder("Date"));
    datePanel.add(dateOfViewing);
    dateAndButtonPanel.add(datePanel);
    JPanel valueButtonPanel = new JPanel();
    viewPortfolioButton = new JButton();
    viewPortfolioButton.setText("View Portfolio");
    valueButtonPanel.add(viewPortfolioButton);
    dateAndButtonPanel.add(valueButtonPanel);
    mainPanel.add(dateAndButtonPanel);
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

  @Override
  public void displayRadioButtonsForPortfolio(String portfolios) {
    this.selectPortfolioPanel(portfolios);
  }


  @Override
  public void addFeatures(JViewPortfolioController jViewPortfolioController) {
    backButton.addActionListener(evt -> jViewPortfolioController.back());
    viewPortfolioButton.addActionListener(
            evt -> jViewPortfolioController.viewPortfolio(dateOfViewing.getText(),
                    this.getSelectedButton()));
  }

  @Override
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

  @Override
  public void setLogOutput(String message) {
    this.outputLabel.setForeground(Color.BLACK);
    this.outputLabel.setText(message);
  }

  @Override
  public void setSuccessOutput(String message) {
    this.outputLabel.setForeground(new Color(0, 102, 0));
    this.outputLabel.setText(message);
  }

  @Override
  public void setFailureOutput(String message) {
    this.outputLabel.setForeground(Color.RED);
    this.outputLabel.setText(message);
  }

  @Override
  public void addRowToTable(String[] row) {
    portfolioTableModel.addRow(new String[]{row[0], row[1], row[2], row[3]});
  }

  @Override
  public void clearUserInputs() {
    this.dateOfViewing.setText("YYYY-MM-DD");
    this.outputLabel.setText("");
    this.clearTable();
  }

  @Override
  public void clearTable() {
    for (int i = 0; i < portfolioTableModel.getRowCount(); i++) {
      portfolioTableModel.removeRow(i);
    }
  }
}
