package calculator;

/**
 * Mock implementation of the Calculator model interface.
 * We mock the model to only test the controller.
 */

// mock model no logic of real model
public class MockCalculatorModel implements CalculatorModel {
  private final StringBuilder log;
  // random number
  private final int uniqueCode;

  public MockCalculatorModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  // check what every model give you is correct
  // model is useless, controller is behave as it does
  @Override
  public int add(int num1, int num2) {
    log.append("Input for add: ")
        .append(num1)
        .append(" ")
        .append(num2)
        .append("\n");
    return uniqueCode;
  }

  @Override public int sub(int num1, int num2) {
    log.append("Input for sub: ")
        .append(num1)
        .append(" ")
        .append(num2)
        .append("\n");
    return uniqueCode;
  }

  @Override public int mul(int num1, int num2) {
    log.append("Input for mul: ")
        .append(num1)
        .append(" ")
        .append(num2)
        .append("\n");
    return uniqueCode;
  }
}
