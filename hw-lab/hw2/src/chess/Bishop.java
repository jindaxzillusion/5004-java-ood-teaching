package chess;

/**
 * This class represents a bishop chess piece with
 * a row, a column and a color.
 */
public class Bishop extends AbstractChessPiece {

  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (!super.canMove(row, col)) {
      return false;
    }
    return Math.abs(row - getRow()) == Math.abs(col - getColumn());
  }

}
