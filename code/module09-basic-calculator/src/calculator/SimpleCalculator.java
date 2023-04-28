package calculator;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A very simple calculator. The user enters an operation and two numbers.
 * The answer is produced.
 */
public class SimpleCalculator {
  /**
   * Main method used to run our implementation.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    try {
      new CalculatorControllerImpl(new InputStreamReader(System.in), System.out)
          .go(new CalculatorModelImpl());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
