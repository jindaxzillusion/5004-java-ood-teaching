package solitaire;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for class MarbleSolitaireModelImpl.
 */
public class MarbleSolitaireModelImplTest {
  private MarbleSolitaireModel m1 = new MarbleSolitaireModelImpl();
  private MarbleSolitaireModel m2 = new MarbleSolitaireModelImpl(3, 2);
  private MarbleSolitaireModel m3 = new MarbleSolitaireModelImpl(5);
  private MarbleSolitaireModel m4 = new MarbleSolitaireModelImpl(5, 5, 6);

  @org.junit.Test public void testConstructorsArm3() {
    assertEquals(
        "    O O O    \n"
            + "    O O O    \n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ", m1.getGameState());
    assertEquals(
        "    O O O    \n"
            + "    O O O    \n"
            + "O O O O O O O\n"
            + "O O _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ", m2.getGameState());
  }

  @org.junit.Test public void testConstructorsArm5() {
    m3.move(4, 6, 6, 6);
    assertEquals(false, m3.isGameOver());
    m4.move(3, 6, 5, 6);
    assertEquals(false, m4.isGameOver());
  }


  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeArmLength() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(-20);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidEvenArmLength() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(20);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyPoint() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl(0, 0);
  }

  @org.junit.Test public void testMultipleMove() {
    m1.move(5, 3, 3, 3);
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O    \n"
        + "    O O O    ", m1.getGameState());
    m1.move(2, 3, 4, 3);
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O _ O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O _ O    \n"
        + "    O O O    ", m1.getGameState());
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    m1.move(5, 3, 2, 2);
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOutOfBound() {
    m1.move(5, 3, -1, -1);
  }

  @org.junit.Test public void testGameState() {
    assertEquals(
        "    O O O    \n"
            + "    O O O    \n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O    \n"
            + "    O O O    ", m1.getGameState());
    m1.move(5, 3, 3, 3);
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O O O O O\n"
        + "O O O O O O O\n"
        + "O O O _ O O O\n"
        + "    O _ O    \n"
        + "    O O O    ", m1.getGameState());
    m1.move(2, 3, 4, 3);
    assertEquals("    O O O    \n"
        + "    O O O    \n"
        + "O O O _ O O O\n"
        + "O O O _ O O O\n"
        + "O O O O O O O\n"
        + "    O _ O    \n"
        + "    O O O    ", m1.getGameState());
  }



  @org.junit.Test public void isGameOver() {
    assertEquals(false, m1.isGameOver());
    m1.move(5, 3, 3, 3);
    assertEquals(false, m1.isGameOver());
  }


  @org.junit.Test public void getScore() {
    assertEquals(32, m1.getScore());
    m1.move(5, 3, 3, 3);
    assertEquals(31, m1.getScore());
  }
}