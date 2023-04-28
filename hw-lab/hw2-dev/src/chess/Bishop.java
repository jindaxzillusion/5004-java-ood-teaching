package chess;

/**
 * A bishop can only move diagonally, and kill any opponent’s piece if it can move to its place.
 */
public class Bishop extends ChessPieceImpl {
  /**
   * Constructs the Bishop chess Piece.
   *
   * @param row   the row where the Bishop is located.
   * @param col   the column where the Bishop is located.
   * @param color the color of the Bishop.
   * @throws IllegalArgumentException when row or col are outside the range 0-7.
   */
  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (isInsideTheBoard(row, col)) {
      return isDiagonalMovement(row, col);
    }
    return false;
  }
}
