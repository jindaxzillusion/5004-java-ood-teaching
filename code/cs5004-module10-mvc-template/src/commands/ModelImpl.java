package commands;

/**
 * Implementation of the Model Interface.
 */
public class ModelImpl implements Model {
  private String input;

  public ModelImpl() {
    input = "";
  }

  @Override public void setString(String text) {
    input = text;
  }

  @Override public String getString() {
    return input;
  }
}