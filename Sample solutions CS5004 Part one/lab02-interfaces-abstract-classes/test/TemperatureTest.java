import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import temperature.CelsiusTemperature;
import temperature.FahrenheitTemperature;
import temperature.Temperature;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Temperature celsiusTemperature1;
  private Temperature fahrenheitTemperature1;
  private Temperature celsiusTemperature2;
  private Temperature fahrenheitTemperature2;

  /**
   * Set up for unit tests.
   */
  @Before
  public void setUp() {
    celsiusTemperature1 = new CelsiusTemperature(40);
    fahrenheitTemperature1 = new FahrenheitTemperature(350);
    celsiusTemperature2 = new CelsiusTemperature(0);
    fahrenheitTemperature2 = new FahrenheitTemperature(32);
  }

  /**
   * Test case to test all the positive test cases for Constructor.
   */
  @Test
  // @GradedTest(name = "Test Constructor at Absolute Zero", max_score = 4)
  public void testConstructorAtAbsZero() {
    new CelsiusTemperature(-273.15f);
    new FahrenheitTemperature(-459.66f);
  }

  /**
   * Test cases to test CelsiusTemperature constructor when temperature is less than Absolute Zero.
   */
  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test first Celsius Constructor less than Absolute Zero", max_score = 4)
  public void testConstructorLessThanAbsZero() {
    new CelsiusTemperature(-273.2f);
  }

  /**
   * Test cases to test FahrenheitTemperature constructor when temperature is less than Absolute
   * Zero.
   */
  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test first Fahrenheit Constructor less than Absolute Zero", max_score = 4)
  public void testConstructorLessThanAbsZero2() {
    new FahrenheitTemperature(-459.7f);
  }

  /**
   * Test case to test String Implementation for classes CelsiusTemperature and
   * FahrenheitTemperature.
   */
  @Test
  // @GradedTest(name = "Test toString", max_score = 5)
  public void testToString() {
    assertEquals("40.0° Celsius", celsiusTemperature1.toString());
    assertEquals("0.0° Celsius", celsiusTemperature2.toString());
    assertEquals("350.0° Fahrenheit", fahrenheitTemperature1.toString());
    assertEquals("32.0° Fahrenheit", fahrenheitTemperature2.toString());
  }

  /**
   * Test case to test temperature in Celsius.
   */
  @Test
  // @GradedTest(name = "Test inCelsius", max_score = 4)
  public void testInCelsius() {
    assertEquals(40.0, celsiusTemperature1.inCelsius(), 0.01);
    assertEquals(0.0, celsiusTemperature2.inCelsius(), 0.01);
    assertEquals(176.67, fahrenheitTemperature1.inCelsius(), 0.01);
    assertEquals(0.0, fahrenheitTemperature2.inCelsius(), 0.01);
  }

  /**
   * Test case to test temperature in Fahrenheit.
   */
  @Test
  // @GradedTest(name = "Test inFahrenheit", max_score = 4)
  public void testInFahrenheit() {
    assertEquals(104.0, celsiusTemperature1.inFahrenheit(), 0.01);
    assertEquals(32.0, celsiusTemperature2.inFahrenheit(), 0.01);
    assertEquals(350.0, fahrenheitTemperature1.inFahrenheit(), 0.01);
    assertEquals(32.0, fahrenheitTemperature2.inFahrenheit(), 0.01);
  }

  /**
   * Test case to test temperature in Kelvin.
   */
  @Test
  // @GradedTest(name = "Test inKelvin", max_score = 4)
  public void testInKelvin() {
    assertEquals(313.15, celsiusTemperature1.inKelvin(), 0.01);
    assertEquals(273.15, celsiusTemperature2.inKelvin(), 0.01);
    assertEquals(449.82, fahrenheitTemperature1.inKelvin(), 0.01);
    assertEquals(273.15, fahrenheitTemperature2.inKelvin(), 0.01);
  }

  /**
   * Test case to test average temperature and the return type of averageTemperature() method.
   */
  @Test
  // @GradedTest(name = "Test averageTemperature", max_score = 6)
  public void testAverageTemperature() {
    // Checking if average values are correct.
    Temperature t1 = celsiusTemperature1.average(celsiusTemperature2);
    assertEquals(20.0, t1.inCelsius(), 0.01);

    Temperature t2 = fahrenheitTemperature1.average(fahrenheitTemperature2);
    assertEquals(191, t2.inFahrenheit(), 0.01);

    Temperature t3 = celsiusTemperature1.average(fahrenheitTemperature1);
    assertEquals(108.335, t3.inCelsius(), 0.01);

    Temperature t4 = fahrenheitTemperature1.average(celsiusTemperature1);
    assertEquals(227, t4.inFahrenheit(), 0.01);

    // Checking if correct object type is returned.
    assertTrue(t1 instanceof CelsiusTemperature);
    assertTrue(t2 instanceof FahrenheitTemperature);
    assertTrue(t3 instanceof CelsiusTemperature);
    assertTrue(t4 instanceof FahrenheitTemperature);
  }

  /**
   * Test case to test compareTo() method when objects are equal.
   */
  @Test
  // @GradedTest(name = "Test compareTo when objects are equal", max_score = 5)
  public void testCompareToWhenEqual() {
    assertEquals(0, celsiusTemperature2.compareTo(new CelsiusTemperature(0)));
    assertEquals(0, celsiusTemperature1.compareTo(new FahrenheitTemperature(104)));
    assertEquals(0, fahrenheitTemperature2.compareTo(celsiusTemperature2));
    assertEquals(0, fahrenheitTemperature1.compareTo(new FahrenheitTemperature(350)));
  }

  /**
   * Test case to test compareTo() method when object1 is greater than object2.
   */
  @Test
  // @GradedTest(name = "Test compareTo when object1 is greater", max_score = 5)
  public void testCompareToWhenGreater() {
    assertTrue(celsiusTemperature1.compareTo(celsiusTemperature2) > 0);
    assertTrue(fahrenheitTemperature1.compareTo(celsiusTemperature1) > 0);
    assertTrue(fahrenheitTemperature1.compareTo(fahrenheitTemperature2) > 0);
    assertTrue(celsiusTemperature1.compareTo(fahrenheitTemperature2) > 0);
  }

  /**
   * Test case to test compareTo() method when object1 is less than object2.
   */
  @Test
  // @GradedTest(name = "Test compareTo when object1 is smaller", max_score = 5)
  public void testCompareToWhenLess() {
    assertTrue(celsiusTemperature2.compareTo(celsiusTemperature1) < 0);
    assertTrue(celsiusTemperature1.compareTo(fahrenheitTemperature1) < 0);
    assertTrue(fahrenheitTemperature2.compareTo(fahrenheitTemperature1) < 0);
    assertTrue(fahrenheitTemperature2.compareTo(celsiusTemperature1) < 0);
  }
}