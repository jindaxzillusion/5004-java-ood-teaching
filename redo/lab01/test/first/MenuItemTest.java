package first;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MenuItemTest {
  private MenuItem m1;
  @Before public void setUp() throws Exception {
    m1 = new MenuItem("menu1", "des", false,  3.1);
  }

  @Test public void getName() {
    assertEquals("menu1", m1.getName());
  }

  @Test public void getDescription() {
    assertEquals("des", m1.getDescription());
  }

  @Test public void isVegetarian() {
    assertEquals(false, m1.isVegetarian());
  }

  @Test public void getPrice() {
    assertEquals(3.1, m1.getPrice(), 0.1);
  }
}