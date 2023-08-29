package temperature;

/**
 * Class for Temperature in Celsius.
 */
public class CelsiusTemperature extends AbstractTemperature {

  /**
   * Constructor to create object when temperature in Celsius.
   *
   * @param celsius temperature in celsius.
   */
  public CelsiusTemperature(double celsius) {
    super(celsius);
  }

  @Override public Temperature average(Temperature t) {
    return new CelsiusTemperature(calculateAverageTemperature(t));
  }

  @Override public String toString() {
    return String.format("%.1f° Celsius", inCelsius());
  }
}
