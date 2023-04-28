package chess;

/**
 * This class represents a queen Chess piece with
 * a row, a column and a color.
 */
public class Queen extends AbstractChessPiece {

  public Queen(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (!super.canMove(row, col)) {
      return false;
    }
    return (row == getRow() || col == getColumn()
        || Math.abs(row - getRow()) == Math.abs(col - getColumn()));
  }

}
