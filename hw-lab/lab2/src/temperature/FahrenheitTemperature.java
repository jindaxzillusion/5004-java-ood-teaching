package temperature;

/**
 * This class represents a Fahrenheit Temperature.
 */
public class FahrenheitTemperature extends AbstractTemperature {
  private final double temperature;

  /**
   * Constructor for FahrenheitTemperature class.
   *
   * @param temperature in Fahrenheit degree.
   */
  public FahrenheitTemperature(double temperature) throws IllegalArgumentException {
    if (temperature < ABS_ZERO_C * 9.0 / 5.0 + 32) {
      throw new IllegalArgumentException("Temperature cannot be smaller than absolute zero");
    }
    this.temperature = temperature;
  }

  /**
   * This method return the double of temperature in double.
   * @return Fahrenheit temperature in double.
   */
  @Override
  public double inFahrenheit() {
    return temperature;
  }

  /**
   * This method return the average of two temperature in Fahrenheit.
   * @param t in Fahrenheit degree.
   * @return FahrenheitTemperature object.
   */
  public Temperature average(Temperature t) {
    double averageT = (this.inFahrenheit() + t.inFahrenheit()) / 2.0;
    return new FahrenheitTemperature(averageT);
  }

  /**
   * This method return string representation for temperature in Fahrenheit.
   * @return String for temperature in Fahrenheit.
   */
  @Override
  public String toString() {
    return String.format("%.1f° Fahrenheit", this.temperature);
  }
}
