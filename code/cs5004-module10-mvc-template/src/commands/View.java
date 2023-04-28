package commands;

/**
 * The interface for our view class.
 */
public interface View {
  /**
   * Set the label that is showing what the model stores.
   *
   * @param text the text entered by the user.
   */
  void setEchoOutput(String text);

  /**
   * Get the string from the text field and return it.
   *
   * @return the string from the text field.
   */
  String getInputString();

  /**
   * Clear the text field. Note that a more general "setInputString" would work for this
   * purpose but would be incorrect. This is because the text field is not set programmatically
   * in general but input by the user.
   */
  void clearInputString();

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard listener attached
   * to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * Toggle the color of the displayed text. This is an explicit view operation because
   * this is something that only the view can control
   */
  void toggleColor();

  /**
   * Add the Controller to the view.
   *
   * @param features the implementation of our controller.
   */
  void addFeatures(Features features);
}
