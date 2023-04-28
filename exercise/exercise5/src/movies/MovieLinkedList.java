package movies;

import java.util.LinkedList;
import java.util.List;

/**
 * This class was designed as an exercise for CS 5004. It implements the MovieList interface.
 */
public class MovieLinkedList implements MovieList {
  // list and linkedlist interface, field is interface
  // linkedlist is class implement list interface
  // go for most abstract possible for object, always go for list interface than go to concrete class
  // also work if change into arraylist, also work for anything implement this interface, dynamic dispatch
  private final List<Movie> movieLinkedList;

  /**
   * Constructor. Create an empty linked list.
   */
  // creater no parameter, create a linkedlist, movie is type of linkedlist
  public MovieLinkedList() {
    movieLinkedList = new LinkedList<>();
  }

  @Override public void add(int index, Movie movie) throws IndexOutOfBoundsException {
    movieLinkedList.add(index, movie);
  }

  @Override public boolean remove(Movie movie) {
    return movieLinkedList.remove(movie);
  }
  // return a MovieList, go to most abstract interface, it behaves as object follow all methods
  // a method from interface, force you to return an object
  @Override public MovieList moviesMade(TimeIndicator timeIndicator, int year) {
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
      // not the best way, the best is getDirector().equals(director)
      // need that person have an equals method,
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
