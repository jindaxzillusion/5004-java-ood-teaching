package commands;

/**
 * This example shows how to specify the (Key,Runnable) keyboard map using the ability of java 8 to
 * support lambda expressions.
 */
public class GeneralCommandCallbacks {
  /**
   * Driver class. Creates MVC objects.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    Model model = new ModelImpl();
    Controller controller = new Controller(model);
    // create the view with string
    View view = new FrameView("Enter your question:");
    // controller hook up view and model, view and model not talking to each other
    controller.setView(view);
  }
}
