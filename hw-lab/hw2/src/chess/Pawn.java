package chess;

/**
 * This class represents a pawn Chess piece with
 * a row, a column and a color.
 */
public class Pawn extends AbstractChessPiece {

  /**
   * Construct a pawn piece object that has the provided row, column, color,
   * throws error if location is not valid.
   *
   * @param row the row to be given to chess piece
   * @param col the column to be given to chess piece
   * @param color the color to be assigned to this chess piece
   */
  public Pawn(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
    if (color == Color.WHITE && row < 1) {
      throw new IllegalArgumentException("White pawn row cannot be smaller than 1");
    }
    if (color == Color.BLACK && row > 6) {
      throw new IllegalArgumentException("Black pawn row cannot be greater than 6");
    }
  }

  @Override public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (!super.canMove(row, col)) {
      return false;
    }

    if (getColor() == Color.WHITE) {
      if (getRow() == 1) {
        if (row - getRow() == 1 && getColumn() == col) {
          return true;
        } else if (row - getRow() == 2 && getColumn() == col) {
          return true;
        }
      } else {
        if (row - getRow() == 1 && getColumn() == col) {
          return true;
        }
      }
    } else if (getColor() == Color.BLACK) {
      if (getRow() == 6) {
        if (getRow() - row == 1 && getColumn() == col) {
          return true;
        } else if (getRow() - row == 2 && getColumn() == col) {
          return true;
        }
      } else {
        if (getRow() - row == 1 && getColumn() == col) {
          return true;
        }
      }
    }
    return false;
  }


  @Override public boolean canKill(ChessPiece piece) {
    if (getColor() == piece.getColor()) {
      return false;
    } else if (getColor() == Color.WHITE) {
      if (piece.getRow() - getRow() == 1 && Math.abs(piece.getColumn() - getColumn()) == 1) {
        return true;
      }
    } else {
      if (getRow() - piece.getRow() == 1 && Math.abs(piece.getColumn() - getColumn()) == 1) {
        return true;
      }
    }
    return false;
  }
}



