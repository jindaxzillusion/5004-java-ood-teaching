package calculator;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Controller implementation of a very simple calculator.
 */
public class CalculatorControllerImpl implements CalculatorController {
  // allow to have something entered by user， and unit tests
  final Readable in;
  final Appendable out;

  /**
   * Constructor for the controller.
   *
   * @param in the input provided by the user.
   * @param out the output of the calculator.
   */
  public CalculatorControllerImpl(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  @Override 
  public void go(CalculatorModel calculatorModel) throws IOException {
    // check what you give me is not NULL, there is sth there, if there is no implementation complain
    Objects.requireNonNull(calculatorModel);
    int num1;
    int num2;
    Scanner scan = new Scanner(this.in);
    while (true) {
      switch (scan.next()) {
        // if they type plus
        case "+":
          // save the int they type
          num1 = scan.nextInt();
          num2 = scan.nextInt();
          this.out.append(String.format("%d\n", calculatorModel.add(num1, num2)));
          break;
        // ends
        case "-":
          num1 = scan.nextInt();
          num2 = scan.nextInt();
          this.out.append(String.format("%d\n", calculatorModel.sub(num1, num2)));
          break;
        case "*":
          num1 = scan.nextInt();
          num2 = scan.nextInt();
          this.out.append(String.format("%d\n", calculatorModel.mul(num1, num2)));
          break;
        case "q":
          return;
        default:
          throw new IllegalStateException("Unexpected value: " + scan.next());
      }
    }
  }
}
