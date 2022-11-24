package model.builder;

import java.util.List;

import model.FlexiblePortfolio;
import model.RigidPortfolio;
import model.User;
import model.UserImpl;

/**
 * A UserBuilder class that contains methods that assist in creating the
 * user and fetching required information.
 */
public class UserBuilder {
  private int userId = -1;
  private String firstName;
  private String lastName;
  private List<RigidPortfolio> listOfRigidPortfolios;
  private List<FlexiblePortfolio> listOfFlexiblePortfolios;

  private double commission;

  public List<FlexiblePortfolio> getListOfFlexiblePortfolios() {
    return listOfFlexiblePortfolios;
  }

  public double getCommission() {
    return commission;
  }

  public void setCommission(double commission) {
    this.commission = commission;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<RigidPortfolio> getListOfRigidPortfolios() {
    return listOfRigidPortfolios;
  }

  public void setListOfRigidPortfolios(List<RigidPortfolio> listOfRigidPortfolios) {
    this.listOfRigidPortfolios = listOfRigidPortfolios;
  }

  public void setListOfFlexiblePortfolios(List<FlexiblePortfolio> listOfFlexiblePortfolios) {
    this.listOfFlexiblePortfolios = listOfFlexiblePortfolios;
  }

  /**
   * Processes the user information to build user object.
   *
   * @return returns an object of type User.
   * @throws IllegalArgumentException in case any of the arguments inserted is missing.
   */
  public User build() {
    if (this.firstName != null & this.lastName != null & this.userId != -1
            & this.listOfRigidPortfolios != null) {
      User toReturn = new UserImpl(this.firstName, this.lastName, this.userId, this.commission);
      for (RigidPortfolio portfolio : this.listOfRigidPortfolios) {
        toReturn.addToListOfRigidPortfolios(portfolio);
      }
      for (FlexiblePortfolio portfolio : this.listOfFlexiblePortfolios) {
        toReturn.addToListOfFlexiblePortfolios(portfolio);
      }
      return toReturn;
    } else {
      throw new IllegalArgumentException("One of the parameters required for User"
              + " have not been set");
    }
  }
}
