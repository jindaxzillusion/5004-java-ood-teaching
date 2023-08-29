package tictactoe;

/**
 * Represents a Controller for Tic Tac Toe: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface TicTacToeController {
  /**
   * Start a tic-tac-toe game.
   */
  void playGame();

  /**
   * Handle cell click event.
   * @param r row of cell being clicked
   * @param c column of cell being clicked
   */
  void onCellClick(int r, int c);
}
