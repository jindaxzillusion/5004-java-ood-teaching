package chess;

/**
 * A knight can move only in an L pattern: two cells horizontally and one vertically or vice versa.
 * It can kill any opponent’s piece if it can move to its place.
 */
public class Knight extends ChessPieceImpl {
  /**
   * Constructs the Knight chess Piece.
   *
   * @param row   the row where the Knight is located.
   * @param col   the column where the Knight is located.
   * @param color the color of the Knight.
   * @throws IllegalArgumentException when row or col are outside the range 0-7.
   */
  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (isInsideTheBoard(row, col)) {
      return Math.abs((this.getRow() - row) * (this.getColumn() - col)) == 2;
    }
    return false;
  }
}
