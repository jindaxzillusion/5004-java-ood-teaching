package transmission;

/**
 * AutomaticTransmission represents a transmission system with 5 speed thresholds (6 gears).
 */
public class AutomaticTransmission implements Transmission {

  private final int threshold1;
  private final int threshold2;
  private final int threshold3;
  private final int threshold4;
  private final int threshold5;
  private int speed = 0;

  /**
   * Constructs an AutomaticTransmission class based on 5 speed thresholds.
   *
   * @param threshold1 the 1st speed threshold
   * @param threshold2 the 2nd speed threshold
   * @param threshold3 the 3rd speed threshold
   * @param threshold4 the 4th speed threshold
   * @param threshold5 the 5th speed threshold
   * @throws IllegalArgumentException if thresholds are not  in monotonically increasing order.
   */
  public AutomaticTransmission(int threshold1, int threshold2, int threshold3, int threshold4,
      int threshold5) throws IllegalArgumentException {
    if (threshold1 <= 0) {
      throw new IllegalArgumentException("Threshold 1 has to be greater than zero.");
    }
    if (threshold2 <= threshold1) {
      throw new IllegalArgumentException("Threshold 2 has to be greater than threshold 1.");
    }
    if (threshold3 <= threshold2) {
      throw new IllegalArgumentException("Threshold 3 has to be greater than threshold 2.");
    }
    if (threshold4 <= threshold3) {
      throw new IllegalArgumentException("Threshold 4 has to be greater than threshold 3.");
    }
    if (threshold5 <= threshold4) {
      throw new IllegalArgumentException("Threshold 5 has to be greater than threshold 4.");
    }

    this.threshold1 = threshold1;
    this.threshold2 = threshold2;
    this.threshold3 = threshold3;
    this.threshold4 = threshold4;
    this.threshold5 = threshold5;
  }

  @Override public void increaseSpeed() {
    this.speed++;
  }

  @Override public void decreaseSpeed() throws IllegalStateException {
    if (this.speed == 0) {
      throw new IllegalStateException("Speed is already zero.");
    }
    this.speed--;
  }

  @Override public int getSpeed() {
    return this.speed;
  }

  @Override public int getGear() {
    if (this.speed == 0) {
      return 0;
    } else if (this.speed < this.threshold1) {
      return 1;
    } else if (this.speed < this.threshold2) {
      return 2;
    } else if (this.speed < this.threshold3) {
      return 3;
    } else if (this.speed < this.threshold4) {
      return 4;
    } else if (this.speed < this.threshold5) {
      return 5;
    } else {
      return 6;
    }
  }

  @Override public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", this.getSpeed(), this.getGear());
  }
}
