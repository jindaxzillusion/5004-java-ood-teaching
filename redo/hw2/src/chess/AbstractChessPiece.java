package chess;

public abstract class AbstractChessPiece implements ChessPiece{
  private int row;
  private int col;
  private Color color;
  public AbstractChessPiece(int row, int col, Color color) throws IllegalArgumentException{
    if (!legalCoordinate(row, col)) {
      throw new IllegalArgumentException("e");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  private boolean legalCoordinate(int row, int col) {
    if (row < 0 || row > 7 || col < 0 || col > 7) {
      return false;
    }
    return true;
  }

  private boolean sameCoordinate(int r, int c) {
    if (this.getRow() == r && this.getColumn() == c) {
      return true;
    }
    return false;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return col;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public boolean canMove(int r, int c) {
    if (sameCoordinate(r, c)) {
      return false;
    }
    if (!legalCoordinate(r, c)) {
      return false;
    }
    return true;
  }

  @Override public boolean canKill(ChessPiece piece) {
    if (!legalCoordinate(piece.getRow(), piece.getColumn())) {
      return false;
    }
    if (sameCoordinate(piece.getRow(), piece.getColumn())) {
      return false;
    }
    if (this.getColor() == piece.getColor()){
      return false;
    }
    return this.canMove(piece.getRow(), piece.getColumn());
  }
}
