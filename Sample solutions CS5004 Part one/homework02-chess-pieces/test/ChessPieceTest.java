import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import chess.Bishop;
import chess.ChessPiece;
import chess.Color;
import chess.King;
import chess.Knight;
import chess.Pawn;
import chess.Queen;
import chess.Rook;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for all the Chess pieces.
 */
public class ChessPieceTest {
  private ChessPiece whitePawn1;
  private ChessPiece blackPawn1;
  private ChessPiece whitePawn2;
  private ChessPiece blackPawn2;
  private ChessPiece whiteBishop1;
  private ChessPiece blackBishop1;
  private ChessPiece whiteKnight1;
  private ChessPiece blackKnight1;
  private ChessPiece whiteRook1;
  private ChessPiece blackRook1;
  private ChessPiece whiteQueen;
  private ChessPiece blackQueen;
  private ChessPiece whiteKing;
  private ChessPiece blackKing;

  /**
   * Set up a bunch of chess pieces.
   */
  @Before public void setUp() {
    this.whitePawn1 = new Pawn(1, 4, Color.WHITE);
    this.blackPawn1 = new Pawn(6, 5, Color.BLACK);
    this.whitePawn2 = new Pawn(2, 3, Color.WHITE);
    this.blackPawn2 = new Pawn(4, 5, Color.BLACK);
    this.whiteBishop1 = new Bishop(0, 2, Color.WHITE);
    this.blackBishop1 = new Bishop(7, 5, Color.BLACK);
    this.whiteKnight1 = new Knight(5, 2, Color.WHITE);
    this.blackKnight1 = new Knight(4, 6, Color.BLACK);
    this.whiteRook1 = new Rook(0, 0, Color.WHITE);
    this.blackRook1 = new Rook(7, 0, Color.BLACK);
    this.whiteQueen = new Queen(0, 3, Color.WHITE);
    this.blackQueen = new Queen(7, 3, Color.BLACK);
    this.whiteKing = new King(0, 4, Color.WHITE);
    this.blackKing = new King(7, 4, Color.BLACK);
  }

  /**
   * Test the getRow method.
   */
  @Test public void getRow() {
    assertEquals(1, whitePawn1.getRow());
    assertEquals(6, blackPawn1.getRow());
    assertEquals(0, whiteBishop1.getRow());
    assertEquals(7, blackBishop1.getRow());
  }

  /**
   * Test the getColumn method.
   */
  @Test public void getColumn() {
    assertEquals(4, whitePawn1.getColumn());
    assertEquals(5, blackPawn1.getColumn());
    assertEquals(2, whiteBishop1.getColumn());
    assertEquals(5, blackBishop1.getColumn());
  }

  /**
   * Test constructor does not accept columns greater than 7.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException1() {
    new King(0, 8, Color.BLACK);
  }

  /**
   * Test constructor does not accept columns smaller than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2() {
    new Queen(0, -1, Color.BLACK);
  }

  /**
   * Test constructor does not accept rows greater than 7.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3() {
    new King(8, 5, Color.WHITE);
  }

  /**
   * Test constructor does not accept rows smaller than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException4() {
    new Bishop(-1, 4, Color.WHITE);
  }

  /**
   * Test Pawn constructor does not accept rows smaller than 1 for White Pawns.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPawnConstructorException1() {
    new Pawn(0, 7, Color.WHITE);
  }

  /**
   * Test Pawn constructor does not accept rows greater than 6 for Black Pawns.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPawnConstructorException2() {
    new Pawn(7, 7, Color.BLACK);
  }

  /**
   * Test Pawn constructor follows extended restrictions of ChessPieceImpl.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPawnConstructorException3() {
    new Pawn(1, 8, Color.WHITE);
  }

  /**
   * Test the color of the chess pieces.
   */
  @Test public void getColor() {
    assertEquals(Color.WHITE, whiteQueen.getColor());
    assertEquals(Color.BLACK, blackQueen.getColor());
    assertEquals(Color.WHITE, whiteRook1.getColor());
    assertEquals(Color.BLACK, blackRook1.getColor());
    assertEquals(Color.WHITE, whiteKnight1.getColor());
    assertEquals(Color.BLACK, blackKnight1.getColor());
    assertEquals(Color.WHITE, whiteKing.getColor());
    assertEquals(Color.BLACK, blackKing.getColor());
  }

  /**
   * Test rooks can move to correct locations.
   */
  @Test public void testRookCanMove() {
    assertTrue(this.whiteRook1.canMove(0, 5));
    assertTrue(this.whiteRook1.canMove(5, 0));
    assertFalse(this.whiteRook1.canMove(5, 5));

    assertTrue(this.blackRook1.canMove(7, 5));
    assertTrue(this.blackRook1.canMove(4, 0));
    assertFalse(this.blackRook1.canMove(3, 3));
  }

  /**
   * Test bishops can move to correct locations.
   */
  @Test public void testBishopCanMove() {
    assertTrue(this.whiteBishop1.canMove(2, 4));
    assertTrue(this.whiteBishop1.canMove(5, 7));
    assertTrue(this.blackBishop1.canMove(4, 2));

    assertFalse(this.whiteBishop1.canMove(7, 6));
    assertFalse(this.blackBishop1.canMove(2, 3));
    assertFalse(this.blackBishop1.canMove(6, 5));
  }

  /**
   * Test knights can move to correct locations.
   */
  @Test public void testKnightCanMove() {
    assertTrue(this.blackKnight1.canMove(2, 5));
    assertTrue(this.blackKnight1.canMove(6, 7));
    assertTrue(this.whiteKnight1.canMove(4, 4));

    assertFalse(this.blackKnight1.canMove(7, 6));
    assertFalse(this.whiteKnight1.canMove(2, 3));
    assertFalse(this.whiteKnight1.canMove(6, 5));
  }

  /**
   * Test Kings can move to correct locations.
   */
  @Test public void testKingCanMove() {
    assertTrue(this.blackKing.canMove(7, 5));
    assertTrue(this.blackKing.canMove(7, 3));
    assertTrue(this.blackKing.canMove(6, 5));
    assertTrue(this.whiteKing.canMove(0, 5));
    assertTrue(this.whiteKing.canMove(1, 4));

    assertFalse(this.blackKing.canMove(7, 6));
    assertFalse(this.blackKing.canMove(2, 3));
    assertFalse(this.blackKing.canMove(6, 0));
    assertFalse(this.whiteKing.canMove(0, 6));
    assertFalse(this.whiteKing.canMove(1, 1));
  }

  /**
   * Test queens can move to correct locations.
   */
  @Test public void testQueenCanMove() {
    assertTrue(this.whiteQueen.canMove(3, 6));
    assertTrue(this.whiteQueen.canMove(5, 3));
    assertTrue(this.whiteQueen.canMove(0, 7));
    assertTrue(this.blackQueen.canMove(0, 3));
    assertTrue(this.blackQueen.canMove(7, 4));
    assertTrue(this.blackQueen.canMove(4, 0));

    assertFalse(this.whiteQueen.canMove(3, 5));
    assertFalse(this.whiteQueen.canMove(4, 1));
    assertFalse(this.whiteQueen.canMove(1, 7));
    assertFalse(this.blackQueen.canMove(3, 0));
    assertFalse(this.blackQueen.canMove(7, 3));
    assertFalse(this.blackQueen.canMove(4, 1));

  }

  /**
   * Test pawns can move to correct locations.
   */
  @Test public void testPawnCanMove() {
    assertTrue(this.whitePawn1.canMove(2, 4));
    assertTrue(this.whitePawn1.canMove(3, 4));
    assertTrue(this.whitePawn2.canMove(3, 3));
    assertTrue(this.blackPawn1.canMove(5, 5));
    assertTrue(this.blackPawn1.canMove(4, 5));
    assertTrue(this.blackPawn2.canMove(3, 5));

    assertFalse(this.whitePawn1.canMove(1, 5));
    assertFalse(this.whitePawn1.canMove(4, 4));
    assertFalse(this.whitePawn2.canMove(3, 5));
    assertFalse(this.blackPawn1.canMove(5, 4));
    assertFalse(this.blackPawn1.canMove(3, 5));
    assertFalse(this.blackPawn2.canMove(2, 5));
  }

  /**
   * Test that staying in the same cell is not considered movement.
   */
  @Test public void testCanMoveIsSameCell() {
    assertFalse("This is the same location. There is no movement.",
        this.blackRook1.canMove(this.blackRook1.getRow(), this.blackRook1.getColumn()));
    assertFalse("This is the same location. There is no movement.",
        this.whiteBishop1.canMove(this.whiteBishop1.getRow(), this.whiteBishop1.getColumn()));
    assertFalse("This is the same location. There is no movement.",
        this.blackQueen.canMove(this.blackQueen.getRow(), this.blackQueen.getColumn()));
    assertFalse("This is the same location. There is no movement.",
        this.blackKnight1.canMove(this.blackKnight1.getRow(), this.blackKnight1.getColumn()));
    assertFalse("This is the same location. There is no movement.",
        this.whiteKing.canMove(this.whiteKing.getRow(), this.whiteKing.getColumn()));
    assertFalse("This is the same location. There is no movement.",
        this.whitePawn2.canMove(this.whitePawn2.getRow(), this.whitePawn2.getColumn()));
  }

  /**
   * Test that chess pieces can and can't kill (except pawns).
   */
  @Test public void testCanKill() {
    assertTrue(this.whiteRook1.canKill(this.blackRook1));
    assertTrue(this.blackQueen.canKill(this.whiteQueen));
    assertTrue(this.whiteKnight1.canKill(this.blackQueen));

    assertFalse(this.whiteRook1.canKill(this.whiteQueen));
    assertFalse(this.whiteRook1.canKill(this.whiteBishop1));
    assertFalse(this.blackQueen.canKill(this.blackKing));
  }

  /**
   * Test that pawns can and can't kill.
   */
  @Test public void testPawnCanKill() {
    ChessPiece blackBishop2 = new Bishop(2, 3, Color.BLACK);
    assertTrue(this.whitePawn1.canKill(blackBishop2));

    ChessPiece blackKnight2 = new Knight(2, 5, Color.BLACK);
    assertTrue(this.whitePawn1.canKill(blackKnight2));

    ChessPiece whiteRook2 = new Rook(3, 6, Color.WHITE);
    assertTrue(this.blackPawn2.canKill(whiteRook2));

    ChessPiece whitePawn3 = new Pawn(5, 4, Color.WHITE);
    assertFalse(this.blackPawn2.canKill(whitePawn3));

    assertFalse(this.whitePawn2.canKill(this.whiteQueen));
    assertFalse(this.blackPawn1.canKill(this.whiteBishop1));
    assertFalse(this.blackPawn2.canKill(this.blackKing));
  }
}