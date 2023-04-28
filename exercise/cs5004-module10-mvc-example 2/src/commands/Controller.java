package commands;

/**
 * Controller. Implementation of the Features interface.
 * is a template
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

  // provide to view
  @Override
  public void setView(View v) {
    view = v;
    view.addFeatures(this);
  }


  @Override public void setAnswer() {
    // Send text to the model.
    String res = model.outputString();

    // Clear input textfield.
    view.clearInputString();

    view.setAnswer(res);

    // Set focus back to main frame so that keyboard events work.
    view.resetFocus();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
