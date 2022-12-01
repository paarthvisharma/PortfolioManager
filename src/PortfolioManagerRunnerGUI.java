import controller.gui.JController;
import controller.gui.JControllerImpl;
import model.Model;
import model.ModelImpl;
import view.gui.JView;
import view.gui.JViewImpl;

/**
 * This is the class that wraps the main method that initializes model, view and controller for GUI
 * and runs the program.
 */
public class PortfolioManagerRunnerGUI {
  /**
   * Static main method that runs the program.
   *
   * @param args expects nothing.
   */
  public static void main(String[] args) {
    Model model = new ModelImpl();
    //    JUserController controller = new JUserControllerImpl(model);
    //    JCreateUserView view = new JCreateUserViewImpl();
    //    controller.setView(view);
    JController controller = new JControllerImpl(model);
    JView view = new JViewImpl();
    controller.setView(view);
    // TODO
    //    Disable buttons in portfolioPanel for users who have no portfolios.
    //    Update the clear table to all classes
    //    Porpagate the this.pack() to all classes as well
    //    abstract the view and controller (This is very necessary)

  }
}
