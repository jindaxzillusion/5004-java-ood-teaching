import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the Person class
 */
public class PersonTest {

  private Person john;

  @Before
  public void setUp() {

    john = new Person("John", "Doe",1945);
  }


  @Test
  public void testFirst() {
    assertEquals("John",john.getFirstName());

  }

  @Test
  public void testSecond() {
    assertEquals("Doe",john.getLastName());
  }

  @Test
  public void testYearOfBirth() {
    assertEquals(1945,john.getYearOfBirth());
  }
  
  @Test
  public void testPersonString() {
    assertEquals("John Doe",john.toString());
  }

  @Test
  public void testSamePerson() {
    Person benlerner = new Person("Ben","Lerner",1982);
    Person benaffleck = new Person("Ben","Affleck",1982);
    Person timlerner = new Person("Tim","Lerner",1982);
    Person anotherbenlerner = new Person("Ben","Lerner",1983);
    Person identicaltwin = new Person("Ben","Lerner",1982);

    assertFalse(benlerner.same(benaffleck));
    assertFalse(benlerner.same(timlerner));
    assertFalse(benlerner.same(anotherbenlerner));
    assertTrue(benlerner.same(identicaltwin));
  }

}