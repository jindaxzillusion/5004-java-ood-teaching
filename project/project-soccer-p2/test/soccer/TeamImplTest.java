package soccer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

/**
 * Unit tests for a soccer team.
 */
public class TeamImplTest {
  private Team t1;
  private List<Player> initPlayers;

  /**
   * We create initial 10 players and a team for testing.
   */
  @org.junit.Before public void setUp() {
    initPlayers = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Player p = new Player(
          String.format("FirstName %d", i),
          String.format("LastName %d", i),
          LocalDate.of(2020, 1, 1),
          Position.FORWARD, 3);
      initPlayers.add(p);
    }
    t1 = new TeamImpl(initPlayers);
  }

  /**
   * Unit tests for less than 10 players team.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLessThan10PlayersTeam() {
    List<Player> lessPlayers = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      Player p = new Player(String.format("FirstName %d", i),
          String.format("LastName %d", i), LocalDate.of(2020, 1, 1),
          Position.FORWARD, 3);
      initPlayers.add(p);
    }
    Team t2 = new TeamImpl(lessPlayers);
  }

  /**
   * Unit tests for get all players by last name.
   */
  @org.junit.Test public void testGetAllPlayersByLastName() {
    Player p1 = new Player("Joe", "A.", LocalDate.of(2020, 1, 1),
        Position.MIDFIELDER, 3);
    t1.addPlayer(p1);
    Player p2 = new Player("Alice", "Z.", LocalDate.of(2020, 1, 1),
        Position.MIDFIELDER, 1);
    t1.addPlayer(p2);
    List<Player> sortedByLastNamePlayers = t1.getAllPlayersByLastName();
    assertEquals("A.", sortedByLastNamePlayers.get(0).getLastName());
    assertEquals("Z.", sortedByLastNamePlayers.get(11).getLastName());
  }

  /**
   * Unit tests for add player.
   */
  @org.junit.Test public void addPlayer() {
    assertEquals(10, t1.getAllPlayersByLastName().size());
    Player p1 = new Player("Joe", "A.", LocalDate.of(2020, 1, 1),
        Position.MIDFIELDER, 3);
    t1.addPlayer(p1);
    assertEquals(11, t1.getAllPlayersByLastName().size());
  }

  /**
   * Unit tests for assign random numbers.
   */
  @org.junit.Test public void assignRandomNumbers() {
    t1.assignJerseyNumbers(initPlayers);
    // test numbers are within 1 to 20
    for (Player p : initPlayers) {
      assertTrue(p.getJerseyNumber() >= 1
          && p.getJerseyNumber() <= 20);
    }
    // test numbers are unique
    Set<Integer> hashS = new HashSet<>();
    for (Player p : initPlayers) {
      hashS.add(p.getJerseyNumber());
    }
    assertEquals(10, hashS.size());
  }

  /**
   * Unit tests for get starting lineup.
   */
  @org.junit.Test public void testSetStartingLineupSize() {
    assertEquals(10, initPlayers.size());
    t1.setStartingLineup(initPlayers);
    List<Player> lineup = t1.getStartingLineupByPosition();
    assertEquals(7, lineup.size());
  }

  /*
   * one test that verifies that the list of the team's starting lineup is sorted by position.
   * (and alphabetically for the same position)
   */
  @Test
  public void testGetStartingLineupByPosition() {
    initPlayers = new ArrayList<>();
    initPlayers.add(new Player("John", "A.",
        LocalDate.of(2020, 1, 1), Position.GOALIE, 5));
    initPlayers.add(new Player("Jane", "C.",
        LocalDate.of(2020, 1, 1), Position.DEFENDER, 5));
    initPlayers.add(new Player("Mike", "B.",
        LocalDate.of(2020, 1, 1), Position.DEFENDER, 5));
    initPlayers.add(new Player("Sarah", "E.",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 5));
    initPlayers.add(new Player("Tom", "F.",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 5));
    initPlayers.add(new Player("Emily", "D.",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 5));
    initPlayers.add(new Player("David", "G.",
        LocalDate.of(2020, 1, 1), Position.FORWARD, 5));
    initPlayers.add(new Player("linux", "mint",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3));
    initPlayers.add(new Player("arch", "linux",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3));
    initPlayers.add(new Player("k", "ubuntu",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3));
    Team t3 = new TeamImpl(initPlayers);
    t3.setStartingLineup(initPlayers);
    assertEquals(7, t3.getStartingLineupByPosition().size());
    assertEquals(Position.GOALIE, t3.getStartingLineupByPosition().get(0).getPosition());
    assertEquals(Position.DEFENDER, t3.getStartingLineupByPosition().get(1).getPosition());
    assertEquals(Position.DEFENDER, t3.getStartingLineupByPosition().get(2).getPosition());
    assertEquals("D.", t3.getStartingLineupByPosition().get(1).getLastName());
    assertEquals("F.", t3.getStartingLineupByPosition().get(2).getLastName());

    assertEquals(Position.MIDFIELDER, t3.getStartingLineupByPosition().get(3).getPosition());
    assertEquals(Position.MIDFIELDER, t3.getStartingLineupByPosition().get(4).getPosition());
    assertEquals(Position.MIDFIELDER, t3.getStartingLineupByPosition().get(5).getPosition());
    assertEquals(Position.FORWARD, t3.getStartingLineupByPosition().get(6).getPosition());
  }
}