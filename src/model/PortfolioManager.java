package model;

import java.io.File;

/**
 * The Portfolio Manager class manages the Users by assigning them unique Ids for identification.
 */
public class PortfolioManager {
  private int userId = 0;

  /**
   * This is the constructor for PortfolioImpl. It retrieves the UserId and updates
   * the program state.
   *
   * @param nextUserId userId of next user that will be created.
   */
  public PortfolioManager(int nextUserId) {
    this.userId = nextUserId;
  }

  /**
   * Method to create user with unique userId. It takes in the following parameters.
   *
   * @param firstName   firstName associated with a user.
   * @param lastName    lastName associated with a user.
   * @param userDataDir directory where program data is being stored.
   * @return an object of type user.
   */
  public User createUser(
          String firstName, String lastName, double commission, String userDataDir) {
    this.userId = getMaxUserId(userDataDir) + 1;
    return new UserImpl(firstName, lastName, userId, commission);
  }

  /**
   * Processes the directory to obtain the max user Id created.
   *
   * @param userDataDir directory where UserData is stored.
   * @return returns the max userId.
   */

  private int getMaxUserId(String userDataDir) {
    File f = new File(userDataDir);
    String[] pathNames = f.list();
    int maxUserId = 0;
    if (pathNames == null) {
      return 0;
    }
    for (String path : pathNames) {
      maxUserId = Math.max(Integer.parseInt(path.trim()), maxUserId);
    }
    return maxUserId;
  }

  /**
   * Method to get the userId of the next user that will be created.
   *
   * @return returns userId.
   */

  public int getNextUserId() {
    return this.userId;
  }

}
