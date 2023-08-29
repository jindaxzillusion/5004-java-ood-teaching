package first;

public class Movie {
  private String title;
  private Person director;
  private int year;
  private String country;
  private String genre;
  public Movie(String title, Person director, int year, String country, String genre) {
    this.title = title;
    this.director = director;
    this.year = year;
    this.country = country;
    this.genre = genre;
  }

  public String getTitle() {
    return title;
  }

  public Person getDirector() {
    return director;
  }

  public int getYear() {
    return year;
  }

  public String getCountry() {
    return country;
  }

  public String getGenre() {
    return genre;
  }
}
