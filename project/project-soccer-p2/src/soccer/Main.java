package soccer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Driver class for a soccer team.
 */
public class Main {
  /**
   * Main method showing how the program works.
   *
   * @param args keyboard input.
   */
  public static void main(String[] args) {
    List<Player> initPlayers = new ArrayList<>();
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
    initPlayers.add(new Player("linux", "fedora",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3));
    initPlayers.add(new Player("arch", "linux",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3));
    initPlayers.add(new Player("linux", "mint",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3));

    Team t1;
    try {
      t1 = new TeamImpl(initPlayers);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("team size should be 10-20");
    }
    System.out.println("Team created successfully.");

    Player p1 = new Player("pop_!", "os",
        LocalDate.of(2020, 1, 1), Position.MIDFIELDER, 3);
    try {
      t1.addPlayer(p1);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("player must < 10 yrs old");
    }
    System.out.println("Player added: " + p1.getFirstName()
        + " " + p1.getLastName());
    
    t1.assignJerseyNumbers(t1.getAllPlayersByLastName());
    t1.setStartingLineup(t1.getAllPlayersByLastName());

    System.out.println("Your Current Team Member:");
    for (Player p : initPlayers) {
      System.out.println("Preferred Position: " + p.getPreferredPosition()
              + ", FirstName: " + p.getFirstName()
              + ", LastName: " + p.getLastName()
              + ", JerseyNumber: " + p.getJerseyNumber()
      );
    }

    List<Player> lineup = t1.getStartingLineupByPosition();
    System.out.println("Your Starting Lineup:");
    for (Player p : lineup) {
      System.out.println("Position: " + p.getPosition()
          + ", FirstName: " + p.getFirstName()
          + ", LastName: " + p.getLastName()
          + ", JerseyNumber: " + p.getJerseyNumber()
      );
    }
  }
}
