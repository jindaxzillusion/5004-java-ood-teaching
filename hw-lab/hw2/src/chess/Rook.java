package chess;

/**
 * This class represents a rook Chess piece with
 * a row, a column and a color.
 */
public class Rook extends AbstractChessPiece {

  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  @Override public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (!super.canMove(row, col)) {
      return false;
    }
    return getRow() == row || getColumn() == col;
  }

}
