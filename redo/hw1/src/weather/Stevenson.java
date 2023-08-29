package weather;

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
      throw new IllegalArgumentException("e");
    }
    if (windSpeed < 0) {
      throw new IllegalArgumentException("e");
    }
    if (totalRain < 0) {
      throw new IllegalArgumentException("e");
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
    return (int)Math.round(temperature);
  }

  /**
   * Get the dew point for this reading rounded to the nearest integer.
   *
   * @return the dew point
   */
  public int getDewPoint() {
    return (int)Math.round(dewPoint);
  }

  /**
   * Get the wind speed for this reading rounded to the nearest integer.
   *
   * @return the wind speed
   */
  public int getWindSpeed() {
    return (int)Math.round(windSpeed);
  }

  /**
   * Get the total rain of this reading (in mm).
   *
   * @return the total rain
   */
  public int getTotalRain() {
    return (int)Math.round(totalRain);
  }

  /**
   * Get the relative humidity of this weather reading rounded to the nearest integer.
   *
   * @return the relative humidity
   */
  public int getRelativeHumidity() {
    double actualVaporPressure = 6.11 * Math.pow(10, ((7.5 * dewPoint) / (237.3 + dewPoint)));
    double saturatedVaporPressure = 6.11 * Math.pow(10, ((7.5 * temperature)
        / (237.3 + temperature)));

    return (int)Math.round(actualVaporPressure / saturatedVaporPressure * 100);
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
    double r = getRelativeHumidity();
    double heatIndex = c1
        + c2 * temperature
        + c3 * r
        + c4 * temperature * r
        + c5 * temperature * temperature
        + c6 * r * r
        + c7 * temperature * temperature * r
        + c8 * temperature * r * r
        + c9 * temperature * temperature * r * r;
    return (int) Math.round(heatIndex);
  }

  /**
   * Get the wind chill rounded to the nearest integer.
   *
   * @return the wind chill
   */
  public int getWindChill() {
    double FTemp = (9.0/5.0) * temperature + 32;
    return (int)Math.round(35.74 + 0.6215
      -35.75*Math.pow(windSpeed, 0.16)
      + 0.4275 * FTemp * Math.pow(windSpeed, 0.16));
  }

  @Override
  public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d",
        getTemperature(), getDewPoint(), getWindSpeed(), getTotalRain());
  }
}
