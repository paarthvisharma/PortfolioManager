package view.gui;

import controller.gui.JPortfolioController;

/**
 * This is a view interface for main portfolio menu.
 */
public interface JPortfolioMenuView {
  /**
   * Method to link the view to controller.
   *
   * @param jPortfolioMenu controller for portfolio menu.
   */
  void addFeatures(JPortfolioController jPortfolioMenu);

  /**
   * Method to set the visibility of a view.
   */
  void isVisible(boolean state);
}
