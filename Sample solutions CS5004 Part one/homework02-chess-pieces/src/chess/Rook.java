package chess;

/**
 * A rook can move horizontally or vertically. It can kill any opponent’s piece if it can move to
 * its place.
 */
public class Rook extends ChessPieceImpl {
  /**
   * Constructs the Rook chess Piece.
   *
   * @param row   the row where the Rook is located.
   * @param col   the column where the Rook is located.
   * @param color the color of the Rook.
   * @throws IllegalArgumentException when row or col are outside the range 0-7.
   */
  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    if (isInsideTheBoard(row, col)) {
      return isHorizontalOrVerticalMovement(row, col);
    }
    return false;
  }
}
