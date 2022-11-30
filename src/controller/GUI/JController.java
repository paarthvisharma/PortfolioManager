package controller.GUI;

import view.GUI.JView;

/**
 * Interface for the main controller of the GUI for the stock program.
 */
public interface JController {
  /**
   * Method to set the view.
   *
   * @param jView an object of type JView.
   */
  void setView(JView jView);
}
