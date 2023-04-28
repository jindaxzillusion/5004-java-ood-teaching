package first;

/**
 * This class represents a movie with a title, a director and a
 * year, a country and a genre.
 */
public class Movie {
  private final String title;
  private final Person director;
  private final int year;
  private final String country;
  private final String genre;

  /**
   * This is the constructor that builds a movie.
   *
   * @param title the title of the movie.
   * @param director director of movie.
   * @param year release year of movie
   * @param country country of movie
   * @param genre genre of genre
   */
  public Movie(String title, Person director, int year, String country, String genre) {
    this.title = title;
    this.director = director;
    this.year = year;
    this.country = country;
    this.genre = genre;
  }

  /**
   * Return the title of the movie.
   *
   * @return the title of the movie.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Return the director of the movie.
   *
   * @return the director of the movie.
   */
  public Person getDirector() {
    return this.director;
  }

  /**
   * Return the year of the movie.
   *
   * @return the year of the movie.
   */
  public int getYear() {
    return this.year;
  }

  /**
   * Return the country of the movie.
   *
   * @return the country of the movie.
   */
  public String getCountry() {
    return this.country;
  }

  /**
   * Return the genre of the movie.
   *
   * @return the genre of the movie.
   */
  public String getGenre() {
    return this.genre;
  }
}
