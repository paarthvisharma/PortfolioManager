package view.gui;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import controller.gui.JSetCommissionController;

/**
 * This class implements the JSetCommissionForUserView interface and contains the methods
 * to help display Set Commission menu.
 */
public class JSetCommissionForUserView extends JFrame {

  private final JPanel mainPanel;
  private JButton backButton;
  private JLabel outputLabel;
  private JButton setCommissionButton;

  private JTextArea commissionAmount;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JSetCommissionForUserView() {
    super();
    setTitle("Set Commission");
    setSize(400, 400);
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.setTopMostPanel();
    this.inputCommissionPanel();
    this.buttonPanel();
    //    this.placeLogOutput();
    this.logOutputPanel();
  }

  private void setTopMostPanel() {
    JPanel topMostPanel = new JPanel();
    topMostPanel.setLayout(new FlowLayout());
    backButton = new JButton();
    backButton.setActionCommand("back");
    backButton.setText("Back");
    backButton.setHorizontalAlignment(SwingConstants.LEFT);
    JLabel topMostLabel = new JLabel();
    topMostLabel.setText("Enter the new commission amount for the user");
    topMostPanel.add(backButton);
    topMostPanel.add(topMostLabel);
    mainPanel.add(topMostPanel);
  }

  private void inputCommissionPanel() {
    JPanel inputCommissionPanel = new JPanel();
    inputCommissionPanel.setLayout(new GridLayout(1, 1, 2, 0));
    commissionAmount = new JTextArea(1, 20);
    commissionAmount.setLineWrap(true);
    commissionAmount.setBorder(BorderFactory.createTitledBorder("Enter commission"));
    inputCommissionPanel.add(commissionAmount);
    mainPanel.add(inputCommissionPanel);
  }

  private void buttonPanel() {

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 1, 2, 2));
    JPanel setCommissionButtonPanel = new JPanel();
    setCommissionButton = new JButton();
    setCommissionButton.setText("Set Commission amount");
    setCommissionButtonPanel.add(setCommissionButton);
    buttonPanel.add(setCommissionButtonPanel);
    mainPanel.add(buttonPanel);
  }

  /**
   * Method to link the view to controller.
   *
   * @param jSetCommissionController controller for set commission for user.
   */
  public void addFeatures(JSetCommissionController jSetCommissionController) {
    this.backButton.addActionListener(evt -> jSetCommissionController.back());
    this.setCommissionButton.addActionListener(evt ->
            jSetCommissionController.setCommission(commissionAmount.getText()));

  }

  /**
   * Method to set the visibility of a view.
   */
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

  /**
   * Method to clear the user inputs.
   */
  public void clearAllUserInputs() {
    commissionAmount.setText("");
    outputLabel.setText("");
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

  private void logOutputPanel() {
    JPanel outputLog = new JPanel();
    outputLabel = new JLabel();
    outputLabel.setText("The current value for commission is set to $1.");
    outputLog.setBorder(BorderFactory.createTitledBorder("Output Logs"));
    outputLog.add(outputLabel);
    mainPanel.add(outputLog);
    add(mainPanel);
  }

  /**
   * Method to set initial output.
   *
   * @param commission amount of commission.
   */
  public void setInitialMessage(double commission) {
    setLogOutput("The current value for commission is $" + commission);
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

}
