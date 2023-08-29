package movies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
public class MovieDriver {
  public static void main(String[] args) {
    Set<Movie> movieSet = new TreeSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader("longer_list_movies.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        Movie movie = new Movie(values[0], values[1], Integer.parseInt(values[2]));
        movieSet.add(movie);
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }

    System.out.println("Original Set: ");
    movieSet.forEach(System.out::println);

    // Filter movies released in the 90s
    Set<Movie> filteredMovies = new TreeSet<>();
    movieSet.stream()
        .filter(m -> m.getYear() >= 1990 && m.getYear() < 2000)
        .forEach(filteredMovies::add);

    System.out.println("\nMovies released in the 90s: ");
    filteredMovies.forEach(System.out::println);

    // Map movie titles to upper case
    Set<String> upperCaseTitles = movieSet.stream()
        .map(Movie::getTitle)
        .map(String::toUpperCase)
        .collect(java.util.stream.Collectors.toSet());

    System.out.println("\nMovie Titles in Upper Case: ");
    upperCaseTitles.forEach(System.out::println);

    // Find the average year of all movies
    double averageYear = movieSet.stream()
        .mapToInt(Movie::getYear)
        .average()
        .orElse(0.0);

    System.out.println("\nAverage Year of All Movies: " + averageYear);

  }
}
