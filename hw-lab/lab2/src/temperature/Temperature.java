/**
 * This is an interface to represent a temperature. There are methods to return the temperature in
 * Celsius, Fahrenheit, and Kelvin. It is also possible to average the value of two temperatures.
 */

package temperature;

/**
 * Represents a temperature and extends the Comparable interface.
 */
public interface Temperature extends Comparable<Temperature> {

  /**
   * Absolute zero, in degrees Celsius. For our purposes, no temperature can be below this value.
   */
  double ABS_ZERO_C = -273.15f;

  /**
   * The temperature in degrees Celsius.
   *
   * @return the temperature in degrees Celsius
   */
  double inCelsius();

  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  double inFahrenheit();

  /**
   * The temperature in degrees Kelvin.
   *
   * @return the temperature in degrees Kelvin
   */
  double inKelvin();

  /**
   * Average two temperatures and return the resulting te-mperature.
   *
   * @param t the other temperature
   * @return the new temperature
   */
  Temperature average(Temperature t);
}