package chess;

public class Pawn extends AbstractChessPiece{
  public Pawn(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
    if (row < 1 && getColor() == Color.WHITE) {
      throw new IllegalArgumentException("e");
    }
    if (row > 6 && getColor() == Color.BLACK) {
      throw new IllegalArgumentException("e");
    }
  }
  /**
   * Determine if the chess piece can move to a given cell.
   *
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

    if (getColor() == Color.WHITE) {
      if (getRow() == 1) {
        if (getColumn() == col && row - getRow() == 2) {
          return true;
        } else if (getColumn() == col && row - getRow() == 1) {
          return true;
        } else {
          return false;
        }
      } else if (getRow() != 1) {
        if (getColumn() == col && row - getRow() == 1) {
          return true;
        } else {
          return false;
        }
      }
    }

    if (getColor() == Color.BLACK) {
      if (getRow() == 6) {
        if (getColumn() == col && getRow() - row == 2) {
          return true;
        } else if (getColumn() == col && getRow() - row == 1) {
          return true;
        } else {
          return false;
        }
      } else if (getRow() != 6) {
        if (getColumn() == col && getRow() - row == 1) {
          return true;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (getColor() == piece.getColor()) {
      return false;
    }
    if (!super.canMove(piece.getRow(), piece.getColumn())) {
      return false;
    }
    if (getColor() == Color.WHITE) {
      if (Math.abs(getColumn() - piece.getColumn()) == 1 && piece.getRow() - getRow() == 1) {
        return true;
      }
    } else {
      if (Math.abs(getColumn() - piece.getColumn()) == 1 && getRow() - piece.getRow() == 1) {
        return true;
      }
    }
    return false;
  }
}
