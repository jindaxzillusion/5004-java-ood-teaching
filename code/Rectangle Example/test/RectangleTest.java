import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import shapes.Rectangle;

/**
 * These are the unit tests for the Rectangle class.
 */
public class RectangleTest {

  private Rectangle r1, r2;

  /**
   * We create two rectangles r1 and r2.
   */
  @Before
  public void setUp() {
    r1 = new Rectangle(2, 7);
    r2 = new Rectangle(10, 5);
  }

  /**
   * We test that the method getLength() works as expected.
   */
  @Test
  public void testGetLength() {
    assertEquals(2, this.r1.getLength(), 0.1);
    assertEquals(10, this.r2.getLength(), 0.1);
  }

  /**
   * We test that the method getWidth() works as expected.
   */
  @Test
  public void testGetWidth() {
    assertEquals(7, this.r1.getWidth(), 0.1);
    assertEquals(5, this.r2.getWidth(), 0.1);
  }

  /**
   * We test that the method area() works as expected.
   */
  @Test
  public void testArea() {
    assertEquals(14, this.r1.area(), 0.1);
    assertEquals(50, this.r2.area(), 0.1);
  }

  /**
   * We test that the method perimeter() works as expected.
   */
  @Test
  public void testPerimeter() {
    assertEquals(18, this.r1.perimeter(), 0.1);
    assertEquals(30, this.r2.perimeter(), 0.1);
  }
}