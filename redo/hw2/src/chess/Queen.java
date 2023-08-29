package chess;

public class Queen extends AbstractChessPiece{
  public Queen(int row, int col, Color color) throws IllegalArgumentException {
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
    if (row == getRow() || col == getColumn() || row - getRow() == col - getColumn()) {
      return true;
    }
    return false;
  }
}
