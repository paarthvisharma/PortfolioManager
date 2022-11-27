package controller.GUI;

import java.util.List;

import model.Model;
import model.User;
import view.GUI.JCreatePortfolioView;
import view.GUI.JPortfolioMenuView;
import view.GUI.JView;

public class JCreatePortfolioControllerImpl implements JCreatePortfolioController {

  private Model model;
  private JView view;
  private JCreatePortfolioView createPortfolioView;
  private JPortfolioMenuView portfolioMenuView;
  private User user;

  public JCreatePortfolioControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void back() {
    this.createPortfolioView.isVisible(false);
    this.portfolioMenuView.isVisible(true);
  }

  @Override
  public void createPortfolio() {

  }

  @Override
  public void addStock() {

  }

  @Override
  public void setView(JView jView) {
    this.view = jView;
    this.portfolioMenuView = this.view.getPortfolioMenuView();
    this.createPortfolioView = this.view.getCreatePortfolioView();
    this.createPortfolioView.addFeatures(this);
  }

  @Override
  public void monitorTable(List<String> weightsColumn) {

  }

  @Override
  public void setUser(User user) {
    this.user = user;
  }
}
