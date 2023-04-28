import static org.junit.Assert.assertEquals;

import calculator.CalculatorController;
import calculator.CalculatorControllerImpl;
import calculator.CalculatorModelImpl;
import calculator.MockCalculatorModel;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Unit tests for the controller of the simple calculator.
 */
public class CalcControllerTest {

  /**
   * Test the controller and the add method of the model.
   *
   * @throws Exception as required by the output.
   */

  // testing controller add method of model
  // testing both, not isolating
  @Test
  public void testAddWithModelAndController() throws Exception {
    // out is buffer, in is reader
    StringBuffer out = new StringBuffer();
    // space is pressing enter, add 3 and 4, 8 and 9 and quit
    Reader in = new StringReader("+ 3 4 + 8 9 q");
    CalculatorController controller = new CalculatorControllerImpl(in, out);
    controller.go(new CalculatorModelImpl());
    assertEquals("7\n17\n", out.toString());
  }

  /**
   * Test the controller without the model (by using a mock model).
   *
   * @throws Exception as required by the output.
   */
  // mock model is implementation of model, but not do anything
  // dont need know how model look like, but only test controller
  @Test
  public void testAddWithMockModelAndController() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("+ 3 4 + 8 9 q");
    CalculatorController controller = new CalculatorControllerImpl(in, out);
    StringBuilder log = new StringBuilder(); //log for mock model
    // make long unique code
    controller.go(new MockCalculatorModel(log, 1234321));
    // check input
    assertEquals("Input for add: 3 4\nInput for add: 8 9\n", log.toString()); //inputs reached the model correctly
    // call correct method, get unique code
    assertEquals("1234321\n1234321\n", out.toString()); //output of model transmitted correctly
  }
  @Test
  public void testSubWithMockModelAndController() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("- 4 3 - 9 8 q");
    CalculatorController controller = new CalculatorControllerImpl(in, out);
    StringBuilder log = new StringBuilder();
    controller.go(new MockCalculatorModel(log, 1333333));
    assertEquals("Input for sub: 4 3\nInput for sub: 9 8\n", log.toString()); //inputs reached the model correctly
    assertEquals("1333333\n1333333\n", out.toString()); //output of model transmitted correctly
  }

  @Test
  public void testMulWithMockModelAndController() throws Exception {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("* 4 3 * 9 8 q");
    CalculatorController controller = new CalculatorControllerImpl(in, out);
    StringBuilder log = new StringBuilder();
    controller.go(new MockCalculatorModel(log, 1633333));
    assertEquals("Input for mul: 4 3\nInput for mul: 9 8\n", log.toString()); //inputs reached the model correctly
    assertEquals("1633333\n1633333\n", out.toString()); //output of model transmitted correctly
  }
}