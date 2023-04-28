package tictactoe;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Controller implementation of tic-tac-toe game.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  final Readable in;
  final Appendable out;

  /**
   * Constructor for the controller.
   *
   * @param in the input provided by the user.
   * @param out the output of the tic-tac-toe.
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  // get from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
  private boolean isNumeric(String s) {
    try {
      Double.parseDouble(s);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override public void playGame(TicTacToe m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("model is null");
    }
    Scanner sc = new Scanner(this.in);
    boolean printMsg = true;

    while (!m.isGameOver()) {
      try {
        if (printMsg) {
          this.out.append(m.toString()).append('\n');
          this.out.append("Enter a move for " + m.getTurn().toString() + ":\n");
          printMsg = false;
        }
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
      int r = -1;
      int c = -1;
      while (true) {
        String input = sc.next();
        if ("q".equals(input) || "Q".equals(input)) {
          try {
            this.out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          return;
        }
        if (!isNumeric(input)) {
          try {
            this.out.append("Not a valid number: " + input + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          continue;
        }
        r = Integer.parseInt(input);
        break;
      }

      while (true) {
        String input = sc.next();
        if ("q".equals(input) || "Q".equals(input)) {
          try {
            this.out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          return;
        }
        if (!isNumeric(input)) {
          try {
            this.out.append("Not a valid number: " + input + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
          continue;
        }
        c = Integer.parseInt(input);
        break;
      }
      try {
        m.move(r - 1, c - 1);
        printMsg = true;
      } catch (IllegalArgumentException e) {
        try {
          this.out.append("Not a valid move: " + String.valueOf(r)
              + ", " + String.valueOf(c) + "\n");
        } catch (IOException ee) {
          throw new IllegalStateException(ee);
        }
      }
    }

    try {
      this.out.append(m.toString()).append('\n');
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    if (m.getWinner() == null) {
      try {
        this.out.append("Game is over! Tie game.");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (m.getWinner() == Player.X) {
      try {
        this.out.append("Game is over! " + Player.X  + " wins!");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else if (m.getWinner() == Player.O) {
      try {
        this.out.append("Game is over! " + Player.O  + " wins!");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    }
  }
}
