package view.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import controller.gui.JCalculateValueController;

/**
 * This class implements the JCalculateValueView interface and contains the methods to display
 * Value menu.
 */
public class JCalculateValueViewImpl extends JFrame implements JCalculateValueView {

  private JPanel mainPanel;
  private JPanel displayPortfoliosPanel;
  private JRadioButton[] radioButtons;

  private JTextField dateOfValuation;
  private JLabel outputLabel;
  private JButton backButton;
  private JButton valuatePortfolioButton;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JCalculateValueViewImpl() {
    super();
    setTitle("Valuate Portfolio");
    setSize(400, 400);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.setTopMostPanel();
    displayPortfoliosPanel = new JPanel();
    mainPanel.add(displayPortfoliosPanel);
    this.placeDateAndButtonPanel();
    this.placeLogOutput();
  }

  private void setTopMostPanel() {
    JPanel topMostPanel = new JPanel();
    topMostPanel.setLayout(new FlowLayout());
    backButton = new JButton();
    backButton.setActionCommand("back");
    backButton.setText("Back");
    backButton.setHorizontalAlignment(SwingConstants.LEFT);
    JLabel topMostLabel = new JLabel();
    topMostLabel.setText("Select a portfolio from the below list");
    topMostPanel.add(backButton);
    topMostPanel.add(topMostLabel);
    mainPanel.add(topMostPanel);
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

  private void placeDateAndButtonPanel() {
    JPanel dateAndButtonPanel = new JPanel();
    dateAndButtonPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JPanel datePanel = new JPanel();
    dateOfValuation = new JTextField(10);
    dateOfValuation.setText("YYYY-MM-DD");
    dateOfValuation.setBorder(BorderFactory.createTitledBorder("Date"));
    datePanel.add(dateOfValuation);
    dateAndButtonPanel.add(datePanel);
    JPanel valueButtonPanel = new JPanel();
    valuatePortfolioButton = new JButton();
    valuatePortfolioButton.setText("Calculate value");
    valueButtonPanel.add(valuatePortfolioButton);
    dateAndButtonPanel.add(valueButtonPanel);
    mainPanel.add(dateAndButtonPanel);
  }

  private void placeLogOutput() {
    JPanel outputPanel = new JPanel();
    outputPanel.setBorder(BorderFactory.createTitledBorder("Output of operation performed"));
    outputLabel = new JLabel();
    outputPanel.add(outputLabel);
    mainPanel.add(outputPanel);
    add(mainPanel);
  }

  @Override
  public void addFeatures(JCalculateValueController jCalculateValueController) {
    this.backButton.addActionListener(evt -> jCalculateValueController.back());
    this.valuatePortfolioButton.addActionListener(evt ->
            jCalculateValueController.calculatePortfolioValue(dateOfValuation.getText(),
                    this.getSelectedButton()));
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
  public void displayRadioButtonsForPortfolio(String portfolios) {
    this.selectPortfolioPanel(portfolios);
  }
}
