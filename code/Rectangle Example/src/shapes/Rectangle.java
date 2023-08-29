package shapes;

/**
 * This class provides support for rectangles. It uses length and width to compute area and
 * perimeter.
 */
public class Rectangle {

  private final double length;
  private final double width;

  /**
   * This is the constructor that builds a rectangle.
   *
   * @param length the length of the rectangle.
   * @param width  the width of the rectangle.
   */
  public Rectangle(double length, double width) {
    this.length = length;
    this.width = width;
  }

  /**
   * Return the length of the rectangle.
   *
   * @return the length of the rectangle.
   */
  public double getLength() {
    return this.length;
  }

  /**
   * Return the width of the rectangle.
   *
   * @return the width of the rectangle.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Computes the area of the rectangle.
   *
   * @return the area of the rectangle.
   */
  public double area() {
    return this.length * this.width;
  }

  /**
   * Computes the perimeter of the rectangle.
   *
   * @return the perimeter of the rectangle.
   */
  public double perimeter() {
    return 2 * (this.length + this.width);
  }
}
