package calculator;

/**
 * This is the interface for the model of a very simple calculator.
 */
public interface CalculatorModel {
  /**
   * Two integers are provided as parameters and their sum is the output.
   * @param num1 the first integer number that will be added.
   * @param num2 the second integer number that will be added.
   * @return the sum of the two integer numbers provided.
   */
  int add(int num1, int num2);
  int sub(int num1, int num2);
  int mul(int num1, int num2);
}
