package soccer;

import java.util.List;

/**
 * Represents a team for soccer: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface Team {
  /**
   * Add this player to team.
   *
   * @param player the player to be added
   * @throws IllegalArgumentException if player is greater than 10 years old
   */
  void addPlayer(Player player) throws IllegalArgumentException;

  /**
   * Get all players in the team by last name alphabetically.
   *
   * @return list of all players by last name alphabetically.
   */
  List<Player> getAllPlayersByLastName();

  /**
   * Set starting line up for match.
   *
   * @param players list of the starting line up for match.
   */
  void setStartingLineup(List<Player> players);

  /**
   * Randomly assign jersey number for team member.
   *
   * @param players list of player to be assigned.
   */
  void assignJerseyNumbers(List<Player> players);

  /**
   * Getting list of starting line up players sorted by position.
   *
   * @return list of starting line up players sorted by position.
   */
  List<Player> getStartingLineupByPosition();
}
