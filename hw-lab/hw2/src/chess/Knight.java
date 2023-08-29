package chess;

/**
 * This class represents a knight Chess piece with
 * a row, a column and a color.
 */
public class Knight extends AbstractChessPiece {

  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (!super.canMove(row, col)) {
      return false;
    }
    return (Math.abs(getRow() - row) == 2 && Math.abs(getColumn() - col) == 1)
        || (Math.abs(getRow() - row) == 1 && Math.abs(getColumn() - col) == 2);
  }
}
