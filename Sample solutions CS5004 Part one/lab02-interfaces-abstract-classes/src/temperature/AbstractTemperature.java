package temperature;

/**
 * Abstract Class for implementing Temperature.
 */
public abstract class AbstractTemperature implements Temperature {
  private final double tempInC;

  /**
   * Constructor to create object when temperature is passed in Celsius.
   *
   * @param celsius temperature in celsius.
   * @throws IllegalArgumentException when temperature is below ABS_ZERO_C
   */
  protected AbstractTemperature(double celsius) throws IllegalArgumentException {
    if (celsius < ABS_ZERO_C) {
      throw new IllegalArgumentException("Temperature cannot be below " + ABS_ZERO_C);
    }
    this.tempInC = celsius;
  }

  /**
   * Converts a temperature in Celsius into a Temperature if Fahrenheit.
   *
   * @param temperatureInC the temperature in Celsius.
   * @return the temperature in Fahrenheit.
   */
  protected static double convertFromCelsiusToFahrenheit(double temperatureInC) {
    return (temperatureInC * (9 / 5.0)) + 32;
  }

  /**
   * Converts a temperature in Fahrenheit into a Temperature if Celsius.
   *
   * @param temperatureInF the temperature in Fahrenheit.
   * @return the temperature in Celsius.
   */
  protected static double convertFromFahrenheitToCelsius(double temperatureInF) {
    return (temperatureInF - 32) * (5 / 9.0);
  }

  /**
   * Calculates average of two temperatures.
   *
   * @param t other temperature object.
   * @return average temperature in Celsius.
   */
  protected double calculateAverageTemperature(Temperature t) {
    return (this.inCelsius() + t.inCelsius()) / 2.0;
  }

  @Override public double inCelsius() {
    return tempInC;
  }

  @Override public double inFahrenheit() {
    return convertFromCelsiusToFahrenheit(tempInC);
  }

  @Override public double inKelvin() {
    return tempInC - ABS_ZERO_C;
  }

  @Override public int compareTo(Temperature o) {
    return (int) (this.inCelsius() - o.inCelsius());
  }
}
