package first;

import static org.junit.Assert.*;

import first.Movie;
import first.Person;

public class MovieTest {
  private Person p1;
  private Movie m1;
  @org.junit.Before public void setUp() throws Exception {
    p1 = new Person("test", "test", 1990);
    m1 = new Movie("title1", p1, 1900, "german", "comic");
  }

  @org.junit.Test public void getTitle() {
    assertEquals("title1", m1.getTitle());
  }

  @org.junit.Test public void getDirector() {
    assertEquals(p1, m1.getDirector());
  }

  @org.junit.Test public void getYear() {
    assertEquals(1900, m1.getYear());
  }

  @org.junit.Test public void getCountry() {
    assertEquals("german", m1.getCountry());
  }

  @org.junit.Test public void getGenre() {
    assertEquals("comic", m1.getGenre());
  }
}