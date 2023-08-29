package temperature;

/**
 * This class represents a FahrenheitTemperature with a unit and a temperature, it extends
 * the AbstractTemperature Class.
 */
public class FahrenheitTemperature extends AbstractTemperature {

    /**
     * Construct a FahrenheitTemperature Class with a temperature and unit
     * @param temperature the value that represents this temperature.
     * @throws IllegalArgumentException IF the temperature is lower than ABS_ZERO_F.
     */
    public FahrenheitTemperature( double temperature) throws IllegalArgumentException {

        super(AbstractTemperature.FAHRENHEIT, temperature);
    }


}
