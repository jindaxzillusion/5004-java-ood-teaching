import static org.junit.Assert.assertEquals;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import weather.Stevenson;

/**
 * Grading tests for the WeatherReading class for Module 1 Lab.
 * Auto-grader weight = 30 / 100
 */
public class StevensonTests {
  private double[] temps;
  private double[] dewPoints;
  private double[] winds;
  private int[] rains;
  private Stevenson[] readings;

  /**
   * Setting up some data.
   */
  @Before
  public void setup() {
    Random rand = new Random();
    int size = 100;
    temps = new double[size];
    dewPoints = new double[size];
    winds = new double[size];
    rains = new int[size];
    readings = new Stevenson[size];
    for (int i = 0; i < size; i++) {
      temps[i] = rand.nextDouble() * 100;
      dewPoints[i] = temps[i] - rand.nextDouble() * 20;
      winds[i] = rand.nextDouble() * 50;
      rains[i] = rand.nextInt(100);
      readings[i] = new Stevenson(temps[i], dewPoints[i], winds[i], rains[i]);
    }
  }

  /**
   * Tests a bad dew point.
   * Weight = 2
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBadDewPoint() {
    new Stevenson(80, 90, 10, 7);
  }

  /**
   * Tests a bad wind speed.
   * Weight = 2
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBadWindSpeed() {
    new Stevenson(80, 64, -3, 7);
  }

  /**
   * Tests a bad rainfall.
   * Weight = 2
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBadRainfall() {
    new Stevenson(80, 64, 5, -7);
  }

  /**
   * Tests getDewPoint().
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetDewPoint() {
    for (int i = 0; i < readings.length; i++) {
      int expected = (int) Math.round(dewPoints[i]);
      assertEquals(expected, readings[i].getDewPoint());
    }
  }

  /**
   * Tests getTemperature().
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetTemperature() {
    for (int i = 0; i < readings.length; i++) {
      int expected = (int) Math.round(temps[i]);
      assertEquals(expected, readings[i].getTemperature());
    }
  }

  /**
   * Tests getWindSpeed().
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetWindSpeed() {
    for (int i = 0; i < readings.length; i++) {
      int expected = (int) Math.round(winds[i]);
      assertEquals(expected, readings[i].getWindSpeed());
    }
  }

  /**
   * Tests getTotalRain().
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetTotalRain() {
    for (int i = 0; i < readings.length; i++) {
      int expected = Math.round(rains[i]);
      assertEquals(expected, readings[i].getTotalRain());
    }
  }

  /**
   * Tests getRelativeHumidity().
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetRelativeHumidity() {
    for (int i = 0; i < readings.length; i++) {
      double rh = calculateRelativeHumidity(temps[i], dewPoints[i]);
      int expected = (int) Math.round(rh);
      assertEquals(expected, readings[i].getRelativeHumidity());
    }
  }

  /**
   * Tests getHeatIndex().
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetHeatIndex() {
    for (int i = 0; i < readings.length; i++) {
      int expected = (int) Math.round(calcHeatIndex(temps[i], dewPoints[i]));
      assertEquals(expected, readings[i].getHeatIndex());
    }
  }

  /**
   * Tests getWindChill.
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testGetWindChill() {
    for (int i = 0; i < readings.length; i++) {
      int expected = (int) Math.round(calcWindChill(temps[i], winds[i]));
      assertEquals(expected, readings[i].getWindChill());
    }
  }

  /**
   * Tests the toString method.
   * Weight = 3
   */
  @Test(timeout = 3000)
  public void testToString() {
    for (int i = 0; i < readings.length; i++) {
      String expected = String.format("Reading: T = %d, D = %d, v = %d, rain = %d",
          (int) Math.round(temps[i]), (int) Math.round(dewPoints[i]), (int) Math.round(winds[i]),
          Math.round(rains[i]));
      assertEquals(expected, readings[i].toString());
    }
  }

  private double calculateRelativeHumidity(double t, double dp) {
    return 100.0 * (calculateVaporPressure(dp) / calculateVaporPressure(t));
  }

  private double calculateVaporPressure(double temperatureInCelsius) {
    return 6.11 * Math.pow(10, ((7.5 * temperatureInCelsius) / (237.3 + temperatureInCelsius)));
  }

  private double calcHeatIndex(double t, double dp) {
    double c1 = -8.78469475556;
    double c2 = 1.61139411;
    double c3 = 2.33854883889;
    double c4 = -0.14611605;
    double c5 = -0.012308094;
    double c6 = -0.0164248277778;
    double c7 = 0.002211732;
    double c8 = 0.00072546;
    double c9 = -0.000003582;
    double r = calculateRelativeHumidity(t, dp);
    return c1 + c2 * t + c3 * r + c4 * t * r + c5 * t * t + c6 * r * r + c7 * t * t * r
        + c8 * t * r * r + c9 * t * t * r * r;
  }

  private double calcWindChill(double temp, double v) {
    double t = convertCtoF(temp);
    double windChill = 35.74 + 0.6215 * t - 35.75 * Math.pow(v, 0.16)
        + 0.4275 * t * Math.pow(v, 0.16);
    return (int) Math.round(convertFtoC(windChill));
  }

  private double convertCtoF(double degC) {
    return degC * 9.0 / 5.0 + 32.0;
  }

  private double convertFtoC(double degF) {
    return (degF - 32.0) * 5.0 / 9.0;
  }
}
