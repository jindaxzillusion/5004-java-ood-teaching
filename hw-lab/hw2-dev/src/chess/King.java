package chess;

/**
 * A king can move one square in any direction (horizontally, vertically, or diagonally). It can
 * kill any opponent’s piece if it can move to its place
 */
public class King extends ChessPieceImpl {
  /**
   * Constructs the King chess Piece.
   *
   * @param row   the row where the King is located.
   * @param col   the column where the King is located.
   * @param color the color of the King.
   * @throws IllegalArgumentException when row or col are outside the range 0-7.
   */
  public King(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (isInsideTheBoard(row, col)) {
      return ((Math.abs(this.getRow() - row) <= 1) & (Math.abs(this.getColumn() - col) <= 1)) & (
          Math.abs(this.getRow() - row) + Math.abs(this.getColumn() - col) != 0);
    }
    return false;
  }
}
