package calculator;

import java.io.IOException;

/**
 * This is the interface for the controller of a very simple calculator.
 */
public interface CalculatorController {
  /**
   * Run the controller of a very simple calculator.
   *
   * @param calculatorModel the model of the calculator.
   * @throws IOException produced by failed or interrupted I/O operations.
   */
  // if something happen in input/output issue
  void go(CalculatorModel calculatorModel) throws IOException;
}
