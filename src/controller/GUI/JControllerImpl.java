package controller.GUI;

import model.Model;
import view.GUI.JView;

/**
 * Class that implements the JController Interface which is the main controller of the GUI
 * for the stock program.
 */
public class JControllerImpl implements JController {

  private Model model;
  private JView view;
  private JUserController userController;
  //  private JPortfolioController portfolioController;

  /**
   * Constructor to initialize model.
   *
   * @param model an object of type model.
   */
  public JControllerImpl(Model model) {
    this.model = model;
    userController = new JUserControllerImpl(model);
    //    portfolioController = new JPortfolioControllerImpl(model);
  }

  @Override
  public void setView(JView jView) {
    view = jView;
    userController.setView(view);
    //    portfolioController.setView(view);
    //    portfolioController.setView(view.getPortfolioMenuView());
    //    view.getCreateUserView().addFeatures(userController);
    //    view.getPortfolioMenuView().addFeatures(portfolioController);
  }

}
