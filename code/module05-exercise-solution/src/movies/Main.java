package movies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * We read a movie file, clean it and sort it.
 */
public class Main {

  /**
   * We read a file with info about movies and store them in a set.
   *
   * @param args arguments for the function (none needed)
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Please enter path to movie list: ");
    File file = new File(sc.nextLine());

    Set<Movie> movieTree = new TreeSet<>();

    try {
      Scanner scanner = new Scanner(file);
      scanner.nextLine();  // This is the line with the description of the columns; we skip it.

      while (scanner.hasNextLine()) {
        Movie newMovie = turnStringIntoMovie(scanner.nextLine());
        movieTree.add(newMovie);
      }
      scanner.close();

      // Print movie list as read from the file
      System.out.println("Sorted list of movies:");
      for (Movie element : movieTree) {
        System.out.println(element.toString());
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static Movie turnStringIntoMovie(String movieString) throws IllegalArgumentException {
    // Movie info
    String[] movieInfoArray = movieString.split(",");

    // Director Info
    String[] directorInfoArray = movieInfoArray[1].split(" ");
    String firstName = directorInfoArray[0];
    StringBuilder lastName = new StringBuilder();
    for (int index = 1; index < directorInfoArray.length; index++) {
      lastName.append(directorInfoArray[index]);
    }

    return new Movie(movieInfoArray[0], new Person(firstName, lastName.toString()),
        Integer.parseInt(movieInfoArray[2]));
  }
}
