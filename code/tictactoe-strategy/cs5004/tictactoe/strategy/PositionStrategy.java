package cs5004.tictactoe.strategy;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;

/**
 * A Strategy interface for choosing where to play next for the given player
 */
public interface PositionStrategy {

  /**
   * Choose Position for next move of the Tic Tac Toe game.
   *
   * @param model The Tic Tac Toe model.
   * @param player The Tic Tac Toe Player (X or O).
   * @return the position to make the next move.
   */
  Position choosePosition(TicTacToeModel model, Piece player);
}
