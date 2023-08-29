package movies;

import java.util.LinkedList;
import java.util.List;

/**
 * This class was designed as an exercise for CS 5004. It implements the MovieList interface.
 */
public class MovieLinkedList implements MovieList {

  private final List<Movie> movieLinkedList;

  /**
   * Constructor. Create an empty list.
   */
  public MovieLinkedList() {
    movieLinkedList = new LinkedList<>();
  }

  @Override
  public void add(int index, Movie movie) throws IndexOutOfBoundsException {
    movieLinkedList.add(index, movie);
  }

  @Override
  public boolean remove(Movie movie) {
    return movieLinkedList.remove(movie);
  }

  @Override
  public MovieList moviesMade(TimeIndicator timeIndicator, int year) {
    MovieList filteredList = new MovieLinkedList();
    int index = 0;
    switch (timeIndicator) {
      case AFTER:
        for (Movie m : this.movieLinkedList) {
          if (m.getYear() > year) {
            filteredList.add(index, m);
            index++;
          }
        }
        break;
      case BEFORE:
        for (Movie m : this.movieLinkedList) {
          if (m.getYear() < year) {
            filteredList.add(index, m);
            index++;
          }
        }
        break;
      case DURING:
        for (Movie m : this.movieLinkedList) {
          if (m.getYear() == year) {
            filteredList.add(index, m);
            index++;
          }
        }
        break;
      default:
    }
    return filteredList;
  }

  @Override public MovieList moviesDirectedBy(Person director) {
    MovieList filteredList = new MovieLinkedList();
    int index = 0;

    for (Movie m : this.movieLinkedList) {
      if (m.getDirector().toString().equalsIgnoreCase(director.toString())) {
        filteredList.add(index, m);
        index++;
      }
    }
    return filteredList;
  }

  @Override public String toString() {
    StringBuilder stringList = new StringBuilder();
    for (Movie movie : this.movieLinkedList) {
      stringList.append(movie.toString()).append("\n");
    }
    return stringList.toString();
  }

}
