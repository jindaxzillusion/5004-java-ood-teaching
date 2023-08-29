import static org.junit.Assert.assertEquals;

import first.Person;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {
 
  private Person john;
  private Person sally;

  /**
   * We create two people p1 and p2.
   */
  @Before
  public void setUp() {
    this.john = new Person("John", "Doe", 1945);
    this.sally = new Person("Sally", "Ride", 1951);
  }

  /**
   * We test that the method getFirstName() works as expected.
   */
  @Test
  public void testGetFirstName() {
    assertEquals("John", this.john.getFirstName());
    assertEquals("Sally", this.sally.getFirstName());
  }

  /**
   * We test that the method getAge() works as expected.
   */
  @Test
  public void testGetAge() {
    assertEquals(75, this.john.getAge());
    assertEquals(69, this.sally.getAge());
  }

  /**
   * We test that the method getFullName() works as expected.
   */
  @Test
  public void testGetFullName() {
    assertEquals("John Doe", this.john.getFullName());
    assertEquals("Sally Ride", this.sally.getFullName());
  }
}