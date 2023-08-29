package movies;

import java.util.LinkedList;
import java.util.List;

public class MovieLinkedList implements MovieList{
  private LinkedList<Movie> movieLinkedList;

  public MovieLinkedList() {
    this.movieLinkedList = new LinkedList<>();
  }
  @Override
  public void add(int index, Movie movie) throws IndexOutOfBoundsException {
    movieLinkedList.add(index, movie);
  }

  @Override
  public boolean remove(Movie movie) {
    return movieLinkedList.remove(movie);
  }

  private boolean checkTimeIndicator(TimeIndicator timeIndicator, int year, Movie movie) {
    int curYear = movie.getYear();
    if (timeIndicator == TimeIndicator.BEFORE) {
      return curYear < year;
    } else if (timeIndicator == timeIndicator.DURING) {
      return curYear == year;
    } else if (timeIndicator == timeIndicator.AFTER) {
      return curYear > year;
    } else {
      return false;
    }
  }

  @Override
  public MovieList moviesMade(TimeIndicator timeIndicator, int year) {
//    MovieList result = new MovieLinkedList();
//    for (Movie movie : movies){
//      if (checkTimeIndicator(timeIndicator, year, movie)) {
//        result.add(0, movie);
//      }
//    }
//    return result;
    MovieList filteredList = new MovieLinkedList();
    int index = 0;
    switch (timeIndicator) {
      case AFTER:
        for (Movie m: this.movieLinkedList) {
          if (m.getYear() > year) {
            filteredList.add(index, m);
            index ++;
          }
        }
        break;
      case BEFORE:
        for (Movie m: this.movieLinkedList) {
          if (m.getYear() == year) {
            filteredList.add(index, m);
            index ++;
          }
        }
        break;
      default:
    }
    return filteredList;
  }

  @Override public MovieList moviesDirectedBy(Person director) {
//    MovieList result = new MovieLinkedList();
//    for (Movie movie: this.movieLinkedList) {
//      if (movie.getDirector().equals(director)){
//        result.add(0, movie);
//      }
//    }
//    return result;
    MovieList filteredList = new MovieLinkedList();
    int index = 0;

    for (Movie m: this.movieLinkedList) {
      if (m.getDirector().toString().equalsIgnoreCase(director.toString())){
        filteredList.add(index, m);
        index ++;
      }

    }
    return filteredList;
  }
}
