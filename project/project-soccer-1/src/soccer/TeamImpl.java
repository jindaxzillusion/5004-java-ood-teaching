package soccer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Team implementation for a soccer team for children under 10 years old.
 */
public class TeamImpl implements Team {
  private final List<Player> players;
  private final List<Player> startingLineup;
  private int size;

  /**
   * Constructor for the team.
   *
   * @param initialPlayers the initial team members in a team.
   * @throws IllegalArgumentException if team size < 10 or > 20.
   */
  public TeamImpl(List<Player> initialPlayers) throws IllegalArgumentException {
    if (initialPlayers.size() < 10 || initialPlayers.size() > 20) {
      throw new IllegalArgumentException("team size should be 10-20");
    }
    players = new ArrayList<>(initialPlayers);
    startingLineup = new ArrayList<>();
    size = initialPlayers.size();
  }

  @Override public List<Player> getAllPlayersByLastName() {
    List<Player> sortedPlayers = new ArrayList<>(players);
    sortedPlayers.sort(Comparator.comparing(Player::getLastName));
    return sortedPlayers;
  }

  private List<Player> sortBySkill(List<Player> players) {
    List<Player> sortedPlayers = new ArrayList<>(players);
    sortedPlayers.sort(Comparator.comparing(Player::getSkillLevel));
    Collections.reverse(sortedPlayers);
    return sortedPlayers;
  }

  @Override public void addPlayer(Player player) throws IllegalArgumentException {
    LocalDate curDate = LocalDate.now();
    long age = ChronoUnit.YEARS.between(player.getDateOfBirth(), curDate);
    if (age >= 10) {
      throw new IllegalArgumentException("age must be < 10");
    }
    if (size < 20) {
      this.players.add(player);
      size += 1;
    } else {
      List<Player> sortedBySkillPlayers = sortBySkill(this.players);
      Player lowestSkillPlayer = sortedBySkillPlayers.get(sortedBySkillPlayers.size() - 1);
      if (player.getSkillLevel() >= lowestSkillPlayer.getSkillLevel()) {
        players.remove(lowestSkillPlayer);
        players.add(player);
      }
    }
  }

  @Override public void assignJerseyNumbers(List<Player> players) {
    int[] numbers = new int[20];
    int i;
    for (i = 0; i < 20; i++) {
      numbers[i] = i + 1;
    }
    Collections.shuffle(Arrays.asList(numbers));
    i = 0;
    for (Player p : players) {
      int num = numbers[i];
      p.setJerseyNumber(num);
      i += 1;
    }
  }

  @Override public void setStartingLineup(List<Player> players) {
    List<Player> sortedBySkillPlayers = sortBySkill(players);
    List<Player> lineUp = sortedBySkillPlayers.subList(0, 7);

    int goalieCount = 1;
    int defenderCount = 2;
    int midfielderCount = 3;
    int forwardCount = 1;

    for (Player p : lineUp) {
      if (goalieCount > 0) {
        startingLineup.add(p);
        p.setPosition(Position.GOALIE);
        goalieCount -= 1;
      } else if (defenderCount > 0) {
        startingLineup.add(p);
        p.setPosition(Position.DEFENDER);
        defenderCount -= 1;
      } else if (midfielderCount > 0) {
        startingLineup.add(p);
        p.setPosition(Position.MIDFIELDER);
        midfielderCount -= 1;
      } else if (forwardCount > 0) {
        startingLineup.add(p);
        p.setPosition(Position.FORWARD);
        forwardCount -= 1;
      }
    }
  }

  @Override public List<Player> getStartingLineupByPosition() {
    List<Player> lineup = new ArrayList<>(startingLineup);
    lineup.sort(Comparator.comparing(Player::getPosition)
        .thenComparing(Player::getLastName));
    return lineup;
  }
}
