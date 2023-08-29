package chess;

public class King extends AbstractChessPiece{
  public King(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }
  /**
   * Determine if the chess piece can move to a given cell.
   *
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return True if the piece can move to that location. False otherwise.
   */
  @Override
  public boolean canMove(int row, int col) throws IllegalArgumentException{
    if (!super.canMove(row, col)) {
      return false;
    }
    if (Math.abs(row - getRow()) <= 1 && Math.abs(col - getColumn()) <= 1) {
      return true;
    }
    return false;
  }
}
