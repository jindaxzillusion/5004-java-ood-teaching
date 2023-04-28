package solitaire;

/**
 * Piece enum. Options: O, UnderScore, Space.
 */
public enum Piece {
  O("O"),
  UnderScore("_"),
  Space(" ");

  private final String display;

  /**
   * This will display Piece.
   * @param display the String that will be displayed.
   */
  Piece(String display) {
    this.display = display;
  }

  @Override
  public String toString() {
    return this.display;
  }
}