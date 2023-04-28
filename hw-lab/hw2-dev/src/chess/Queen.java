package chess;

/**
 * A queen can move horizontally, vertically and diagonally. It can kill any opponent’s piece if it
 * can move to its place.
 */
public class Queen extends ChessPieceImpl {
  /**
   * Constructs the Queen chess Piece.
   *
   * @param row   the row where the Queen is located.
   * @param col   the column where the Queen is located.
   * @param color the color of the Queen.
   * @throws IllegalArgumentException when row or col are outside the range 0-7.
   */
  public Queen(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (isInsideTheBoard(row, col)) {
      return isDiagonalMovement(row, col) | isHorizontalOrVerticalMovement(row, col);
    }
    return false;
  }
}
