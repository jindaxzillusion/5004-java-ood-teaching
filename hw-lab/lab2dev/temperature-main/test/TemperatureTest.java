import temperature.CelsiusTemperature;
import temperature.FahrenheitTemperature;
import temperature.Temperature;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature cTemp;
  private Temperature fTemp;
  private Temperature hotTemp;
  private Temperature coldTemp;
  private Temperature hotColdAverage;

  /**
   * Setting up some objects of type Temperature.
   */
  @Before
  public void setUp() {
    cTemp = new CelsiusTemperature(100);
    fTemp = new FahrenheitTemperature(212);
    // You might need to add more objects here for other tests.
    hotTemp = new CelsiusTemperature(2000);
    coldTemp = new FahrenheitTemperature(-300);
    hotColdAverage = hotTemp.average(coldTemp);
  }

  /**
   * Test the user can not create FahrenheitTemperature
   * below ABS_ZERO_F
   */
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFTemp() {
    new FahrenheitTemperature(-1000);
  }

  /**
   * Test the user can not create CelsiusTemperature
   * below ABS_ZERO_C
   */
  @Test(expected = IllegalArgumentException.class)
  public void testForInvalidFTemp2() {
    new CelsiusTemperature(-1000);
  }

  /**
   * Test methods inCelsius, in Fahrenheit and inKelvin
   * With different instances onf Temperature.
   */
  @Test
  public void testObservers() {
    assertEquals(100, cTemp.inCelsius(), 0.01);
    assertEquals(212, cTemp.inFahrenheit(), 0.01);
    assertEquals(373.15, cTemp.inKelvin(), 0.01);

    assertEquals(-184.44, coldTemp.inCelsius(), 0.01);
    assertEquals(-300, coldTemp.inFahrenheit(), 0.01);
    assertEquals(88.71, coldTemp.inKelvin(), 0.01);

    assertEquals(2000, hotTemp.inCelsius(), 0.01);
    assertEquals(3632, hotTemp.inFahrenheit(), 0.01);
    assertEquals(2273.15, hotTemp.inKelvin(), 0.01);
  }

  /**
   * Test the method inFahrenheit
   */
  @Test
  public void testInFahrenheit() {
    assertEquals(212, fTemp.inFahrenheit(), 0.01);
  }

  /**
   * Test the method average with different Temperature's instances.
   */
  @Test
  public void testAverage() {
    assertEquals(100, cTemp.average(fTemp).inCelsius(),0.01);
    assertEquals(212, fTemp.average(cTemp).inFahrenheit(),0.01);

    assertEquals(907.78, hotColdAverage.inCelsius() ,0.01);
    assertEquals(361.67, hotColdAverage.average(coldTemp).inCelsius(),0.01);
    assertEquals(503.89, hotColdAverage.average(fTemp).inCelsius(),0.01);

    //Same object
    assertEquals(907.78, hotColdAverage.average(hotColdAverage).inCelsius() ,0.01);

    //cold object
    Temperature newCold = new CelsiusTemperature(-20.45);
    assertEquals(-152.41, coldTemp.average(newCold).inFahrenheit() ,0.01);

  }

  /**
   * Test the toString Method.
   * This should follow the format "temperature° Unit"
   */
  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp.toString());
    assertEquals("212.0° Fahrenheit", fTemp.toString());
    assertEquals("907.8° Celsius", hotColdAverage.toString());
    assertEquals("-300.0° Fahrenheit", coldTemp.toString());
  }

  /**
   * test the method compareTo.
   * This should return 0 if the temperatures are equal.
   *                    1 if the instance is greater than the parameter's instance.
   *                    -1 if the instances is lower than the parameter's instance.
   */
  @Test
  public void testCompareToWhenEqual() {

    assertEquals(0, fTemp.compareTo(cTemp));
    assertEquals(0, cTemp.compareTo(fTemp));

    Temperature hotColdFahrenheit = new FahrenheitTemperature(hotColdAverage.inFahrenheit());
    assertEquals(0, hotColdAverage.compareTo(hotColdFahrenheit));
    assertEquals(0, hotColdFahrenheit.compareTo(hotColdAverage));
  }

  /**
   * test the method compareTo.
   * This should return 0 if the temperatures are equal.
   *                    1 if the instance is greater than the parameter's instance.
   *                    -1 if the instances is lower than the parameter's instance.
   */
  @Test
  public void testCompareToWhenGreater() {

    assertEquals(1, fTemp.compareTo(coldTemp));
    assertEquals(1, cTemp.compareTo(coldTemp));

    Temperature hotColdFahrenheit = new FahrenheitTemperature(hotColdAverage.inFahrenheit() - 100);
    assertEquals(1, hotColdAverage.compareTo(hotColdFahrenheit));
    assertEquals(-1, hotColdFahrenheit.compareTo(hotColdAverage));
  }

  /**
   * test the method compareTo.
   * This should return 0 if the temperatures are equal.
   *                    1 if the instance is greater than the parameter's instance.
   *                    -1 if the instances is lower than the parameter's instance.
   */
  @Test
  public void testCompareToWhenLower() {

    assertEquals(-1, fTemp.compareTo(hotTemp));
    assertEquals(-1, cTemp.compareTo(hotTemp));

    Temperature hotColdFahrenheit = new FahrenheitTemperature(hotColdAverage.inFahrenheit() + 100);
    assertEquals(-1, hotColdAverage.compareTo(hotColdFahrenheit));
    assertEquals(1, hotColdFahrenheit.compareTo(hotColdAverage));
  }

  // Think of other tests that you need to write in order to validate the classes you created.
}