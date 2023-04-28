package movies;

import java.util.Objects;

/**
 * This class represents a movie. The movie has a title, director, and year of release.
 */
public class Movie implements Comparable<Movie> {
  private final String title;
  private final Person director;
  private final int year;

  /**
   * Constructs a Movie object and initializes it to the movie's title, director and year.
   *
   * @param title    the title of this movie
   * @param director the name of the movie's director
   * @param year     the year the movie was released
   */
  public Movie(String title, Person director, int year) {
    this.title = title;
    this.director = director;
    this.year = year;
  }

  /**
   * Get the title of the movie.
   *
   * @return the title of the movie
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Get the name of the director of the movie.
   *
   * @return the name of the director of the movie
   */
  public Person getDirector() {
    return this.director;
  }

  /**
   * Get the year of the movie.
   *
   * @return the year of the movie
   */
  public int getYear() {
    return this.year;
  }

  // Example: The Apartment (Billy Wilder, 1960)
  @Override public String toString() {
    return String.format(this.title + " (" + this.director.toString() + ", %d)", this.year);

  }

  // Order: year, title, director
  @Override public int compareTo(Movie o) {
    if (this.year == o.year) {
      if (this.title.equalsIgnoreCase(o.title)) {
        return this.director.compareTo(o.director);
      }
      return this.title.compareToIgnoreCase(o.title);
    }
    return this.year - o.year;
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Movie)) {
      return false;
    }
    Movie movie = (Movie) o;
    return getYear() == movie.getYear() && getTitle().equalsIgnoreCase(movie.getTitle())
        && getDirector().equals(movie.getDirector());
  }

  @Override public int hashCode() {
    return Objects.hash(getTitle().toLowerCase(), getDirector(), getYear());
  }
}
