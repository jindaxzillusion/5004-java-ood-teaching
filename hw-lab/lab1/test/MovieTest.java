import static org.junit.Assert.assertEquals;

import first.Movie;
import first.Person;
import org.junit.Before;
import org.junit.Test;

/**
 * These are the unit tests for the Movie class.
 */
public class MovieTest {
  private Movie m1;
  private Movie m2;
  private Person p1;
  private Person p2;

  /**
   * We create two people p1 and p2, two movies m1, m2.
   */
  @Before
  public void setUp() {
    this.p1 = new Person("John", "Doe", 1945);
    this.p2 = new Person("Sally", "Ride", 1951);
    this.m1 = new Movie("test1", p1, 1991, "India", "pop-os");
    this.m2 = new Movie("test2", p2, 1992, "USA", "ubuntu");
  }

  /**
   * We test that the method getTitle() works as expected.
   */
  @org.junit.Test public void getTitle() {
    assertEquals("test1", this.m1.getTitle());
    assertEquals("test2", this.m2.getTitle());
  }

  /**
   * We test that the method getDirector() works as expected.
   */
  @org.junit.Test
  public void testGetDirector() {
    assertEquals(this.p1, this.m1.getDirector());
  }

  /**
   * We test that the method getYear() works as expected.
   */
  @org.junit.Test
  public void testGetYear() {
    assertEquals(1991, this.m1.getYear());
    assertEquals(1992, this.m2.getYear());
  }

  /**
   * We test that the method getCountry() works as expected.
   */
  @org.junit.Test
  public void testGetCountry() {
    assertEquals("India", this.m1.getCountry());
    assertEquals("USA", this.m2.getCountry());
  }

  /**
   * We test that the method getGenre() works as expected.
   */
  @org.junit.Test
  public void testGetGenre() {
    assertEquals("pop-os", this.m1.getGenre());
    assertEquals("ubuntu", this.m2.getGenre());
  }
}