package transmission;

public class AutomaticTransmission implements Transmission{
  /**
   * Increases the speed of this Transmission by 1 MPH.
   */
  private final int one2two;
  private final int two2three;
  private final int three2four;
  private final int four2five;
  private final int five2six;
  private int speed;
  private int gear;

  public AutomaticTransmission(int one2two, int two2three, int three2four, int four2five, int five2six) throws IllegalArgumentException {
    if (one2two >= two2three || two2three >= three2four || three2four >= four2five || four2five >= five2six) {
      throw new IllegalArgumentException("e");
    }
    if (one2two < 0 || two2three < 0 || three2four < 0 || four2five < 0) {
      throw new IllegalArgumentException("e");
    }
    this.one2two = one2two;
    this.two2three = two2three;
    this.three2four = three2four;
    this.four2five = four2five;
    this.five2six = five2six;
    this.speed = 0;
    this.gear = 0;
  }

  private void switchGear() {
    if (this.getSpeed() == 0) {
      gear = 0;
    } else if (getSpeed() > 0 && getSpeed() < one2two) {
      gear = 1;
    } else if (getSpeed() >= one2two && getSpeed() < two2three) {
      gear = 2;
    } else if (getSpeed() >= two2three && getSpeed() < three2four) {
      gear = 3;
    } else if (getSpeed() >= three2four && getSpeed() < four2five) {
      gear = 4;
    } else if (getSpeed() >= four2five && getSpeed() < five2six) {
      gear = 5;
    } else {
      gear = 6;
    }
  }

  @Override public void increaseSpeed() {
    this.speed += 1;
    switchGear();
  }

  /**
   * Decreases the speed of this Transmission by 1 MPH.
   *
   * @throws IllegalStateException if calling this method would cause the speed to go below 0
   */
  @Override public void decreaseSpeed() throws IllegalStateException {
    if (getSpeed() == 0) {
      throw new IllegalStateException("e");
    }
    speed -= 1;
    switchGear();
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
    return this.gear;
  }

  @Override public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", getSpeed(), getGear());
  }
}
