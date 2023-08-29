package solitaire;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the Marble Solitaire class. A Marble Solitaire game has board, arm length,
 * isOver game status, and boardSize.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private String[][] board;
  private int armLength;
  private boolean isOver;
  private int boardSize;

  /**
   * Constructor, Create a default Marble Solitarie with armLength 3.
   */
  public MarbleSolitaireModelImpl() {
    this(3,3,3);
  }

  /**
   * A constructor should take one parameter: armLength.
   * It should initialize the game board with given arm length.
   * Arm length should be odd number and no less than 3, otherwise an IllegalArgumentException
   * will be thrown.
   *
   * @param armLength length of arm
   */
  public MarbleSolitaireModelImpl(int armLength) {
    this(armLength, (3 * armLength - 3) / 2, (3 * armLength - 3) / 2);
  }

  /**
   * A constructor should take two parameters: emptyRow and emptyColumn.
   * It should initialize the game board so that the arm thickness is 3 and
   * the empty slot is at the position (emptyRow, emptyColumn).
   * location of empty row and empty column should be on board,
   * otherwise an IllegalArgumentException will be thrown.
   * @param emptyRow row of empty piece
   * @param emptyCol col of empty piece
   */
  public MarbleSolitaireModelImpl(int emptyRow, int emptyCol) {
    this(3, emptyRow, emptyCol);
  }

  /**
   * A constructor should take three parameters: emptyRow and emptyColumn, and armLength.
   * It should initialize the game board so that the arm thickness is armLength and
   * the empty slot is at the position (emptyRow, emptyColumn).
   * Arm length should be odd number and no less than 3, location of empty row and empty column
   * should be on board, otherwise an IllegalArgumentException will be thrown.
   *
   * @param armLength length of arm
   * @param emptyRow row of empty piece
   * @param emptyCol col of empty piece
   */
  public MarbleSolitaireModelImpl(int armLength, int emptyRow, int emptyCol) {
    initBoard(armLength, emptyRow, emptyCol);
  }

  private boolean isOutOfBounds(int r, int c, int arm) {
    return (r < (boardSize - arm) / 2 && c < (boardSize - arm) / 2)
        || (r < (boardSize - arm) / 2 && c >= (boardSize + arm) / 2)
        || (r >= (boardSize + arm) / 2 && c < (boardSize - arm) / 2)
        || (r >= (boardSize + arm) / 2 && c >= (boardSize + arm) / 2)
        || r < 0 || r > boardSize || c < 0 || c > boardSize;
  }

  private void initBoard(int armLength, int emptyRow, int emptyCol)
      throws IllegalArgumentException {
    this.boardSize = armLength * 3 - 2;

    if (armLength % 2 != 1 || armLength < 3) {
      throw new IllegalArgumentException("Arm length invalid");
    }
    if (isOutOfBounds(emptyRow, emptyCol, armLength)) {
      throw new IllegalArgumentException("Invalid empty point");
    }

    board = new String[boardSize][boardSize];
    for (int r = 0; r < boardSize; r++) {
      for (int c = 0; c < boardSize; c++) {
        if (isOutOfBounds(r, c, armLength)) {
          board[r][c] = Piece.Space.toString();
        } else {
          board[r][c] = Piece.O.toString();
        }
      }
    }
    board[emptyRow][emptyCol] = Piece.UnderScore.toString();
    this.armLength = armLength;
    this.isOver = false;
  }

  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (isOutOfBounds(fromRow, fromCol, this.armLength)
        || isOutOfBounds(toRow, toCol, this.armLength)) {
      return false;
    }

    if (board[toRow][toCol] != Piece.UnderScore.toString()) {
      return false;
    }

    int dr = Math.abs(fromRow - toRow);
    int dc = Math.abs(fromCol - toCol);

    // check valid jump
    if (!((dr == 2 && dc == 0) || (dr == 0 && dc == 2))) {
      return false;
    }

    int mr = (fromRow + toRow) / 2;
    int mc = (fromCol + toCol) / 2;

    // check middle is O
    if (board[mr][mc] != Piece.O.toString()) {
      return false;
    }
    return true;
  }

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override public void move(int fromRow, int fromCol, int toRow, int toCol)
      throws IllegalArgumentException {
    if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("not valid move");
    }

    int mr = (fromRow + toRow) / 2;
    int mc = (fromCol + toCol) / 2;

    // move
    board[toRow][toCol] = Piece.O.toString();
    board[mr][mc] = Piece.UnderScore.toString();
    board[fromRow][fromCol] = Piece.UnderScore.toString();
    checkEnd();
  }

  private void checkEnd() {
    for (int r = 0; r < boardSize; r++) {
      for (int c = 0; c < boardSize; c++) {
        if (board[r][c] == Piece.O.toString()) {
          if (!isOutOfBounds(r - 2, c, armLength)) {
            if (isValidMove(r, c, r - 2, c)) {
              return;
            }
          }
          if (!isOutOfBounds(r + 2, c, armLength)) {
            if (isValidMove(r, c, r + 2, c)) {
              return;
            }
          }
          if (!isOutOfBounds(r, c - 2, armLength)) {
            if (isValidMove(r, c, r, c - 2)) {
              return;
            }
          }
          if (!isOutOfBounds(r, c + 2, armLength)) {
            if (isValidMove(r, c, r, c + 2)) {
              return;
            }
          }
        }
      }
    }
    this.isOver = true;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override public boolean isGameOver() {
    return this.isOver;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, X or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override public String getGameState() {
    List<String> rows = new ArrayList<>();

    for (String[] row : board) {
      List<String> rowStrings = new ArrayList<>();
      for (String s : row) {
        if (s == Piece.Space.toString()) {
          rowStrings.add(Piece.Space.toString());
        } else if (s == Piece.UnderScore.toString()) {
          rowStrings.add(Piece.UnderScore.toString());
        } else if (s == Piece.O.toString()) {
          rowStrings.add(Piece.O.toString());
        }
      }
      rows.add(String.join(" ", rowStrings));
    }
    return String.join("\n", rows);
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override public int getScore() {
    int cnt = 0;
    for (int r = 0; r < this.boardSize; r++) {
      for (int c = 0; c < this.boardSize; c++) {
        if (board[r][c] == Piece.O.toString()) {
          cnt++;
        }
      }
    }
    return cnt;
  }
}
