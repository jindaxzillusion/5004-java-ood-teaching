package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents a tic tac toe game. a tic tac toe has a board, turn, isOver
 * and winner.
 */
public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  private Player turn;
  private boolean isOver;
  private Player winner;

  /**
   * Construct a tictactoe game with board, turn, isOver and winner.
   */
  public TicTacToeModel() {
    board = new Player[3][3];
    for (int i = 0; i < 3; i += 1) {
      for (int j = 0; j < 3; j += 1) {
        board[i][j] = null;
      }
    }
    this.turn = Player.X;
    this.isOver = false;
    this.winner = null;
  }

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException    if the game is over
   */
  @Override public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {
    if (isGameOver()) {
      throw new IllegalStateException("game over");
    }
    if (!inBoard(r, c)) {
      throw new IllegalArgumentException("outside board");
    }
    if (getMarkAt(r, c) != null) {
      throw new IllegalArgumentException("occupied");
    }
    if (getTurn() == Player.O) {
      board[r][c] = Player.O;
      this.turn = Player.X;
    } else if (getTurn() == Player.X) {
      board[r][c] = Player.X;
      this.turn = Player.O;
    }
    checkWin();
  }

  private boolean inBoard(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      return false;
    }
    return true;
  }

  private void checkWin() {
    for (int i = 0; i <= 2; i += 1) {
      if (board[i][0] != null && board[i][0] == board[i][1]
          && board[i][0] == board[i][2]) {
        this.winner = board[i][0];
        this.isOver = true;
      }
    }
    for (int j = 0; j <= 2; j += 1) {
      if (board[0][j] != null && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
        this.winner = board[0][j];
        this.isOver = true;
      }
    }

    if (board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
      this.winner = board[0][0];
      this.isOver = true;
    }

    if (board[0][2] != null && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
      this.winner = board[0][2];
      this.isOver = true;
    }

    boolean hasNull = false;
    for (int i = 0; i <= 2; i += 1) {
      for (int j = 0; j <= 2; j += 1) {
        if (board[i][j] == null) {
          hasNull = true;
        }
      }
    }
    if (!hasNull) {
      this.isOver = true;
      this.winner = null;
    }
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   */
  @Override public Player getTurn() {
    return this.turn;
  }

  /**
   * Return whether the game is over. The game is over when either the board is full, or one player
   * has won.
   *
   * @return true if the game is over, false otherwise
   */
  @Override public boolean isGameOver() {
    return this.isOver;
  }

  /**
   * Return the winner of the game, or {@code null} if there is no winner. If the game is not over,
   * returns {@code null}.
   *
   * @return the winner, or null if there is no winner
   */
  @Override public Player getWinner() {
    return this.winner;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  @Override public Player[][] getBoard() {
    Player[][] cpy = new Player[3][3];
    for (int i = 0; i < 3; i += 1) {
      for (int j = 0; j < 3; j += 1) {
        cpy[i][j] = board[i][j];
      }
    }
    return cpy;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (!inBoard(r, c)) {
      throw new IllegalArgumentException("outside board");
    }
    if (board[r][c] == Player.X) {
      return Player.X;
    }
    if (board[r][c] == Player.O) {
      return Player.O;
    }
    return null;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
            row -> " " + Arrays.stream(row).map(
                p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
//     This is the equivalent code as above, but using iteration, and still using
//     the helpful built-in String.join method.
//         List<String> rows = new ArrayList<>();
//         for(Player[] row : getBoard()) {
//         List<String> rowStrings = new ArrayList<>();
//         for(Player p : row) {
//         if(p == null) {
//         rowStrings.add(" ");
//         } else {
//         rowStrings.add(p.toString());
//         }
//         }
//         rows.add(" " + String.join(" | ", rowStrings));
//         }
//         return String.join("\n-----------\n", rows);
    //
  }

}
