package soccer;



/**
 * The interface for the soccer team controller.
 */
public interface TeamController {
  /**
   * Start the soccer game and initiate JLabel text.
   */
  void playGame();

  /**
   * Handle the event when the add player button is clicked.
   */
  void onAddPlayerBtnClick();

  /**
   * Handle the event when the create team button is clicked.
   */
  void onCreateTeamBtnClick();

  /**
   * Handle the event when the get all player button is clicked.
   */
  void onGetAllPlayerBtnClick();

  /**
   * Handle the event when the get starting line up button is clicked.
   */
  void onGetStartingLineUpBtnClick();
}
