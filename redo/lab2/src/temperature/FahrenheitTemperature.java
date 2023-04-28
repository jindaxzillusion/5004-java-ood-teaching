package temperature;

public class FahrenheitTemperature extends AbstractTemperature {
  private final double temperature;

  public FahrenheitTemperature(double temperature) throws IllegalArgumentException{
    if (temperature <= ABS_ZERO_C * 9.0 / 5.0 + 32) {
      throw new IllegalArgumentException("e");
    }
    this.temperature = temperature;
  }


  /**
   * The temperature in degrees Fahrenheit.
   *
   * @return the temperature in degrees Fahrenheit
   */
  @Override public double inFahrenheit() {
    return temperature;
  }


  /**
   * Average two temperatures and return the resulting temperature.
   *
   * @param t the other temperature
   * @return the new temperature
   */
  @Override public Temperature average(Temperature t) {
    return new FahrenheitTemperature(this.inFahrenheit() + t.inFahrenheit() / 2.0);
  }

  @Override
  public String toString() {
    return String.format("%.1f° Fahrenheit", temperature);
  }
}
