package temperature;

public class CelsiusTemperature extends AbstractTemperature {
  private double temperature;
  public CelsiusTemperature(double temperature) throws IllegalArgumentException {
    if (temperature <= ABS_ZERO_C) {
      throw new IllegalArgumentException("e");
    }
    this.temperature = temperature;
  }
  /**
   * The temperature in degrees Celsius.
   *
   * @return the temperature in degrees Celsius
   */

  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  @Override public double inFahrenheit() {
    return temperature * 9.0 / 5.0 + 32;
  }


  /**
   * Average two temperatures and return the resulting temperature.
   *
   * @param t the other temperature
   * @return the new temperature
   */
  @Override public Temperature average(Temperature t) {
    return new CelsiusTemperature((this.inCelsius() + t.inCelsius()) / 2);
  }

  @Override
  public String toString() {
    return String.format("%.1f° Celsius", temperature);
  }
}
