package weather;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * These are the unit tests for the Stevenson class.
 */
public class StevensonTest {
  private Stevenson s1;
  private Stevenson s2;

  /**
   * We create a Stevenson reading s1.
   */
  @org.junit.Before
  public void setUp() {
    s1 = new Stevenson(30, 20, 60, 100);
    s2 = new Stevenson(20, 10, 30, 10);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testIllegalWindSpeed() {
    Stevenson s3 = new Stevenson(30, 20, -1, 100);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testIllegalTotalRain() {
    Stevenson s4 = new Stevenson(30, 20, 10, -1);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testIllegalDewPoint() {
    Stevenson s5 = new Stevenson(30, 100, 10, 100);
  }

  @org.junit.Test public void getTemperature() {
    assertEquals(30, s1.getTemperature());
    assertEquals(20, s2.getTemperature());
  }

  @org.junit.Test public void getDewPoint() {
    assertEquals(20, s1.getDewPoint());
    assertEquals(10, s2.getDewPoint());
  }

  @org.junit.Test public void getWindSpeed() {
    assertEquals(60, s1.getWindSpeed());
    assertEquals(30, s2.getWindSpeed());
  }

  @org.junit.Test public void getTotalRain() {
    assertEquals(100, s1.getTotalRain());
    assertEquals(10, s2.getTotalRain());
  }

  @org.junit.Test public void getRelativeHumidity() {
    assertEquals(55, s1.getRelativeHumidity());
    assertEquals(53, s2.getRelativeHumidity());
  }

  @org.junit.Test public void getHeatIndex() {
    assertEquals(32, s1.getHeatIndex(), 0.1);
    assertEquals(25, s2.getHeatIndex(), 0.1);
  }

  @org.junit.Test public void getWindChill() {
    assertEquals(33, s1.getWindChill(), 0.1);
    assertEquals(19, s2.getWindChill(), 0.1);
  }

  @org.junit.Test public void testToString() {
    assertEquals("Reading: T = 30, D = 20, v = 60, rain = 100", s1.toString());
  }
}