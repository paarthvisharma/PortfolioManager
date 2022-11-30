package view.GUI;

import java.awt.*;

import javax.swing.*;

import controller.GUI.JPortfolioController;

public class JPortfolioMenuViewImpl extends JFrame implements JPortfolioMenuView {

  private JPanel mainPanel;
  private JPanel topMostPanel;
  private JPanel performancePortfolioButtonPanel;
  private JPanel performancePortfolioPanel;
  private JPanel viewPortfolioPanel;
  private JPanel viewPortfolioButtonPanel;
  private JPanel loadPortfolioPanel;
  private JPanel loadPortfolioButtonPanel;
  private JPanel costBasisOfPortfolioPanel;
  private JPanel costBasisOfPortfolioButtonPanel;
  private JPanel createPortfolioPanel;
  private JPanel createPortfolioButtonPanel;
  private JPanel transactInPortfolioPanel;
  private JPanel transactInPortfolioButtonPanel;
  private JPanel valuatePortfolioPanel;
  private JPanel valuatePortfolioButtonPanel;
  private JPanel setCommissionPanel;
  private JPanel setCommissionButtonPanel;
  private JLabel topMostLabel;
  private JLabel performancePortfolioLabel;
  private JLabel viewPortfolioLabel;
  private JLabel loadPortfolioLabel;
  private JLabel costBasisOfPortfolioLabel;
  private JLabel createPortfolioLabel;
  private JLabel transactInPortfolioLabel;
  private JLabel valuatePortfolioLabel;
  private JLabel setCommissionLabel;
  private JButton backButton;
  private JButton viewPortfolioButton;
  private JButton performancePortfolioButton;
  private JButton loadPortfolioButton;
  private JButton costBasisOfPortfolioButton;
  private JButton createPortfolioButton;
  private JButton transactInPortfolioButton;
  private JButton valuatePortfolioButton;
  private JButton setCommissionButton;


  public JPortfolioMenuViewImpl() {
    super("Portfolio Menu");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 1, 2, 2));

    topMostPanel = new JPanel();
    topMostPanel.setLayout(new FlowLayout());
    backButton = new JButton();
    backButton.setText("Back");
    backButton.setHorizontalAlignment(SwingConstants.LEFT);
    topMostLabel = new JLabel();
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
    setCommissionPanel = new JPanel();
    setCommissionPanel.setLayout(new GridLayout(1, 2, 2, 2));
    setCommissionLabel = new JLabel();
    setCommissionLabel.setText("Set commission for user");
    setCommissionButtonPanel = new JPanel();
    setCommissionButtonPanel.setLayout(new BoxLayout(setCommissionButtonPanel, BoxLayout.LINE_AXIS));
    setCommissionButton = new JButton();
    setCommissionButton.setText("Set commission");
    setCommissionButtonPanel.add(setCommissionButton);
    setCommissionPanel.add(setCommissionLabel);
    setCommissionPanel.add(setCommissionButtonPanel);
    mainPanel.add(setCommissionPanel);
  }

  private void performancePanel() {
    performancePortfolioPanel = new JPanel();
    performancePortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    performancePortfolioLabel = new JLabel();
    performancePortfolioLabel.setText("Performance of portfolio:");
    performancePortfolioButtonPanel = new JPanel();
    performancePortfolioButtonPanel.setLayout(new BoxLayout(performancePortfolioButtonPanel, BoxLayout.LINE_AXIS));
    performancePortfolioButton = new JButton();
    performancePortfolioButton.setText("Plot performance");
    performancePortfolioButtonPanel.add(performancePortfolioButton);
    performancePortfolioPanel.add(performancePortfolioLabel);
    performancePortfolioPanel.add(performancePortfolioButtonPanel);
    mainPanel.add(performancePortfolioPanel);
  }

  private void viewPortfolioPanel() {
    viewPortfolioPanel = new JPanel();
    viewPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    viewPortfolioLabel = new JLabel();
    viewPortfolioLabel.setText("Views a portfolio:");
    viewPortfolioButtonPanel = new JPanel();
    viewPortfolioButtonPanel.setLayout(new BoxLayout(viewPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    viewPortfolioButton = new JButton();
    viewPortfolioButton.setText("View Portfolio");
    viewPortfolioButtonPanel.add(viewPortfolioButton);
    viewPortfolioPanel.add(viewPortfolioLabel);
    viewPortfolioPanel.add(viewPortfolioButtonPanel);
    mainPanel.add(viewPortfolioPanel);
  }

  private void loadPortfolioPanel() {
    loadPortfolioPanel = new JPanel();
    loadPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    loadPortfolioLabel = new JLabel();
    loadPortfolioLabel.setText("Loads a portfolio:");
    loadPortfolioButtonPanel = new JPanel();
    loadPortfolioButtonPanel.setLayout(new BoxLayout(loadPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    loadPortfolioButton = new JButton();
    loadPortfolioButton.setText("Load Portfolio");
    loadPortfolioButtonPanel.add(loadPortfolioButton);
    loadPortfolioPanel.add(loadPortfolioLabel);
    loadPortfolioPanel.add(loadPortfolioButtonPanel);
    mainPanel.add(loadPortfolioPanel);
  }

  private void costBasisPanel() {
    costBasisOfPortfolioPanel = new JPanel();
    costBasisOfPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    costBasisOfPortfolioLabel = new JLabel();
    costBasisOfPortfolioLabel.setText("Cost basis of portfolio:");
    costBasisOfPortfolioButtonPanel = new JPanel();
    costBasisOfPortfolioButtonPanel.setLayout(new BoxLayout(costBasisOfPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    costBasisOfPortfolioButton = new JButton();
    costBasisOfPortfolioButton.setText("Cost basis of Portfolio");
    costBasisOfPortfolioButtonPanel.add(costBasisOfPortfolioButton);
    costBasisOfPortfolioPanel.add(costBasisOfPortfolioLabel);
    costBasisOfPortfolioPanel.add(costBasisOfPortfolioButtonPanel);
    mainPanel.add(costBasisOfPortfolioPanel);
  }

  private void createPortfolioPanel() {
    createPortfolioPanel = new JPanel();
    createPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    createPortfolioLabel = new JLabel();
    createPortfolioLabel.setText("Creates a portfolio:");
    createPortfolioButtonPanel = new JPanel();
    createPortfolioButtonPanel.setLayout(new BoxLayout(createPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    createPortfolioButton = new JButton();
    createPortfolioButton.setText("Create Portfolio");
    createPortfolioButtonPanel.add(createPortfolioButton);
    createPortfolioPanel.add(createPortfolioLabel);
    createPortfolioPanel.add(createPortfolioButtonPanel);
    mainPanel.add(createPortfolioPanel);
  }

  private void transactPortfolioPanel() {
    transactInPortfolioPanel = new JPanel();
    transactInPortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    transactInPortfolioLabel = new JLabel();
    transactInPortfolioLabel.setText("Transacts in portfolio:");
    transactInPortfolioButtonPanel = new JPanel();
    transactInPortfolioButtonPanel.setLayout(new BoxLayout(transactInPortfolioButtonPanel, BoxLayout.LINE_AXIS));
    transactInPortfolioButton = new JButton();
    transactInPortfolioButton.setText("Buy/Sell in portfolio");
    transactInPortfolioButtonPanel.add(transactInPortfolioButton);
    transactInPortfolioPanel.add(transactInPortfolioLabel);
    transactInPortfolioPanel.add(transactInPortfolioButtonPanel);
    mainPanel.add(transactInPortfolioPanel);
  }

  private void valuatePortfolioPanel() {
    valuatePortfolioPanel = new JPanel();
    valuatePortfolioPanel.setLayout(new GridLayout(1, 2, 2, 2));
    valuatePortfolioLabel = new JLabel();
    valuatePortfolioLabel.setText("Valuates a portfolio:");
    valuatePortfolioButtonPanel = new JPanel();
    valuatePortfolioButtonPanel.setLayout(new BoxLayout(valuatePortfolioButtonPanel, BoxLayout.LINE_AXIS));
    valuatePortfolioButton = new JButton();
    valuatePortfolioButton.setText("Valuate Portfolio");
    valuatePortfolioButtonPanel.add(valuatePortfolioButton);
    valuatePortfolioPanel.add(valuatePortfolioLabel);
    valuatePortfolioPanel.add(valuatePortfolioButtonPanel);
    mainPanel.add(valuatePortfolioPanel);
  }

  @Override
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

  @Override
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

}
