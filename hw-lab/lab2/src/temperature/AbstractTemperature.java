package temperature;

/**
 * Abstract class that implements Temperature interface.
 */
public abstract class AbstractTemperature implements Temperature {
  protected static final double ABS_ZERO_C = -273.15;

  /** This is inCelsius method for temperature.
   * @return a celsius temperature.
   */
  @Override
  public double inCelsius() {
    return ((inFahrenheit() - 32) * (5.00 / 9.00));
  }

  /**
   * This is abstract inFahrenheit method for temperature.
   * @return a Fahrenheit temperature.
   */
  @Override
  public abstract double inFahrenheit();

  /**
   * This is inKelvin method for temperature.
   * @return a Fahrenheit temperature.
   */
  @Override
  public double inKelvin() {
    return inCelsius() - ABS_ZERO_C;
  }

  /**
   * Compare two temperature.
   *
   * @param t the temperature to compare
   * @return integer indicates the result of comparison
   */
  @Override
  public int compareTo(Temperature t) {
    if (this.inCelsius() < t.inCelsius()) {
      return -1;
    } else if (this.inCelsius() > t.inCelsius()) {
      return 1;
    } else {
      return 0;
    }
  }
}
