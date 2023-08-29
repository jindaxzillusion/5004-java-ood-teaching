import static org.junit.Assert.assertEquals;

import commands.Model;
import commands.ModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the model.
 */
public class ModelTest {

  private Model m1;
  private Model m2;

  /**
   * Set up. Create two objects of type Model and set the string messages.
   */
  @Before
  public void setUp() {
    m1 = new ModelImpl();
    m2 = new ModelImpl();
    m1.setString("Hello, world!");
    m2.setString("You will never forget my name.");
  }

  /**
   * Test getString method.
   */
  @Test
  public void testGetString() {
    assertEquals("Hello, world!", m1.getString());
    assertEquals("You will never forget my name.", m2.getString());
  }

}
