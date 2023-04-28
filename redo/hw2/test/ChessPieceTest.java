import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import chess.Bishop;
import chess.Color;
import chess.King;
import chess.Pawn;
import chess.Queen;
import chess.Rook;
import chess.Knight;
import org.junit.Before;
import org.junit.Test;


/**
 * These are the unit tests for the Chess Pieces.
 */
public class ChessPieceTest {
  private King k1;
  private Queen q1;
  private Bishop b1;
  private Pawn p1;
  private Rook r1;
  private Knight kn1;

  /**
   * We create test pieces for every chess piece.
   */
  @Before
  public void setUp() {
    k1 = new King(0, 0, Color.WHITE);
    q1 = new Queen(0, 0, Color.WHITE);
    b1 = new Bishop(0, 0, Color.WHITE);
    p1 = new Pawn(1, 0, Color.WHITE);
    r1 = new Rook(0, 0, Color.WHITE);
    kn1 = new Knight(0, 0, Color.WHITE);
  }

  @Test public void testGetRow() {
    assertEquals(k1.getRow(), 0);
    assertEquals(p1.getRow(), 1);
  }

  @Test public void testGetCol() {
    assertEquals(k1.getColumn(), 0);
    assertEquals(q1.getColumn(), 0);
  }

  @Test public void testGetColor() {
    assertEquals(k1.getColor(), Color.WHITE);
    assertEquals(kn1.getColor(), Color.WHITE);
  }

  @Test
  public void testKingMove() {
    assertTrue(k1.canMove(0, 1));
    assertTrue(k1.canMove(1, 1));
    assertTrue(k1.canMove(1, 0));
    assertFalse(k1.canMove(0, 0));
    assertFalse(k1.canMove(2, 0));
    assertFalse(k1.canMove(2, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionKingPosition() {
    new King(-1, -1, Color.BLACK);
  }

  @Test
  public void testKingKill() {
    Queen q2 = new Queen(1, 1, Color.BLACK);
    assertTrue(k1.canKill(q2));

    Queen q3 = new Queen(2, 2, Color.BLACK);
    assertFalse(k1.canKill(q3));

    Bishop b2 = new Bishop(1, 1, Color.BLACK);
    assertTrue(k1.canKill(b2));
  }

  @Test
  public void testQueenMove() {
    assertFalse(q1.canMove(0, 0));
    assertTrue(q1.canMove(1, 0));
    assertTrue(q1.canMove(0, 1));
    assertTrue(q1.canMove(1, 1));
    assertTrue(q1.canMove(3, 3));
  }

  @Test
  public void testQueenKill() {
    Bishop b2 = new Bishop(1, 1, Color.BLACK);
    Bishop b3 = new Bishop(1, 3, Color.BLACK);
    assertTrue(q1.canKill(b2));
    assertFalse(q1.canKill(b3));
  }

  @Test
  public void testBishopMove() {
    assertTrue(b1.canMove(2, 2));
    assertTrue(b1.canMove(3, 3));
    assertFalse(b1.canMove(1, 0));
  }

  @Test
  public void testBishopKill() {
    Bishop b2 = new Bishop(1, 1, Color.BLACK);
    King kn2 = new King(1, 3, Color.BLACK);
    assertTrue(b1.canKill(b2));
    assertFalse(b1.canKill(kn2));
  }

  @Test
  public void testRookMove() {
    assertTrue(r1.canMove(0, 1));
    assertTrue(r1.canMove(1, 0));
    assertFalse(r1.canMove(1, 1));
  }

  @Test
  public void testRookKill() {
    Bishop b2 = new Bishop(1, 1, Color.BLACK);
    assertFalse(r1.canKill(b2));

    Bishop b3 = new Bishop(1, 0, Color.BLACK);
    assertTrue(r1.canKill(b3));
  }

  @Test
  public void testPawnMove() {
    assertTrue(p1.canMove(2, 0));
    assertTrue(p1.canMove(3, 0));
    assertFalse(p1.canMove(2, 1));
    assertFalse(p1.canMove(0, 0));

    Pawn p2 = new Pawn(6, 0, Color.BLACK);
    assertTrue(p2.canMove(5, 0));
    assertTrue(p2.canMove(4, 0));
    assertFalse(p2.canMove(6, 1));
    assertFalse(p2.canMove(7, 0));

    Pawn p3 = new Pawn(3, 0, Color.WHITE);
    assertTrue(p3.canMove(4, 0));
    assertFalse(p3.canMove(2, 0));

    Pawn p4 = new Pawn(3, 0, Color.BLACK);
    assertTrue(p4.canMove(2, 0));
    assertFalse(p4.canMove(4, 0));

    Pawn p5 = new Pawn(4, 5, Color.BLACK);
    assertFalse(p5.canMove(2, 5));
  }

  @Test
  public void testPawnKill() {
    Bishop b2 = new Bishop(2, 1, Color.BLACK);
    assertTrue(p1.canKill(b2));
    Bishop b3 = new Bishop(1, 0, Color.BLACK);
    assertFalse(p1.canKill(b3));
  }

  @Test
  public void testKnightMove() {
    assertTrue(kn1.canMove(1, 2));
    assertTrue(kn1.canMove(2, 1));
    assertFalse(kn1.canMove(1, 1));
  }


  @Test
  public void testKnightKill() {
    Bishop b2 = new Bishop(1, 1, Color.BLACK);
    Pawn p2 = new Pawn(1, 2, Color.BLACK);
    assertFalse(kn1.canKill(b2));
    assertTrue(kn1.canKill(p2));
  }
}
