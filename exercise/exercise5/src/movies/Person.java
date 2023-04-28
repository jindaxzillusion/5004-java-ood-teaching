package movies;

import java.util.Objects;

/**
 * This class represents a person, the person has a first name and last name.
 */
// update Person with comparable because need to change when comparing movie
public class Person implements Comparable<Person> {
  private final String firstName;
  private final String lastName;

  /**
   * Constructs a Person object and initializes it to the given first name and last name.
   *
   * @param firstName the first name of this person
   * @param lastName  the last name of this person
   */
  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Get the first name of this person.
   *
   * @return the first name of this person
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Return the last name of this person.
   *
   * @return the last name of this person
   */
  public String getLastName() {
    return this.lastName;
  }

  @Override public String toString() {
    return this.getFirstName() + " " + this.getLastName();
  }

  @Override public int compareTo(Person o) {
    // check which last name comes equal, then compare first name
    // equals ignore case, always think if you should ignore case
    if (this.lastName.equalsIgnoreCase(o.lastName)) {
      return this.firstName.compareTo(o.firstName);
    }
    // compare lastname
    // string class have compareTo
    return this.lastName.compareTo(o.lastName);
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o; // casting to make it not an object
    return Objects.equals(getFirstName().toLowerCase(), person.getFirstName().toLowerCase())
        && Objects.equals(getLastName().toLowerCase(), person.getLastName().toLowerCase());
  }

  @Override public int hashCode() {
    return Objects.hash(getFirstName().toLowerCase(), getLastName().toLowerCase());
  }
}

