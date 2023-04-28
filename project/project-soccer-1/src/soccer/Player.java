package soccer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Player for a soccer team.
 */
public class Player {
  private final String firstName;
  private final String lastName;
  private final LocalDate dateOfBirth;
  private final Position preferredPosition;
  private Position position;
  private final int skillLevel;
  private int jerseyNumber;

  /**
   * Constructor for the player.
   *
   * @param firstName the first name of a player.
   * @param lastName the last name of a player.
   * @param dateOfBirth the date of birth of a player.
   * @param preferredPosition the preferred position of birth of a player.
   * @param skillLevel the skill level of a player.
   * @throws IllegalArgumentException if player is greater than 10 years old
   */
  public Player(String firstName, String lastName, LocalDate dateOfBirth,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException {
    if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
      throw new IllegalArgumentException("first name or last name cannot be empty");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.preferredPosition = preferredPosition;
    this.skillLevel = skillLevel;
    LocalDate curDate = LocalDate.now();
    long age = ChronoUnit.YEARS.between(dateOfBirth, curDate);
    if (age >= 10) {
      throw new IllegalArgumentException("age must be < 10");
    }
    if (skillLevel < 1 || skillLevel > 5) {
      throw new IllegalArgumentException("skill level should be 1-5");
    }
  }

  /**
   * Return first name of player.
   *
   * @return First name of player.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Return last name of player.
   *
   * @return Last name of player.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Return date of birth of player.
   *
   * @return Date of birth of player.
   */
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Return preferred position of player.
   *
   * @return Preferred position of player.
   */
  public Position getPreferredPosition() {
    return preferredPosition;
  }

  /**
   * Return skill Level of player.
   *
   * @return Skill Level of player.
   */
  public int getSkillLevel() {
    return skillLevel;
  }

  /**
   * Return jersey number of player.
   *
   * @return Jersey number of player.
   */
  public int getJerseyNumber() {
    return jerseyNumber;
  }

  /**
   * Set Jersey number of player.
   *
   * @param num Jersey number of player.
   */
  public void setJerseyNumber(int num) {
    this.jerseyNumber = num;
  }

  /**
   * Set player's position in the match.
   *
   * @param pos Player's position in the match.
   */
  public void setPosition(Position pos) {
    this.position = pos;
  }

  /**
   * Return player's position in the match.
   *
   * @return Player's position in the match.
   */
  public Position getPosition() {
    return this.position;
  }
}
