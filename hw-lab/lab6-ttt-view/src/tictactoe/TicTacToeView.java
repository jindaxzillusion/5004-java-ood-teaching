package tictactoe;

/**
 * Interface for tic-tac-toe view.
 */
public interface TicTacToeView {
  /**
   * Add the Controller to the view.
   *
   * @param tttController the implementation of our controller.
   */
  void setEventListener(TicTacToeController tttController);

  /**
   * Show text view of turn information.
   * @param text string display turn information.
   */
  void setTurnTextView(String text);

  /**
   * Update tic-tac-toe game board.
   * @param player string representation of current player
   * @param r row of board move
   * @param c column of board move
   */
  void setPlayerMoveTextView(String player, int r, int c);

  /**
   * If game is over, players are not allow place moves.
   */
  void setAllButtonDisabled();
}
