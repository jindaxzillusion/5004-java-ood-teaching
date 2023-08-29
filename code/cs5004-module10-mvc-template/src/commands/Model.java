package commands;

/**
 * ModelImpl interface for a very simple MVC example. We have methods to get and set a String.
 */
public interface Model {
  /**
   * Set a new String.
   *
   * @param text the String we want to use
   */
  void setString(String text);

  /**
   * Get the String.
   *
   * @return the Echoed String
   */
  String getString();
}
