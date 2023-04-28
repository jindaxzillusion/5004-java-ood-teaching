package transmission;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class contains all the unit tests for automatic transmission.
 */
public class AutomaticTransmissionTest {
  AutomaticTransmission t1;

  @org.junit.Before
  public void setUp() {
    t1 = new AutomaticTransmission(2, 3, 4, 5, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeThresholds() {
    new AutomaticTransmission(-1, 0, 1, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidThresholds() {
    new AutomaticTransmission(10, 9, 8, 7, 6);
  }

  @org.junit.Test public void testIncreaseSpeed() {
    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 1);
    assertEquals(t1.getGear(), 1);

    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 2);
    assertEquals(t1.getSpeed(), 2);

    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 3);
    assertEquals(t1.getSpeed(), 3);

    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 4);
    assertEquals(t1.getSpeed(), 4);

    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 5);
    assertEquals(t1.getSpeed(), 5);
  }

  @org.junit.Test public void testDecreaseSpeed() {
    t1.increaseSpeed();
    t1.increaseSpeed();
    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 3);
    t1.decreaseSpeed();
    assertEquals(t1.getSpeed(), 2);
  }

  @Test(expected = IllegalStateException.class)
  public void testDecreaseUnder0() {
    t1.decreaseSpeed();
  }

  @org.junit.Test public void getSpeed() {
    t1.increaseSpeed();
    t1.increaseSpeed();
    t1.increaseSpeed();
    assertEquals(t1.getSpeed(), 3);
  }

  @org.junit.Test public void getGear() {
    t1.increaseSpeed();
    t1.increaseSpeed();
    t1.increaseSpeed();
    assertEquals(t1.getGear(), 3);
  }

  @org.junit.Test public void testToString() {
    assertEquals(t1.toString(), "Transmission (speed = 0, gear = 0)");
    t1.increaseSpeed();
    t1.increaseSpeed();
    assertEquals(t1.toString(), "Transmission (speed = 2, gear = 2)");
  }
}