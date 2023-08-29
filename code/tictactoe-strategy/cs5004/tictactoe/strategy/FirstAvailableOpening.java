package cs5004.tictactoe.strategy;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;

/**
 * A Strategy: find the first (topmost-leftmost) available spot
 */
public class FirstAvailableOpening implements PositionStrategy {
  @Override
  public Position choosePosition(TicTacToeModel model, Piece player) {
    for (int r = 0; r < model.getHeight(); r++) {
      for (int c = 0; c < model.getWidth(); c++) {
        if (model.getPieceAt(r, c) == Piece.EMPTY) {
          return new Position(r, c);
        }
      }
    }
    return null;
  }
}
