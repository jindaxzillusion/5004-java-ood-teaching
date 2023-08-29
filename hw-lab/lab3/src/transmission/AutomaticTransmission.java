package transmission;

/**
 * This class represents an automatic Transmission. An automatic Transmission has a speed,
 * current gear level, and gear level limits.
 */
public class AutomaticTransmission implements Transmission {
  private int speed;
  private int currentGear;
  private final int gear1To2;
  private final int gear2To3;
  private final int gear3To4;
  private final int gear4To5;
  private final int gear5To6;

  /**
   * Construct an automatic transmission object that has the provided gear levels.
   *
   * @param gear1To2 the gear speed from level 1 to level 2
   * @param gear2To3 the gear speed from level 2 to level 3
   * @param gear3To4 the gear speed from level 3 to level 4
   * @param gear4To5 the gear speed from level 4 to level 5
   * @param gear5To6 the gear speed from level 5 to level 6
   */
  public AutomaticTransmission(int gear1To2, int gear2To3, int gear3To4,
      int gear4To5, int gear5To6) throws IllegalArgumentException {
    if (gear1To2 <= 0 || gear2To3 < 0 || gear3To4 < 0 || gear4To5 < 0 || gear5To6 < 0) {
      throw new IllegalArgumentException("gear speed should be < 0");
    }
    if (gear1To2 >= gear2To3 || gear2To3 >= gear3To4
        || gear3To4 >= gear4To5 || gear4To5 >= gear5To6) {
      throw new IllegalArgumentException("gear threshold input should be increasing");
    }
    this.speed = 0;
    this.currentGear = 0;
    this.gear1To2 = gear1To2;
    this.gear2To3 = gear2To3;
    this.gear3To4 = gear3To4;
    this.gear4To5 = gear4To5;
    this.gear5To6 = gear5To6;
  }

  /**
   * Increases the speed of this Transmission by 1 MPH.
   */
  @Override public void increaseSpeed() {
    this.speed += 1;
    switchGear(speed);
  }

  private void switchGear(int speed) {
    if (speed == 0) {
      currentGear = 0;
    }
    if (speed > 0 && speed < gear1To2) {
      currentGear = 1;
    }
    if (speed >= gear1To2 && speed < gear2To3) {
      currentGear = 2;
    }
    if (speed >= gear2To3 && speed < gear3To4) {
      currentGear = 3;
    }
    if (speed >= gear3To4 && speed < gear4To5) {
      currentGear = 4;
    }
    if (speed >= gear4To5 && speed < gear5To6) {
      currentGear = 5;
    }
    if (speed >= gear5To6) {
      currentGear = 6;
    }
  }

  /**
   * Decreases the speed of this Transmission by 1 MPH.
   *
   * @throws IllegalStateException if calling this method would cause the speed to go below 0
   */
  @Override public void decreaseSpeed() throws IllegalStateException {
    if (this.speed == 0) {
      throw new IllegalStateException("speed cannot be negative");
    }
    speed -= 1;
    switchGear(speed);
  }

  /**
   * Gets the speed of this Transmission.
   *
   * @return the speed
   */
  @Override public int getSpeed() {
    return this.speed;
  }

  /**
   * Gets the gear of this Transmission.
   *
   * @return the gear
   */
  @Override public int getGear() {
    return this.currentGear;
  }

  /**
   * Gets the string representation of this Transmission.
   *
   * @return the string representation.
   */
  @Override public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", this.speed, this.currentGear);
  }
}
