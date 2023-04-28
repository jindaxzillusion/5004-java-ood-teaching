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
    View view = new FrameView("Echo a string");
    controller.setView(view);
  }
}
