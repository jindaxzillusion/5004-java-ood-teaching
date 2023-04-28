package soccer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Driver class for a soccer team.
 */
public class MainSwingTeam {
  /**
   * Main method showing how the program works.
   *
   * @param args keyboard input.
   */
  public static void main(String[] args) {
    Team m = new TeamImpl();
    TeamView v = new SwingTeamView("Soccer Team");
    TeamController controller = new SwingTeamController(m, v);
    controller.playGame();
  }
}
