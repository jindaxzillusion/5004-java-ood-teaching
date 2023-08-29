import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import transmission.AutomaticTransmission;
import transmission.Transmission;

/**
 * Class for testing the AutomaticTransmission class.
 */
public class AutomaticTransmissionTest {

  private Transmission testInstance1;
  private Transmission testInstance2;

  @Before public void setUp() {
    testInstance1 = automaticTransmission(10, 20, 50, 70, 90);
    testInstance2 = automaticTransmission(40, 60, 80, 100, 120);
  }

  /**
   * This method is providing shorthand way of creating instances of a new AutomaticTransmission
   * object.
   *
   * @param threshold1 the 1st Speed Threshold
   * @param threshold2 the 2nd Speed Threshold
   * @param threshold3 the 3rd Speed Threshold
   * @param threshold4 the 4th Speed Threshold
   * @param threshold5 the 5th Speed Threshold
   * @return a new instance of a transmission.AutomaticTransmission object
   */
  protected Transmission automaticTransmission(int threshold1, int threshold2, int threshold3,
      int threshold4, int threshold5) throws IllegalArgumentException {
    return new AutomaticTransmission(threshold1, threshold2, threshold3, threshold4, threshold5);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test negative threshold 1", max_score = 1)
  public void testNegativeThreshold1() {
    automaticTransmission(-1, 1, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test zero threshold 1", max_score = 1)
  public void testZeroThreshold1() {
    automaticTransmission(0, 1, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test invalid threshold 1", max_score = 1)
  public void testInvalidThresholdOrder1() {
    automaticTransmission(1, 1, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test invalid threshold 2", max_score = 1)
  public void testInvalidThresholdOrder2() {
    automaticTransmission(1, 2, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test invalid threshold 3", max_score = 1)
  public void testInvalidThresholdOrder3() {
    automaticTransmission(1, 2, 3, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test invalid threshold 4", max_score = 1)
  public void testInvalidThresholdOrder4() {
    automaticTransmission(1, 2, 3, 4, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test invalid threshold 5", max_score = 1)
  public void testInvalidThresholdOrder5() {
    automaticTransmission(1, 2, 3, 4, 3);
  }

  @Test(expected = IllegalStateException.class)
  // @GradedTest(name = "Test invalid decreaseSpeed", max_score = 1)
  public void testInvalidDecreaseSpeed() {
    testInstance1.decreaseSpeed();
  }

  @Test
  // @GradedTest(name = "Test increaseSpeed", max_score = 8)
  public void testIncreaseSpeed() {
    testInstance1.increaseSpeed();
    assertEquals(1, testInstance1.getSpeed());
    testInstance1.increaseSpeed();
    assertEquals(2, testInstance1.getSpeed());
    testInstance1.increaseSpeed();
    assertEquals(3, testInstance1.getSpeed());
    testInstance1.increaseSpeed();
    assertEquals(4, testInstance1.getSpeed());

    // Increase 10 Times
    int currentSpeed1 = testInstance1.getSpeed();
    increaseSpeeds(testInstance1, 10);
    assertEquals(currentSpeed1 + 10, testInstance1.getSpeed());

    // Increase 20 Times
    int currentSpeed2 = testInstance1.getSpeed();
    increaseSpeeds(testInstance1, 20);
    assertEquals(currentSpeed2 + 20, testInstance1.getSpeed());
  }

  @Test
  // @GradedTest(name = "Test decreaseSpeed", max_score = 8)
  public void testDecreaseSpeed() {
    // Increase 10 Times
    increaseSpeeds(testInstance1, 10);
    assertEquals(10, testInstance1.getSpeed());

    // Decrease speed step by step
    testInstance1.decreaseSpeed();
    assertEquals(9, testInstance1.getSpeed());
    testInstance1.decreaseSpeed();
    assertEquals(8, testInstance1.getSpeed());
    // Increase 10 Times
    int currentSpeed1 = testInstance1.getSpeed();
    decreaseSpeeds(testInstance1, 3);
    assertEquals(currentSpeed1 - 3, testInstance1.getSpeed());
    // Increase 20 Times
    int currentSpeed2 = testInstance1.getSpeed();
    decreaseSpeeds(testInstance1, 4);
    assertEquals(currentSpeed2 - 4, testInstance1.getSpeed());

  }

  /**
   * This method is providing short-hand way of increasing Transmission Speed.
   */
  private void increaseSpeeds(Transmission instance, int increment) {
    for (int i = 0; i < increment; i++) {
      instance.increaseSpeed();
    }
  }

  /**
   * This method is providing short-hand way of decreasing Transmission Speed.
   */
  private void decreaseSpeeds(Transmission instance, int increment) {
    for (int i = 0; i < increment; i++) {
      instance.decreaseSpeed();
    }
  }

  @Test
  // @GradedTest(name = "Test getSpeed", max_score = 8)
  public void testGetSpeed() {
    assertEquals(0, testInstance1.getSpeed());
    assertEquals(0, testInstance2.getSpeed());

    increaseSpeeds(testInstance1, 10);
    assertEquals(10, testInstance1.getSpeed());

    increaseSpeeds(testInstance1, 10);
    assertEquals(20, testInstance1.getSpeed());

    decreaseSpeeds(testInstance1, 10);
    assertEquals(10, testInstance1.getSpeed());

    decreaseSpeeds(testInstance1, 5);
    assertEquals(5, testInstance1.getSpeed());

    decreaseSpeeds(testInstance1, 5);
    assertEquals(0, testInstance1.getSpeed());
  }

  @Test
  // @GradedTest(name = "Test getGear", max_score = 10)
  public void testGetGear() {
    assertEquals(0, testInstance1.getGear());
    assertEquals(0, testInstance2.getGear());

    increaseSpeeds(testInstance1, 10);
    assertEquals(2, testInstance1.getGear());

    increaseSpeeds(testInstance1, 10);
    assertEquals(3, testInstance1.getGear());

    decreaseSpeeds(testInstance1, 15);
    assertEquals(1, testInstance1.getGear());

    decreaseSpeeds(testInstance1, 5);
    assertEquals(0, testInstance1.getGear());
  }

  @Test
  // @GradedTest(name = "Test toString", max_score = 8)
  public void testToString() {
    // Test Instance 1
    assertEquals("Transmission (speed = 0, gear = 0)", testInstance1.toString());
    increaseSpeeds(testInstance1, 45);
    assertEquals("Transmission (speed = 45, gear = 3)", testInstance1.toString());
    increaseSpeeds(testInstance1, 25);
    assertEquals("Transmission (speed = 70, gear = 5)", testInstance1.toString());
    decreaseSpeeds(testInstance1, 70);
    assertEquals("Transmission (speed = 0, gear = 0)", testInstance1.toString());

    // Test Instance 2
    assertEquals("Transmission (speed = 0, gear = 0)", testInstance2.toString());
    increaseSpeeds(testInstance2, 80);
    assertEquals("Transmission (speed = 80, gear = 4)", testInstance2.toString());
    increaseSpeeds(testInstance2, 40);
    assertEquals("Transmission (speed = 120, gear = 6)", testInstance2.toString());
    decreaseSpeeds(testInstance2, 60);
    assertEquals("Transmission (speed = 60, gear = 3)", testInstance2.toString());
  }
}