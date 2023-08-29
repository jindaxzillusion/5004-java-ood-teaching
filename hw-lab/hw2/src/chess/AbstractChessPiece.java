package chess;

/**
 * This class represents an abstract class for chess piece.
 */
public abstract class AbstractChessPiece implements ChessPiece {

  protected int row;
  protected int col;
  protected Color color;

  /**
   * Construct a chess piece object that has the provided row, column, color.
   *
   * @param row the row to be given to chess piece
   * @param col the column to be given to chess piece
   * @param color the color to be assigned to this chess piece
   */
  public AbstractChessPiece(int row, int col, Color color) throws IllegalArgumentException {
    if (row < 0 || row > 7 || col < 0 || col > 7) {
      throw new IllegalArgumentException("piece coordinate is beyond boundary");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override public int getRow() {
    return this.row;
  }

  @Override public int getColumn() {
    return this.col;
  }

  @Override public Color getColor() {
    return this.color;
  }

  @Override public boolean canMove(int row, int col) throws IllegalArgumentException{
    if (!legalCoordinate(row, col)) {
      throw new IllegalArgumentException("piece coordinate is beyond boundary");
    }
    if (isSameCoordinate(row, col)) {
      return false;
    }
    return true;
  }

  @Override public boolean canKill(ChessPiece piece) {
    if (getColor() == piece.getColor()) {
      return false;
    }
    return canMove(piece.getRow(), piece.getColumn());
  }

  protected boolean legalCoordinate(int r, int c) {
    return r >= 0 && r <= 7 && c >= 0 && c <= 7;
  }

  protected boolean isSameCoordinate(int r, int c) {
    return r == getRow() && c == getColumn();
  }
}
