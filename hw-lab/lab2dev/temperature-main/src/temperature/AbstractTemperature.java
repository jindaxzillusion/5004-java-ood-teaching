package temperature;

/**
 * This class Represents an abstraction of temperature with units and temperature value.
 * It implements Temperature's interface.
 */
public abstract class AbstractTemperature implements Temperature{

    protected final String unit;
    protected final double temperature;

    public static final String CELSIUS = "Celsius";
    public static final String FAHRENHEIT = "Fahrenheit";


    /**
     * Constructs a Temperature and initialize it to the given unit and temperature value
     * @param unit Type of unit. It can be Celsius Or Fahrenheit
     * @param temperature Represent the temperature value
     * @throws IllegalArgumentException IF the temperature is lower than ABS_ZERO_C.
     */
    public AbstractTemperature(String unit, double temperature) throws IllegalArgumentException  {
        this.unit = unit;
        this.temperature = temperature;

        if (this.inCelsius() < Temperature.ABS_ZERO_C) {
            throw new IllegalArgumentException("Temperature can not be lower than Absolute Zero");
        }
    }

    @Override
    public double inCelsius() {
        return this.isCelsius() ? this.getTemperature() : (this.getTemperature() - 32 ) * 5/9;
    }

    @Override
    public double inFahrenheit() {
        return this.isCelsius() ? (this.getTemperature() * 9/5) +  32 : this.getTemperature();
    }

    @Override
    public double inKelvin() {
        return this.inCelsius() - Temperature.ABS_ZERO_C;
    }

    @Override
    public Temperature average (Temperature otherTemperature) {

        Temperature newTemperature;

        if (this.isCelsius()) {
            double averageTemp = (this.inCelsius() + otherTemperature.inCelsius()) / 2;
            newTemperature =  new CelsiusTemperature(averageTemp);
        } else {
            double averageTemp = (this.inFahrenheit()  + otherTemperature.inFahrenheit()) / 2;
            newTemperature = new FahrenheitTemperature(averageTemp);
        }

        return newTemperature;
    }

    @Override
    public String toString() {
        return  String.format("%.1f",this.getTemperature()) + "° " + this.getUnit();
    }

    @Override
    public int compareTo(Temperature otherTemperature) {

        double comparator = this.inCelsius() - otherTemperature.inCelsius();
        return comparator == 0 ? 0 : comparator > 0 ? 1 : -1;
    }

    /**
     * Verify if the unit type is Celsius
     * @return True if this is a Celsius Unit. Otherwise, false.
     */
    protected boolean isCelsius () {
        return this.getUnit() == AbstractTemperature.CELSIUS;
    }

    /**
     * Get the unit of this object - Celsius or Fahrenheit
     *
     * @return the unit of this temperature
     */
    protected String getUnit() {
        return unit;
    }

    /**
     * Get the temperature of this object
     *
     * @return the temperature of the instance
     */
    protected double getTemperature() {
        return temperature;
    }

}
