package view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

/**
 * This class implements the JLoadPortfolioView interface and contains the methods to help display
 * load portfolio menu.
 */

import controller.gui.JLoadPortfolioController;

public class JLoadPortfolioViewImpl extends JFrame implements JLoadPortfolioView, ActionListener {

  private JPanel mainPanel;
  private JLabel filePathLabel;
  private JLabel logOutput;
  private JButton backButton;
  private JButton loadPortfolioButton;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JLoadPortfolioViewImpl() {
    super("Load Portfolio");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    this.placeTopPanel();
    this.loadPortfolioPanel();
    this.logOutputPanel();
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
    topMostLabel.setText("Fill in the appropriate information");
    topMostPanel.add(backButton);
    topMostPanel.add(topMostLabel);
    mainPanel.add(topMostPanel);
  }

  private void loadPortfolioPanel() {
    JPanel loadPortfolioPanel = new JPanel();
    loadPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Load Portfolio"));
    loadPortfolioPanel.setLayout(new GridLayout(2, 1, 2, 2));

    JPanel filePathPanel = new JPanel();
//    filePathPanel.setLayout(new GridLayout(1, 2, 2, 2));

    JPanel fileOpenButtonPanel = new JPanel();
    fileOpenButtonPanel.setLayout(new BoxLayout(fileOpenButtonPanel, BoxLayout.PAGE_AXIS));
    JButton fileOpenButton = new JButton("Open a Portfolio XML file");
    fileOpenButton.setActionCommand("open portfolio file");
    fileOpenButton.addActionListener(this);
    fileOpenButtonPanel.add(fileOpenButton);

    JPanel filePathLabelPanel = new JPanel();
    filePathLabelPanel.setLayout(new BoxLayout(filePathLabelPanel, BoxLayout.PAGE_AXIS));
    filePathLabel = new JLabel("The selected file path will be shown here");
    filePathLabelPanel.add(filePathLabel);
    filePathPanel.add(fileOpenButtonPanel);
    filePathPanel.add(filePathLabelPanel);

    loadPortfolioPanel.add(filePathPanel);

    JPanel loadUserButtonPanel = new JPanel();
    loadUserButtonPanel.setLayout(new BoxLayout(loadUserButtonPanel, BoxLayout.PAGE_AXIS));
    loadPortfolioButton = new JButton("Load portfolio");
    loadPortfolioButton.setActionCommand("load portfolio");
    loadUserButtonPanel.add(loadPortfolioButton);
    loadPortfolioPanel.add(loadUserButtonPanel);

    mainPanel.add(loadPortfolioPanel);
  }

  private void logOutputPanel() {
    JPanel outputLog = new JPanel();
//    outputLog.setLayout(new BoxLayout(outputLog, BoxLayout.PAGE_AXIS));
    logOutput = new JLabel();
    logOutput.setText("The output of each function will be shown here");
    outputLog.setBorder(BorderFactory.createTitledBorder("Output Logs"));
    outputLog.add(logOutput);
    mainPanel.add(outputLog);
  }

  @Override
  public void addFeatures(JLoadPortfolioController jLoadPortfolioController) {
    this.backButton.addActionListener(evt -> jLoadPortfolioController.back());
    this.loadPortfolioButton.addActionListener(evt ->
            jLoadPortfolioController.loadPortfolio(filePathLabel.getText()));
  }

  @Override
  public void clearAllUserInputs() {
    this.filePathLabel.setText("");
    this.logOutput.setText("");
  }

  @Override
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }

  @Override
  public void setLogOutput(String message) {
    logOutput.setForeground(Color.BLACK);
    logOutput.setText(message);
  }

  @Override
  public void setSuccessOutput(String message) {
    logOutput.setForeground(new Color(0, 102, 0));
    logOutput.setText(message);
  }

  @Override
  public void setFailureOutput(String message) {
    logOutput.setForeground(Color.RED);
    logOutput.setText(message);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if ("open portfolio file".equals(e.getActionCommand())) {
      final JFileChooser fChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "XML", "xml");
      fChooser.setFileFilter(filter);
      int retValue = fChooser.showOpenDialog(this);
      if (retValue == JFileChooser.APPROVE_OPTION) {
        File f = fChooser.getSelectedFile();
        filePathLabel.setText(f.getAbsolutePath());
      }
    }
  }
}
