package cs5004.tictactoe.controller;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;
import cs5004.tictactoe.strategy.Position;
import cs5004.tictactoe.strategy.PositionStrategy;

/**
 * A simple Player implementation that delegates most of its
 * complexity to a {@link PositionStrategy}
 * for choosing where to play next
 */
public class PlayerImpl implements Player {
  private final Piece piece;
  private PositionStrategy positionStrategy;

  public PlayerImpl(Piece piece, PositionStrategy strategy) {
    this.piece = piece;
    this.positionStrategy = strategy;
  }
  @Override
  public Position play(TicTacToeModel model) {
    return positionStrategy.choosePosition(model, this.piece);
  }

  @Override
  public Piece getPiece() {
    return this.piece;
  }
}
