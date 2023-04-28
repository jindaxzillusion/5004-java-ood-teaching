package commands;

/**
 * Controller. Implementation of the Features interface.
 */
public class Controller implements Features {
  private final Model model;
  private View view;

  /**
   * Constructor.
   *
   * @param m the Model.
   */
  public Controller(Model m) {
    model = m;
  }

  @Override
  public void setView(View v) {
    view = v;
    view.addFeatures(this);
  }

  @Override
  public void toggleColor() {
    view.toggleColor();
  }

  @Override
  public void makeUppercase() {
    String text = model.getString();
    text = text.toUpperCase();
    view.setEchoOutput(text);
  }

  @Override
  public void restoreToOriginalText() {
    String text = model.getString();
    view.setEchoOutput(text);
  }

  @Override
  public void echoOutput(String typed) {
    // Send text to the model.
    model.setString(typed);

    // Clear input textfield.
    view.clearInputString();

    // Finally echo the string in view.
    String text = model.getString();
    view.setEchoOutput(text);

    // Set focus back to main frame so that keyboard events work.
    view.resetFocus();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
