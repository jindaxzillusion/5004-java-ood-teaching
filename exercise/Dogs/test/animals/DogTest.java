package animals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DogTest {
  private Dog d1, d2;
  @Before
  public void setUp() throws Exception {
    d1 = new Dog("d1", 10, 8, "Medium", true);
    d2 = new Dog("d2", 11, 9, "Large", false);
  }
  @Test(expected=IllegalArgumentException.class)
  public void testForInvalidAge(){
    new Dog("test", -1, 30, "Large", true);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testForInvalidWeight(){
    new Dog("test", 3, -10, "Large", true);
  }

  @Test public void testGetName() {
    assertEquals("d1", this.d1.getName());
    assertEquals("d2", this.d2.getName());
  }

  @Test public void testGetAge() {
    assertEquals(10, this.d1.getAge(), 0.1);
    assertEquals(11, this.d2.getAge(), 0.1);
  }

  @Test public void testGetWeight() {
    assertEquals(8, this.d1.getWeight(), 0.1);
    assertEquals(9, this.d2.getWeight(), 0.1);
  }

  @Test public void testGetSize() {
    assertEquals("Medium", this.d1.getSize());
    assertEquals("Large", this.d2.getSize());
  }

  @Test public void testGotVaccines() {
    assertEquals(true, this.d1.getVaccines());
    assertEquals(false, this.d2.getVaccines());
  }
  @Test
  public void testToString() {
    String expected;
    expected = "Name: d1" + "\n" +
             "Age: 10" + "\n" +
             "Weight: 8" + "\n" +
             "Size: Medium" + "\n" +
             "Vaccines Status: true" + "\n";
    assertEquals(expected, d1.toString());
  }
}