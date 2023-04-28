import static org.junit.Assert.assertEquals;

import first.MenuItem;
import org.junit.Before;
import org.junit.Test;

/**
 * These are the unit tests for the MenuItem class.
 */
public class MenuItemTest {
  private MenuItem m1;
  private MenuItem m2;

  /**
   * We create two MenuItem m1 and m2.
   */
  @Before public void setUp() {
    m1 = new MenuItem("m1", "description1", true, 200);
    m2 = new MenuItem("m2", "description2", false, 300);
  }

  /**
   * We test that the method getName() works as expected.
   */
  @Test public void testGetName() {
    assertEquals("m1", this.m1.getName());
    assertEquals("m2", this.m2.getName());
  }

  /**
   * We test that the method getName() works as expected.
   */
  @Test public void testGetDescription() {
    assertEquals("description1", this.m1.getDescription());
    assertEquals("description2", this.m2.getDescription());
  }

  /**
   * We test that the method getIsVegetarian() works as expected.
   */
  @Test public void testGetIsVegetarian() {
    assertEquals(true, this.m1.getIsVegetarian());
    assertEquals(false, this.m2.getIsVegetarian());
  }

  /**
   * We test that the method getPrice() works as expected.
   */
  @Test public void testGetPrice() {
    assertEquals(200, this.m1.getPrice(), 0.1);
    assertEquals(300, this.m2.getPrice(), 0.1);
  }
}