package view.gui;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import controller.gui.JPortfolioController;

/**
 * This class implements the JPortfolioMenuView interface and contains the methods to help display
 * portfolio menu.
 */
public class JPortfolioMenuView extends JFrame {

  private final JPanel mainPanel;
  private final JButton backButton;
  private JButton viewPortfolioButton;
  private JButton performancePortfolioButton;
  private JButton loadPortfolioButton;
  private JButton costBasisOfPortfolioButton;
  private JButton createPortfolioButton;
  private JButton transactInPortfolioButton;
  private JButton valuatePortfolioButton;
  private JButton setCommissionButton;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JPortfolioMenuView() {
    super("Portfolio Menu");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 1, 2, 2));

    JPanel topMostPanel = new JPanel();
    topMostPanel.setLayout(new FlowLayout());
    backButton = new JButton();
    backButton.setText("Back");
    backButton.setHorizontalAlignment(SwingConstants.LEFT);
    JLabel topMostLabel = new JLabel();
    topMostLabel.setText("Select an operations");
    topMostPanel.add(backButton);
    topMostPanel.add(topMostLabel);
    mainPanel.add(topMostPanel);
    add(mainPanel);

    this.setCommissionPanel();
    this.createPortfolioPanel();
    this.transactPortfolioPanel();
    this.valuatePortfolioPanel();
    this.costBasisPanel();
    this.loadPortfolioPanel();
    this.viewPortfolioPanel();
    this.performancePanel();

    this.isVisible(false);
  }

  private void setCommissionPanel() {
    JPanel setCommissionPanel = new JPanel();
    setCommissionPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel setCommissionLabel = new JLabel();
    setCommissionLabel.setText("Set commission for user");
    JPanel setCommissionButtonPanel = new JPanel();
    setCommissionButtonPanel.setLayout(new BoxLayout(
            setCommissionButtonPanel, BoxLayout.LINE_AXIS));
    setCommissionButton = new JButton();
    setCommissionButton.setText("Set commission");
    setCommissionButtonPanel.add(setCommissionButton);
    setCommissionPanel.add(setCommissionLabel);
    setCommissionPanel.add(setCommissionButtonPanel);
    mainPanel.add(setCommissionPanel);
  }

  private void performancePanel() {
    JPanel performancePortfolioPanel = new JPanel();
    performancePortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel performancePortfolioLabel = new JLabel();
    performancePortfolioLabel.setText("Performance of portfolio:");
    JPanel performancePortfolioButtonPanel = new JPanel();
    performancePortfolioButtonPanel.setLayout(new BoxLayout(
            performancePortfolioButtonPanel, BoxLayout.LINE_AXIS));
    performancePortfolioButton = new JButton();
    performancePortfolioButton.setText("Plot performance");
    performancePortfolioButtonPanel.add(performancePortfolioButton);
    performancePortfolioPanel.add(performancePortfolioLabel);
    performancePortfolioPanel.add(performancePortfolioButtonPanel);
    mainPanel.add(performancePortfolioPanel);
  }

  private void viewPortfolioPanel() {
    JPanel viewPortfolioPanel = new JPanel();
    viewPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel viewPortfolioLabel = new JLabel();
    viewPortfolioLabel.setText("Views a portfolio:");
    JPanel viewPortfolioButtonPanel = new JPanel();
    viewPortfolioButtonPanel.setLayout(new BoxLayout(
            viewPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    viewPortfolioButton = new JButton();
    viewPortfolioButton.setText("View Portfolio");
    viewPortfolioButtonPanel.add(viewPortfolioButton);
    viewPortfolioPanel.add(viewPortfolioLabel);
    viewPortfolioPanel.add(viewPortfolioButtonPanel);
    mainPanel.add(viewPortfolioPanel);
  }

  private void loadPortfolioPanel() {
    JPanel loadPortfolioPanel = new JPanel();
    loadPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel loadPortfolioLabel = new JLabel();
    loadPortfolioLabel.setText("Loads a portfolio:");
    JPanel loadPortfolioButtonPanel = new JPanel();
    loadPortfolioButtonPanel.setLayout(new BoxLayout(
            loadPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    loadPortfolioButton = new JButton();
    loadPortfolioButton.setText("Load Portfolio");
    loadPortfolioButtonPanel.add(loadPortfolioButton);
    loadPortfolioPanel.add(loadPortfolioLabel);
    loadPortfolioPanel.add(loadPortfolioButtonPanel);
    mainPanel.add(loadPortfolioPanel);
  }

  private void costBasisPanel() {
    JPanel costBasisOfPortfolioPanel = new JPanel();
    costBasisOfPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel costBasisOfPortfolioLabel = new JLabel();
    costBasisOfPortfolioLabel.setText("Cost basis of portfolio:");
    JPanel costBasisOfPortfolioButtonPanel = new JPanel();
    costBasisOfPortfolioButtonPanel.setLayout(
            new BoxLayout(costBasisOfPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    costBasisOfPortfolioButton = new JButton();
    costBasisOfPortfolioButton.setText("Cost basis of Portfolio");
    costBasisOfPortfolioButtonPanel.add(costBasisOfPortfolioButton);
    costBasisOfPortfolioPanel.add(costBasisOfPortfolioLabel);
    costBasisOfPortfolioPanel.add(costBasisOfPortfolioButtonPanel);
    mainPanel.add(costBasisOfPortfolioPanel);
  }

  private void createPortfolioPanel() {
    JPanel createPortfolioPanel = new JPanel();
    createPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel createPortfolioLabel = new JLabel();
    createPortfolioLabel.setText("Creates a portfolio:");
    JPanel createPortfolioButtonPanel = new JPanel();
    createPortfolioButtonPanel.setLayout(
            new BoxLayout(createPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    createPortfolioButton = new JButton();
    createPortfolioButton.setText("Create Portfolio");
    createPortfolioButtonPanel.add(createPortfolioButton);
    createPortfolioPanel.add(createPortfolioLabel);
    createPortfolioPanel.add(createPortfolioButtonPanel);
    mainPanel.add(createPortfolioPanel);
  }

  private void transactPortfolioPanel() {
    JPanel transactInPortfolioPanel = new JPanel();
    transactInPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel transactInPortfolioLabel = new JLabel();
    transactInPortfolioLabel.setText("Transacts in portfolio:");
    JPanel transactInPortfolioButtonPanel = new JPanel();
    transactInPortfolioButtonPanel.setLayout(
            new BoxLayout(transactInPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    transactInPortfolioButton = new JButton();
    transactInPortfolioButton.setText("Buy/Sell in portfolio");
    transactInPortfolioButtonPanel.add(transactInPortfolioButton);
    transactInPortfolioPanel.add(transactInPortfolioLabel);
    transactInPortfolioPanel.add(transactInPortfolioButtonPanel);
    mainPanel.add(transactInPortfolioPanel);
  }

  private void valuatePortfolioPanel() {
    JPanel valuatePortfolioPanel = new JPanel();
    valuatePortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    JLabel valuatePortfolioLabel = new JLabel();
    valuatePortfolioLabel.setText("Valuates a portfolio:");
    JPanel valuatePortfolioButtonPanel = new JPanel();
    valuatePortfolioButtonPanel.setLayout(
            new BoxLayout(valuatePortfolioButtonPanel, BoxLayout.LINE_AXIS));
    valuatePortfolioButton = new JButton();
    valuatePortfolioButton.setText("Valuate Portfolio");
    valuatePortfolioButtonPanel.add(valuatePortfolioButton);
    valuatePortfolioPanel.add(valuatePortfolioLabel);
    valuatePortfolioPanel.add(valuatePortfolioButtonPanel);
    mainPanel.add(valuatePortfolioPanel);
  }

  /**
   * Method to link the view to controller.
   *
   * @param jPortfolioMenu controller for portfolio menu.
   */
  public void addFeatures(JPortfolioController jPortfolioMenu) {
    backButton.addActionListener(evt -> jPortfolioMenu.back());
    viewPortfolioButton.addActionListener(evt -> jPortfolioMenu.viewPortfolio());
    performancePortfolioButton.addActionListener(evt -> jPortfolioMenu.performanceOfPortfolio());
    loadPortfolioButton.addActionListener(evt -> jPortfolioMenu.loadPortfolio());
    costBasisOfPortfolioButton.addActionListener(evt -> jPortfolioMenu.costBasisPortfolio());
    createPortfolioButton.addActionListener(evt -> jPortfolioMenu.createPortfolio());
    transactInPortfolioButton.addActionListener(evt -> jPortfolioMenu.transactInPortfolio());
    valuatePortfolioButton.addActionListener(evt -> jPortfolioMenu.valueOfPortfolio());
    setCommissionButton.addActionListener(evt -> jPortfolioMenu.setCommission());
  }

  /**
   * Method to set the visibility of a view.
   */
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

}
