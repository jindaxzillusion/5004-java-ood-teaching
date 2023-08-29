package weather;

import java.util.Objects;

/**
 * Class representing a weather reading from a Stevenson station.
 */
public final class Stevenson {

  private final double temperature;
  private final double dewPoint;
  private final double windSpeed;
  private final int totalRain;

  /**
   * Constructor.
   *
   * @param temperature the air temperature in Celsius
   * @param dewPoint    the dew point
   * @param windSpeed   the wind speed
   * @param totalRain   the total rain
   * @throws IllegalArgumentException if any of the values are illegal.
   */
  public Stevenson(final double temperature, final double dewPoint, final double windSpeed,
      final int totalRain) throws IllegalArgumentException {
    if (dewPoint > temperature) {
      throw new IllegalArgumentException("Dew point cannot be greater than temperature.");
    }
    if (windSpeed < 0) {
      throw new IllegalArgumentException("Wind speed cannot be negative.");
    }
    if (totalRain < 0) {
      throw new IllegalArgumentException("Total rain cannot be negative.");
    }
    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.totalRain = totalRain;
  }

  /**
   * Get the temperature of this reading rounded to the nearest integer.
   *
   * @return the temperature
   */
  public int getTemperature() {
    return (int) Math.round(temperature);
  }

  /**
   * Get the dew point for this reading rounded to the nearest integer.
   *
   * @return the dew point
   */
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  /**
   * Get the wind speed for this reading rounded to the nearest integer.
   *
   * @return the wind speed
   */
  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  /**
   * Get the total rain of this reading (in mm).
   *
   * @return the total rain
   */
  public int getTotalRain() {
    return totalRain;
  }

  /**
   * Get the relative humidity of this weather reading rounded to the nearest integer.
   *
   * @return the relative humidity
   */
  public int getRelativeHumidity() {
    return (int) Math.round(calculateRelativeHumidity());
  }

  /**
   * Get the heat index for this weather reading rounded to the nearest integer.
   *
   * @return the heat index
   */
  public int getHeatIndex() {
    final double c1 = -8.78469475556;
    final double c2 = 1.61139411;
    final double c3 = 2.33854883889;
    final double c4 = -0.14611605;
    final double c5 = -0.012308094;
    final double c6 = -0.0164248277778;
    final double c7 = 0.002211732;
    final double c8 = 0.00072546;
    final double c9 = -0.000003582;
    final double r = calculateRelativeHumidity();
    final double t = this.temperature;
    double heatIndex = c1 + c2 * t + c3 * r + c4 * t * r + c5 * t * t + c6 * r * r + c7 * t * t * r
        + c8 * t * r * r + c9 * t * t * r * r;
    return (int) Math.round(heatIndex);
  }

  /**
   * Get the wind chill rounded to the nearest integer.
   *
   * @return the wind chill
   */
  public int getWindChill() {
    double t = calculateTemperatureInFahrenheit();
    double v = this.windSpeed;
    double windChill =
        35.74 + 0.6215 * t - 35.75 * Math.pow(v, 0.16) + 0.4275 * t * Math.pow(v, 0.16);
    return (int) Math.round(convertFtoC(windChill));
  }

  @Override public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d", getTemperature(),
        getDewPoint(), getWindSpeed(), getTotalRain());
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Stevenson)) {
      return false;
    }

    Stevenson other = (Stevenson) o;
    boolean isEqual = this.getTemperature() == other.getTemperature();
    isEqual &= this.getDewPoint() == other.getDewPoint();
    isEqual &= this.getWindSpeed() == other.getWindSpeed();
    isEqual &= this.getTotalRain() == other.getTotalRain();
    return isEqual;
  }

  @Override public int hashCode() {
    return Objects.hash(this.getTemperature(), this.getDewPoint(), this.getWindSpeed(),
        this.getTotalRain());
  }

  private double calculateVaporPressure(double temperatureInCelsius) {
    return 6.11 * Math.pow(10, ((7.5 * temperatureInCelsius) / (237.3 + temperatureInCelsius)));
  }

  private double calculateRelativeHumidity() {
    return  100.0 * (calculateVaporPressure(dewPoint) / calculateVaporPressure(temperature));
  }

  private double calculateTemperatureInFahrenheit() {
    return this.temperature * 9.0 / 5.0 + 32.0;
  }

  private double convertFtoC(double degF) {
    return (degF - 32.0) * 5.0 / 9.0;
  }
}
