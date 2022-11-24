import java.io.FileWriter;
import java.io.IOException;

import controller.Controller;
import controller.ControllerImpl;
import model.Model;
import model.ModelImpl;
import view.View;
import view.ViewImpl;


import static model.constants.DirectoryStructureConstants.PROGRAM_STATE_FILE_PATH;

/**
 * This is the class that wraps the main method that initializes model, view and controller
 * and runs the program.
 */
public class PortfolioManagerRunner {
  /**
   * Static main method that runs the program.
   *
   * @param args expects nothing.
   */
  public static void main(String[] args) {
    Model modelImpl = new ModelImpl();
    View viewImpl = new ViewImpl(System.out);
    Controller controllerImpl = new ControllerImpl(modelImpl, System.in, viewImpl);
    controllerImpl.runLoop();
    updateProgramState(modelImpl);
  }

  static void updateProgramState(Model model) {
    FileWriter programStateWriter = null;
    try {
      programStateWriter = new FileWriter(PROGRAM_STATE_FILE_PATH);
      programStateWriter.write("userId" + ":" + model.getNextUserId() + "\n");
      programStateWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
