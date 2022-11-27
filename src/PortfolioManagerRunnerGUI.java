import controller.GUI.JController;
import controller.GUI.JControllerImpl;
import model.Model;
import model.ModelImpl;
import view.GUI.JView;
import view.GUI.JViewImpl;

public class PortfolioManagerRunnerGUI {

  public static void main(String[] args) {
    Model model = new ModelImpl();
//    JUserController controller = new JUserControllerImpl(model);
//    JCreateUserView view = new JCreateUserViewImpl();
//    controller.setView(view);
    JController controller = new JControllerImpl(model);
    JView view = new JViewImpl();
    controller.setView(view);


  }
}
