package chess;

/**
 * A pawn can move only “ahead,” not backwards towards where its color started. It can move only one
 * place forward in its own column (except the first time it moves: it can move one or two places
 * forward). However, to kill it must move one place forward diagonally (it cannot kill by moving
 * straight).
 */
public class Pawn extends ChessPieceImpl {
  /**
   * Constructs the Pawn chess Piece.
   *
   * @param row   the row where the Pawn is located.
   * @param col   the column where the Pawn is located.
   * @param color the color of the Pawn.
   * @throws IllegalArgumentException when row or col are outside the range 0-7 and when a WHITE
   *                                  Pawn's row is smaller than 1 and when a BLACK Pawn's row is
   *                                  greater than 6.
   */
  public Pawn(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
    if ((color == Color.WHITE && row < 1) || (color == Color.BLACK && row > 6)) {
      throw new IllegalArgumentException("This type of Pawn cannot be placed at this location");
    }
  }

  @Override
  public boolean canMove(int row, int col) {
    if (isInsideTheBoard(row, col)) {
      if (this.getColumn() == col) {
        if ((this.getColor() == Color.WHITE) & (this.getRow()) == 1) {
          return ((row - this.getRow() == 1) | (row - this.getRow() == 2));
        } else if ((this.getColor() == Color.BLACK) & (this.getRow()) == 6) {
          return ((this.getRow() - row == 1) | (this.getRow() - row == 2));
        } else if (this.getColor() == Color.WHITE) {
          return (row - this.getRow() == 1);
        } else {
          return (this.getRow() - row == 1);
        }
      }
    }
    return false;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (isInsideTheBoard(piece.getRow(), piece.getColumn()) & (this.getColor()
        != piece.getColor())) {
      if (this.getColor() == Color.WHITE) {
        return ((piece.getRow() - this.getRow() == 1) & (
            Math.abs(piece.getColumn() - this.getColumn()) == 1));
      } else {
        return ((this.getRow() - piece.getRow() == 1) & (
            Math.abs(this.getColumn() - piece.getColumn()) == 1));
      }
    }
    return false;
  }
}
