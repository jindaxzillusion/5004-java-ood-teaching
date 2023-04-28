import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import movies.Movie;
import movies.MovieLinkedList;
import movies.MovieList;
import movies.Person;
import movies.TimeIndicator;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the MovieList Interface and its implementations.
 */
public class MovieListTest {
  private Movie theApartment;
  private Movie laDolceVita;
  private Movie drStrangeLove;
  private Movie blazingSaddles;

  private MovieList myMovieList;

  /**
   * Set up movies and lists to use for our tests.
   */
  @Before public void setUp() {
    Person billyWilder = new Person("Billy", "Wilder");
    this.theApartment = new Movie("The Apartment", billyWilder, 1960);
    Person federicoFellini = new Person("Federico", "Fellini");
    this.laDolceVita = new Movie("La Dolce Vita", federicoFellini, 1960);
    Person stanleyKubrick = new Person("Stanley", "Kubrick");
    this.drStrangeLove = new Movie("Dr. Strangelove", stanleyKubrick, 1964);
    Person melBrooks = new Person("Mel", "Brooks");
    this.blazingSaddles = new Movie("Blazing Saddles", melBrooks, 1974);

    this.myMovieList = new MovieLinkedList();
  }

  /**
   * Test adding movies to the list.
   */
  @Test public void testAdd() {
    assertEquals("", this.myMovieList.toString());
    this.myMovieList.add(0, this.blazingSaddles);
    assertEquals("Blazing Saddles (Mel Brooks, 1974)\n", this.myMovieList.toString());
    this.myMovieList.add(0, this.laDolceVita);
    String listString =
        "La Dolce Vita (Federico Fellini, 1960)\n" + "Blazing Saddles (Mel Brooks, 1974)\n";
    assertEquals(listString, this.myMovieList.toString());
  }

  /**
   * Test trying to add a movie with the wrong index.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testAddWithWrongIndex() {
    this.myMovieList.add(3, this.theApartment);
  }

  /**
   * Test removing a movie from the list.
   */
  @Test public void testRemove() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(1, this.laDolceVita);
    String listString =
        "Blazing Saddles (Mel Brooks, 1974)\n" + "La Dolce Vita (Federico Fellini, 1960)\n";
    assertEquals(listString, this.myMovieList.toString());
    assertTrue(this.myMovieList.remove(this.laDolceVita));
    assertEquals("Blazing Saddles (Mel Brooks, 1974)\n", this.myMovieList.toString());
  }

  /**
   * Test trying to remove a movie that is not on the list.
   */
  @Test public void testRemoveOneMovieNotOnTheList() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(0, this.laDolceVita);
    assertTrue(this.myMovieList.remove(this.laDolceVita));
    assertFalse(this.myMovieList.remove(this.drStrangeLove));
  }

  /**
   * Test filter for movies made before a certain year.
   */
  @Test public void testMoviesMadeBefore() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(0, this.laDolceVita);
    this.myMovieList.add(0, this.drStrangeLove);
    this.myMovieList.add(0, this.theApartment);

    MovieList beforeList = this.myMovieList.moviesMade(TimeIndicator.BEFORE, 1964);
    String listString =
        "The Apartment (Billy Wilder, 1960)\n" + "La Dolce Vita (Federico Fellini, 1960)\n";
    assertEquals(listString, beforeList.toString());
  }

  /**
   * Test filter for movies made during a certain year.
   */
  @Test public void testMoviesMadeDuring() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(0, this.laDolceVita);
    this.myMovieList.add(0, this.drStrangeLove);
    this.myMovieList.add(0, this.theApartment);

    MovieList duringList = this.myMovieList.moviesMade(TimeIndicator.DURING, 1974);
    assertEquals("Blazing Saddles (Mel Brooks, 1974)\n", duringList.toString());
  }

  /**
   * Test filter for movies made after a certain year.
   */
  @Test public void testMoviesMadeAfter() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(0, this.laDolceVita);
    this.myMovieList.add(0, this.drStrangeLove);
    this.myMovieList.add(0, this.theApartment);

    MovieList duringList = this.myMovieList.moviesMade(TimeIndicator.AFTER, 1964);
    assertEquals("Blazing Saddles (Mel Brooks, 1974)\n", duringList.toString());
  }

  @Test public void testMoviesMadeAfterWithEmptyList() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(0, this.laDolceVita);
    this.myMovieList.add(0, this.drStrangeLove);
    this.myMovieList.add(0, this.theApartment);

    MovieList duringList = this.myMovieList.moviesMade(TimeIndicator.AFTER, 1974);
    assertEquals("", duringList.toString());

  }

  /**
   * Test filter for movies directed by a particular director.
   */
  @Test public void moviesDirectedBy() {
    this.myMovieList.add(0, this.blazingSaddles);
    this.myMovieList.add(0, this.laDolceVita);
    this.myMovieList.add(0, this.drStrangeLove);
    this.myMovieList.add(0, this.theApartment);

    MovieList felliniList = this.myMovieList.moviesDirectedBy(new Person("Federico", "Fellini"));
    assertEquals("La Dolce Vita (Federico Fellini, 1960)\n", felliniList.toString());

    MovieList brooksList = this.myMovieList.moviesDirectedBy(new Person("mel", "brooks"));
    assertEquals("Blazing Saddles (Mel Brooks, 1974)\n", brooksList.toString());
  }
}