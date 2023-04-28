package calculator;

/**
 * Implementation of the model of a very simple calculator.
 */
public class CalculatorModelImpl implements CalculatorModel {

  @Override
  public int add(int num1, int num2) {
    return num1 + num2;
  }

  @Override public int sub(int num1, int num2) {
    return num1 - num2;
  }

  @Override public int mul(int num1, int num2) {
    return num1 * num2;
  }
}
