package view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.gui.JUserController;

/**
 * This class implements the JCreateUserView interface and contains the methods to help display
 * create user menu.
 */
public class JCreateUserViewImpl extends JFrame implements JCreateUserView, ActionListener {

  private final JPanel mainPanel;
  private JTextArea firstName;
  private JTextArea lastName;
  private JTextArea userId;
  private JButton createUserButton;
  private JButton loginButton;
  private JButton loadUserButton;
  private JLabel filePathLabel;
  private JLabel logOutput;

  /**
   * Constructor for the class to set up the initial view.
   */
  public JCreateUserViewImpl() {
    super("User Menu");
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(4, 1, 0, 5));
    add(mainPanel);

    createUserPanel();
    loginUserPanel();
    loadUserPanel();
    logOutputPanel();

    this.isVisible(true);
  }

  private void createUserPanel() {
    JPanel createUserPanel = new JPanel();
    createUserPanel.setBorder(BorderFactory.createTitledBorder("Create User"));
    createUserPanel.setLayout(new GridLayout(2, 1, 0, 2));

    JPanel firstLastNamePanel = new JPanel();
    firstLastNamePanel.setLayout(new GridLayout(1, 2, 2, 0));

    firstName = new JTextArea(1, 20);
    firstName.setLineWrap(true);
    firstName.setBorder(BorderFactory.createTitledBorder("Enter First Name"));
    firstLastNamePanel.add(firstName);

    lastName = new JTextArea(1, 20);
    lastName.setLineWrap(true);
    lastName.setBorder(BorderFactory.createTitledBorder("Enter Last Name"));
    firstLastNamePanel.add(lastName);


    JPanel createUserButtonPanel = new JPanel();
    createUserButtonPanel.setLayout(new BoxLayout(createUserButtonPanel, BoxLayout.PAGE_AXIS));
    createUserButton = new JButton("Create User");
    createUserButton.setActionCommand("create user");
    createUserButtonPanel.add(createUserButton);

    createUserPanel.add(firstLastNamePanel);
    createUserPanel.add(createUserButtonPanel);

    mainPanel.add(createUserPanel);
  }

  private void loginUserPanel() {
    JPanel loginUserPanel = new JPanel();
    loginUserPanel.setLayout(new GridLayout(2, 1, 2, 2));

    loginUserPanel.setBorder(BorderFactory.createTitledBorder("Login User"));
    userId = new JTextArea(1, 20);
    userId.setLineWrap(true);
    userId.setBorder(BorderFactory.createTitledBorder("Enter User ID"));
    loginUserPanel.add(userId);


    JPanel loginButtonPanel = new JPanel();
    loginButtonPanel.setLayout(new BoxLayout(loginButtonPanel, BoxLayout.PAGE_AXIS));
    loginButton = new JButton("Login");
    loginButton.setActionCommand("login user");
    loginButtonPanel.add(loginButton);
    loginUserPanel.add(loginButtonPanel);
    mainPanel.add(loginUserPanel);
  }

  private void loadUserPanel() {
    JPanel loadUserPanel = new JPanel();
    loadUserPanel.setBorder(BorderFactory.createTitledBorder("Load User"));
    loadUserPanel.setLayout(new GridLayout(2, 1, 2, 2));

    JPanel filePathPanel = new JPanel();
    filePathPanel.setLayout(new GridLayout(1, 2, 2, 2));

    JPanel fileOpenButtonPanel = new JPanel();
    fileOpenButtonPanel.setLayout(new BoxLayout(fileOpenButtonPanel, BoxLayout.PAGE_AXIS));
    JButton fileOpenButton = new JButton("Open a User XML file");
    fileOpenButton.setActionCommand("open user file");
    fileOpenButton.addActionListener(this);
    fileOpenButtonPanel.add(fileOpenButton);

    JPanel filePathLabelPanel = new JPanel();
    filePathLabelPanel.setLayout(new BoxLayout(filePathLabelPanel, BoxLayout.PAGE_AXIS));
    filePathLabel = new JLabel("The selected file path will be shown here");
    filePathLabel.setText("The selected file path will be shown here");
    filePathLabelPanel.add(filePathLabel);
    filePathPanel.add(fileOpenButtonPanel);
    filePathPanel.add(filePathLabelPanel);

    loadUserPanel.add(filePathPanel);

    JPanel loadUserButtonPanel = new JPanel();
    loadUserButtonPanel.setLayout(new BoxLayout(loadUserButtonPanel, BoxLayout.PAGE_AXIS));
    loadUserButton = new JButton("Load user");
    loadUserButton.setActionCommand("load user");
    loadUserButtonPanel.add(loadUserButton);
    loadUserPanel.add(loadUserButtonPanel);

    mainPanel.add(loadUserPanel);
  }

  private void logOutputPanel() {
    JPanel outputLog = new JPanel();
    outputLog.setLayout(new BoxLayout(outputLog, BoxLayout.PAGE_AXIS));
    logOutput = new JLabel();
    logOutput.setText("The output of each function will be shown here");
    outputLog.setBorder(BorderFactory.createTitledBorder("Output Logs"));
    outputLog.add(logOutput);
    mainPanel.add(outputLog);
  }

  @Override
  public void clearAllUserInputs() {
    userId.setText("");
    firstName.setText("");
    lastName.setText("");
    filePathLabel.setText("");
  }

  @Override
  public void addFeatures(JUserController jUserController) {
    loginButton.addActionListener(evt -> jUserController.loginUser(userId.getText()));
    createUserButton.addActionListener(evt -> jUserController.createUser(firstName.getText(),
            lastName.getText()));
    loadUserButton.addActionListener(evt -> jUserController.loadUser(filePathLabel.getText()));
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if ("open user file".equals(e.getActionCommand())) {
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
  public void isVisible(boolean state) {
    this.setVisible(state);
    this.pack();
  }
}
