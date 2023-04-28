package animals;

/**
 * Size enum. Options: Small, Medium, Large.
 */
public enum Size {
  SMALL("Small"),
  MEDIUM("Medium"),
  LARGE("Large");

  private final String display;

  /**
   * The size will display without capitalization.
   * @param display the String that will be displayed.
   */
  Size(String display) {
    this.display = display;
  }

  @Override
  public String toString() {
    return this.display;
  }
}
