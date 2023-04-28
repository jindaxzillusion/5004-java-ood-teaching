package soccer;

import java.time.LocalDate;
import java.util.List;

/**
 * Class implementation of team controller.
 */
public class SwingTeamController implements TeamController {
  private final Team model;
  private final TeamView view;

  /**
   * Constructor for swing team controller.
   *
   * @param view view of team.
   * @param model model of team.
   */
  public SwingTeamController(Team model, TeamView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("view or model is null");
    }
    this.model = model;
    this.view = view;
    view.setEventListener(this); // connect controller to view
  }

  @Override public void playGame() {
    view.setMessageTextView("Please add 10-20 players and create your team.\n");
  }

  @Override public void onAddPlayerBtnClick() {
    String firstName = view.getFirstNameInput();
    String lastName = view.getLastNameInput();
    LocalDate dob = view.getDateOfBirthInput();
    int skillLevel = view.getSkillLevelInput();
    Position pos = view.getPositionInput();

    Player p = new Player(firstName, lastName, dob,
        pos, skillLevel);
    model.addPlayer(p);
    view.setAllInputClear();
    view.setMessageTextView("Player create Successfully.\n");
  }

  @Override public void onCreateTeamBtnClick() {
    List<Player> players = model.getAllPlayersByLastName();
    if (players.size() >= 10 && players.size() <= 20) {
      view.setMessageTextView("Team creation Successfully.\n");
    } else if (players.size() < 10) {
      view.setMessageTextView("Team creation Failed. Add at least 10 players\n");
    }
  }

  @Override public void onGetAllPlayerBtnClick() {
    List<Player> players = model.getAllPlayersByLastName();
    model.assignJerseyNumbers(players);
    String res = "";

    for (Player p : players) {
      res += "Preferred Position: " + p.getPreferredPosition()
          + ", FirstName: " + p.getFirstName()
          + ", LastName: " + p.getLastName()
          + ", JerseyNumber: " + p.getJerseyNumber()
          + "\n";
    }
    view.setResultTextView(res);
  }

  @Override public void onGetStartingLineUpBtnClick() {
    List<Player> players = model.getAllPlayersByLastName();
    model.setStartingLineup(players);
    List<Player> lineUp = model.getStartingLineupByPosition();

    String res = "";

    for (Player p : lineUp) {
      res += "Position: " + p.getPosition()
          + ", FirstName: " + p.getFirstName()
          + ", LastName: " + p.getLastName()
          + ", JerseyNumber: " + p.getJerseyNumber()
          + "\n";
    }
    view.setResultTextView(res);
  }
}
