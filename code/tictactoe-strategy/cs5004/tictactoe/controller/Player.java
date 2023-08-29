package cs5004.tictactoe.controller;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;
import cs5004.tictactoe.strategy.Position;

/**
 * A simple Player interface
 */
public interface Player {
  Position play(TicTacToeModel model);
  Piece getPiece();
}
