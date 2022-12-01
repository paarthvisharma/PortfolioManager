package controllertest.MockGUIView;

import view.gui.JCalculateCostBasisView;
import view.gui.JCalculateValueView;
import view.gui.JCreatePortfolioView;
import view.gui.JCreateUserView;
import view.gui.JLoadPortfolioView;
import view.gui.JPerformanceView;
import view.gui.JPortfolioMenuView;
import view.gui.JSetCommissionForUserView;
import view.gui.JTransactionView;
import view.gui.JView;
import view.gui.JViewPortfolioView;

public class MockView implements JView {

  private JCreateUserView mockCreateUserView = new MockCreateUserView();
  private JPortfolioMenuView mockPortfolioMenuView = new MockPortfolioMenuView();
  private JCreatePortfolioView mockCreatePortfolioView = new MockCreatePortfolioView();
  private JCalculateValueView mockCalculateValueView = new MockCalculateValueView();
  private JCalculateCostBasisView mockCalculateCostBasisView = new MockCalculateCostBasisView();
  private JViewPortfolioView mockViewPortfolioView = new MockViewPortfolioView();
  private JLoadPortfolioView mockLoadPortfolioView = new MockLoadPortfolioView();
  private JTransactionView mockTransactionView = new MockTransactionView();
  private JSetCommissionForUserView mockSetCommissionForUserView = new MockSetCommissionForUserView();
  private JPerformanceView mockPerformanceView = new MockPerformanceView();
  @Override
  public JCreateUserView getCreateUserView() {
    return mockCreateUserView;
  }

  @Override
  public JPortfolioMenuView getPortfolioMenuView() {
    return mockPortfolioMenuView;
  }

  @Override
  public JCreatePortfolioView getCreatePortfolioView() {
    return mockCreatePortfolioView;
  }

  @Override
  public JCalculateValueView getCalculateValueView() {
    return mockCalculateValueView;
  }

  @Override
  public JCalculateCostBasisView getCalculateCostBasisView() {
    return mockCalculateCostBasisView;
  }

  @Override
  public JViewPortfolioView getViewPortfolioView() {
    return mockViewPortfolioView;
  }

  @Override
  public JLoadPortfolioView getLoadPortfolioView() {
    return mockLoadPortfolioView;
  }

  @Override
  public JTransactionView getTransactionView() {
    return mockTransactionView;
  }

  @Override
  public JSetCommissionForUserView getCommissionView() {
    return mockSetCommissionForUserView;
  }

  @Override
  public JPerformanceView getPerformanceView() {
    return mockPerformanceView;
  }
}
