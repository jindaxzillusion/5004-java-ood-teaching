package chess;

/**
 * Implements interface {@link ChessPiece}.
 */
public abstract class ChessPieceImpl implements ChessPiece {
  private final int row;
  private final int col;
  private final Color color;

  /**
   * Constructs a chess Piece.
   *
   * @param row   the row where the piece is located.
   * @param col   the column where the piece is located.
   * @param color the color of the piece.
   * @throws IllegalArgumentException when row or col are outside the range 0-7.
   */
  protected ChessPieceImpl(int row, int col, Color color) throws IllegalArgumentException {
    if (!isInsideTheBoard(row, col)) {
      throw new IllegalArgumentException("This location does not exist on the board.");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public boolean canMove(int row, int col) {
    return false;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return canMove(piece.getRow(), piece.getColumn()) && (this.getColor() != piece.getColor());
  }

  /**
   * Checks if the given location for a cell is inside the Chess board.
   *
   * @param row the cell's row.
   * @param col the cell's column.
   * @return True if the cell is inside the board.
   */
  protected boolean isInsideTheBoard(int row, int col) {
    return (row >= 0) & (row <= 7) & (col >= 0) & (col <= 7);
  }

  /**
   * Check if the desired movement is diagonal.
   *
   * @param row the row of the desired cell.
   * @param col the column of the desired cell.
   * @return True if it is a diagonal movement.
   */
  protected boolean isDiagonalMovement(int row, int col) {
    return ((Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col)) & (this.getRow()
        != row) & (this.getColumn() != col));
  }

  /**
   * Check if the desired movement is horizontal or vertical.
   *
   * @param row the row of the desired cell.
   * @param col the column of the desired cell.
   * @return True if it is a horizontal or vertical movement.
   */
  protected boolean isHorizontalOrVerticalMovement(int row, int col) {
    return (this.getRow() == row) ^ (this.getColumn() == col);
  }

}
