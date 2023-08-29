import org.junit.Before;
import org.junit.Test;

import temperature.CelsiusTemperature;
import temperature.FahrenheitTemperature;
import temperature.Temperature;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature cTemp;
  private Temperature fTemp;

  /**
   * Seting up some objects of type Temperature.
   */
  @Before
  public void setUp() {
    cTemp = new CelsiusTemperature(100);
    fTemp = new FahrenheitTemperature(212);
    // You might need to add more objects here for other tests.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFTemp() {
    new FahrenheitTemperature(-1000);
  }

  @Test
  public void testObservers() {
    assertEquals(100, cTemp.inCelsius(), 0.001);
    assertEquals(212, cTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, cTemp.inKelvin(), 0.001);
  }

  @Test
  public void testInFahrenheit() {
    assertEquals(212, fTemp.inFahrenheit(), 0.001);
  }

  @Test
  public void testAverage() {
    // Test your average() method here according to its specification
  }

  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp.toString());
    assertEquals("212.0° Fahrenheit", fTemp.toString());
  }

  @Test
  public void testCompareToWhenEqual() {
    assertEquals(0, fTemp.compareTo(cTemp));
  }

  // Think of other tests that you need to write in order to validate the classes you created.

}