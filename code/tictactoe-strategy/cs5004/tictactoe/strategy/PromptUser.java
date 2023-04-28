package cs5004.tictactoe.strategy;

import java.util.Scanner;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;

/**
 * A Strategy: ask the user where to play next
 */
public class PromptUser implements PositionStrategy {
  Scanner input;
  public PromptUser() { this(new Scanner(System.in)); }
   public PromptUser(Scanner input) {
    this.input = input;
  }

  @Override
  public Position choosePosition(TicTacToeModel model, Piece player) {
    System.out.println("Enter a row and column");
    int r = input.nextInt();
    int c = input.nextInt();
    return new Position(r, c);
  }
}
