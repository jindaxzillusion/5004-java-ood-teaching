package temperature;

/**
 * Class for temperature in Fahrenheit.
 */
public class FahrenheitTemperature extends AbstractTemperature {

  /**
   * Constructor to create object when temperature in Fahrenheit.
   *
   * @param fahrenheit temperature in fahrenheit.
   */
  public FahrenheitTemperature(double fahrenheit) {
    super(convertFromFahrenheitToCelsius(fahrenheit));
  }

  @Override public Temperature average(Temperature t) {
    return new FahrenheitTemperature(
        convertFromCelsiusToFahrenheit(calculateAverageTemperature(t)));
  }

  @Override public String toString() {
    return String.format("%.1f° Fahrenheit", inFahrenheit());
  }
}
