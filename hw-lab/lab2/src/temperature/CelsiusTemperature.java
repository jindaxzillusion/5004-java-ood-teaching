package temperature;

/**
 * This class represents a Celsius Temperature.
 */
public class CelsiusTemperature extends AbstractTemperature {
  private final double temperature;

  /**
   * Constructor for CelsiusTemperature class.
   *
   * @param temperature in Celsius degree.
   */
  public CelsiusTemperature(double temperature) throws IllegalArgumentException {
    if (temperature <= ABS_ZERO_C) {
      throw new IllegalArgumentException("Invalid temperature");
    }
    this.temperature = temperature;
  }

  /**
   * This method return the double of temperature in double.
   * @return Fahrenheit temperature in double.
   */
  @Override
  public double inFahrenheit() {
    return temperature * 9.0 / 5.0 + 32;
  }

  /**
   * This method return the average of two temperature in Celsius.
   * @param t in Celsius degree.
   * @return CelsiusTemperature object.
   */
  public Temperature average(Temperature t) {
    double averageT = (this.inCelsius() + t.inCelsius()) / 2.0;
    return new CelsiusTemperature(averageT);
  }

  /**
   * This method return string representation for temperature in Celsius.
   * @return String for temperature in Celsius.
   */
  @Override
  public String toString() {
    return String.format("%.1f° Celsius", this.temperature);
  }
}
