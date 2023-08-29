import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import temperature.CelsiusTemperature;
import temperature.FahrenheitTemperature;
import temperature.Temperature;


/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature celsiusTemp;
  private Temperature fahrenheitTemp;
  private Temperature celsiusTemp2;
  private Temperature fahrenheitTemp2;

  /**
   * Setting up some objects of type Temperature.
   */
  @Before
  public void setUp() {
    celsiusTemp = new CelsiusTemperature(100);
    fahrenheitTemp = new FahrenheitTemperature(212);
    // You might need to add more objects here for other tests.
    celsiusTemp2 = new CelsiusTemperature(10);
    fahrenheitTemp2 = new FahrenheitTemperature(21);
  }

  /**
   * Testing for Invalid Celsius Temperature construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidCelsiusTemp() {
    new CelsiusTemperature(-1000);
  }

  /**
   * Testing Invalid Fahrenheit Temperature construction.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFahrenheitTemp() {
    new FahrenheitTemperature(-1000);
  }

  /**
   * Testing inCelsius, inFahrenheit, inKelvin methods.
   */
  @Test
  public void testObservers() {
    assertEquals(100, celsiusTemp.inCelsius(), 0.001);
    assertEquals(212, celsiusTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, celsiusTemp.inKelvin(), 0.001);
  }

  /**
   * Testing inCelsius method.
   */
  @Test
  public void testInCelsius() {
    assertEquals(21, fahrenheitTemp2.inCelsius(), 0.001);
  }

  /**
   * Testing inFahrenheit method.
   */
  @Test
  public void testInFahrenheit() {
    assertEquals(212, fahrenheitTemp2.inFahrenheit(), 0.001);
  }

  /**
   * Testing inKelvin method.
   */
  @Test
  public void testInKalvin() {
    assertEquals(283.15, celsiusTemp2.inKelvin(), 0.001);
  }

  /**
   * Testing average method.
   */
  @Test
  public void testAverage() {
    Temperature avg1 = celsiusTemp.average(fahrenheitTemp);
    assertEquals(100, avg1.inCelsius(), 0.001);
    Temperature avg2 = fahrenheitTemp2.average(celsiusTemp2);
    assertEquals(35.5, avg2.inFahrenheit(), 0.001);
  }

  /**
   * Testing toString method.
   */
  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", celsiusTemp.toString());
    assertEquals("212.0° Fahrenheit", fahrenheitTemp.toString());
  }

  /**
   * Testing compareTo method.
   */
  @Test
  public void testCompareToWhenEqual() {
    assertEquals(0, fahrenheitTemp.compareTo(celsiusTemp));
    assertEquals(0, fahrenheitTemp.compareTo(fahrenheitTemp));
    assertEquals(0, celsiusTemp.compareTo(celsiusTemp));
  }
}