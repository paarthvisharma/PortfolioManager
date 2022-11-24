package model.utils;

/**
 * The StatusObject is a generic class which is returned by the model object that
 * indicates the status of the operation performed, status code and the created object.
 */
public class StatusObject<T> {
  public String statusMessage;
  public int statusCode;
  public T returnedObject;

  /**
   * A constructor which creates an object using the below parameters.
   *
   * @param statusMessage  message after an operation is completed.
   * @param statusCode     contains success/failure codes.
   * @param returnedObject object of a particular type.
   */
  public StatusObject(String statusMessage, int statusCode, T returnedObject) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
    this.returnedObject = returnedObject;
  }
}
