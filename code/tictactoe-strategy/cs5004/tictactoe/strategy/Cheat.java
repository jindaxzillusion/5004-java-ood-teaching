package cs5004.tictactoe.strategy;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;

/**
 * A Strategy: when no one's looking fill in the board and claim to "move"
 * to the last spot to win.
 */
public class Cheat implements PositionStrategy {

  @Override
  public Position choosePosition(TicTacToeModel model, Piece player) {
    for (int c = 0; c < model.getWidth() - 1; c++)
      model.setPieceAt(0, c, player);
    return new Position(0, model.getWidth() - 1);
  }
}
