package soccer;

import java.time.LocalDate;

/**
 * The interface for the team view.
 */
public interface TeamView {
  /**
   * Sets the event listener for the team controller.
   * @param controller the team controller
   */
  void setEventListener(TeamController controller);

  /**
   * Sets the event listener for the team controller.
   * @param text the message text
   */
  void setMessageTextView(String text);

  /**
   * Sets the message text view.
   * @param text the result text
   */
  void setResultTextView(String text);

  /**
   * Clears all input fields.
   */
  void setAllInputClear();

  /**
   * Gets the first name input from the view.
   * @return the first name input
   */
  String getFirstNameInput();

  /**
   * Gets the last name input from the view.
   * @return the last name input
   */
  String getLastNameInput();

  /**
   * Gets the date of birth input from the view.
   * @return the date of birth input
   */
  LocalDate getDateOfBirthInput();

  /**
   * Gets the skill level input from the view.
   * @return the skill level input
   */
  int getSkillLevelInput();

  /**
   * Gets the position input from the view.
   * @return the position input
   */
  Position getPositionInput();
}
