package cs5004.tictactoe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cs5004.tictactoe.model.*;
import cs5004.tictactoe.strategy.Position;

/**
 * Simple TicTacToe controller
 */
public class TTTController implements TicTacToeController {
  private final TicTacToeModel model;
  private final List<Player> players;
  private final Appendable output;

  public TTTController(TicTacToeModel model, Appendable output) {
    this.model = Objects.requireNonNull(model);
    this.players = new ArrayList<>();
    this.output = Objects.requireNonNull(output);
  }

  @Override
  public void addPlayer(Player player) {
    this.players.add(Objects.requireNonNull(player));
  }

  @Override
  public void play() {
    int playerIndex = 0;
    while (this.model.gameStatus() == Status.Playing) {
      this.printBoard();
      Position pos = this.players.get(playerIndex).play(new ReadonlyTicTacToeModel(this.model));
      try {
        this.model.setPieceAt(pos.r, pos.c, this.players.get(playerIndex).getPiece());
        playerIndex = (playerIndex + 1) % this.players.size();
      } catch (Exception e) {

      }
    }
    this.printBoard();
    try {
      if (this.model.gameStatus() == Status.Won)
        output.append("Player ").append(this.model.getWinner().toString()).append(" won");
      else
        output.append("Tie game");
    } catch (IOException e) {

    }
  }

  public void printBoard() {
    try {
      output.append("\n");
      for (int r = 0; r < this.model.getHeight(); r++) {
        if (r > 0) {
          for (int c = 0; c < this.model.getWidth(); c++) {
            if (c > 0)
              output.append("+");
            output.append("---");
          }
          output.append("\n");
        }
        for (int c = 0; c < this.model.getWidth(); c++) {
          if (c > 0) output.append(" | ");
          output.append(this.model.getPieceAt(r, c).toString());
        }
        output.append("\n");
      }
    } catch (IOException e) {

    }
  }
}
