package commands;

/**
 * Interface for an asynchronous controller.
 * provide view everything need from controller
 */
public interface Features {
  /**
   * Provide view with all the callbacks.
   * @param v the view.
   */
  public void setView(View v);


  void setAnswer();

  /**
   * Exit the program.
   */
  void exitProgram();
}
