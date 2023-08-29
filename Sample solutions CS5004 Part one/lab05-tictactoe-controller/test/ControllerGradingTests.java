import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.StringReader;
import java.util.Arrays;

import org.junit.Test;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

/**
 * Test cases for the Tic Tac Toe controller, using mocks for readable and
 * appendable.
 */
public class ControllerGradingTests {

  /**
   * Test a single valid move.
   */
  // @GradedTest(name = "Test a single valid move", max_score = 2)
  @Test(timeout = 3000)
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n" + "-----------\n" + "   |   |  \n" + "-----------\n"
        + "   |   |  \n" + "Enter a move for X:\n" + "   |   |  \n" + "-----------\n"
        + "   | X |  \n" + "-----------\n" + "   |   |  \n" + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n" + "   | X |  \n"
        + "-----------\n" + "   |   |  \n", gameLog.toString());
  }

  /**
   * Test a bogus input for the row instead of int value between 1 and 3
   * (inclusive).
   */
  // @GradedTest(name = "Test a bogus input for the row instead of int value between 1 and 3", max_score = 3)
  @Test(timeout = 3000)
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 13,
        lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n"
        + "   |   |  \n" + "-----------\n" + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test a bogus input for the column instead of int value between 1 and 3
   * (inclusive).
   */
  // @GradedTest(name = "Test a bogus input for the column instead of int value between 1 and 3", max_score = 3)
  @Test(timeout = 3000)
  public void testBogusInputAsColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("3 8.79 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 13,
        lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n", Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n" + "   |   |  \n" + "-----------\n"
        + "   |   |  \n" + "-----------\n" + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test when game results in tie.
   */
  // @GradedTest(name = "Test when game results in tie", max_score = 3)
  @Test(timeout = 3000)
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one
    // string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 60,
        lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  /**
   * Test failing Appendable.
   */
  // @GradedTest(name = "Test failing Appendable", max_score = 2)
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  /**
   * Test playing the game and X is the winner. Five moves.
   */
  // @GradedTest(name = "Test playing the game and X is the winner. Five moves.", max_score = 3)
  @Test(timeout = 3000)
  public void testPlayGameWithWinnerX() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 2 1 1 2 1 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 36,
        lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test playing the game and O is the winner. Six moves.
   */
  // @GradedTest(name = "Test playing the game and O is the winner. Six moves.", max_score = 3)
  @Test(timeout = 3000)
  public void testPlayGameWithWinnerO() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 3 1 2 3 3 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 42,
        lines.length);
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  /**
   * Test what happens when q is used instead of an int value for the row.
   */
  // @GradedTest(name = "Test what happens when q is used instead of an int value for the row", max_score = 3)
  @Test(timeout = 3000)
  public void testQIsUsedInsteadOfRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 3 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 30,
        lines.length);
    assertEquals("Game quit! Ending game state:", lines[lines.length - 6]);
  }

  /**
   * Test what happens when q is used instead of an int value for the column.
   */
  // @GradedTest(name = "Test what happens when q is used instead of an int value for the column", max_score = 3)
  @Test(timeout = 3000)
  public void testQIsUsedInsteadOfColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 3 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 30,
        lines.length);
    assertEquals("Game quit! Ending game state:", lines[lines.length - 6]);
  }

  /**
   * Test input where the row is integer, but outside the bounds of the board.
   */
  // @GradedTest(name = "Test input where the row is integer, but outside the bounds of the board", max_score = 3)
  @Test(timeout = 3000)
  public void testRowIsIntButOutsideBoard() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 4 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 19,
        lines.length);
    assertEquals("Not a valid move: 4, 2", lines[lines.length - 7]);
  }

  /**
   * Test input where the column is integer, but outside the bounds of the board.
   */
  // @GradedTest(name = "Test input where the column is integer, but outside the bounds of the board", max_score = 3)
  @Test(timeout = 3000)
  public void testColumnIsIntButOutsideBoard() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 4 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 19,
        lines.length);
    assertEquals("Not a valid move: 2, 4", lines[lines.length - 7]);
  }

  /**
   * Test input where the move is integers, but invalid because the cell is
   * occupied.
   */
  // @GradedTest(name = "Test input where the move is integers, but invalid because the cell is occupied", max_score = 3)
  @Test(timeout = 3000)
  public void testValidInputButOccupiedCell() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 3 1 2 3 3 1 3 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 43,
        lines.length);
    assertEquals("Not a valid move: 1, 3", lines[lines.length - 7]);
  }

  /**
   * Test multiple invalid moves in a row of various kinds. Move 1: Allowed Move
   * 2: Allowed Move 3: Outside board Move 4: Non-int character Move 5: Occupied
   * cell Move 6: Outside board Move 7: Quit
   */
  // @GradedTest(name = "Test multiple invalid moves in a row of various kinds", max_score = 3)
  @Test(timeout = 3000)
  public void testMultipleInvalidMoves() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 3 9 2 u 2 2 6 2 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 28,
        lines.length);
    assertEquals("Not a valid move: 9, 2", lines[lines.length - 10]);
    assertEquals("Not a valid number: u", lines[lines.length - 9]);
    assertEquals("Not a valid move: 2, 2", lines[lines.length - 8]);
    assertEquals("Not a valid move: 6, 2", lines[lines.length - 7]);
  }

  /**
   * Test input including valid moves interspersed with invalid moves, game is
   * played to completion (ends in tie). Move 1: Allowed Move 2: Allowed Move 3:
   * Occupied cell Move 4: Allowed Move 5: Allowed Move 6: Outside board Move 7:
   * Allowed Move 8: Allowed Move 9: Non-int input Move 10: Allowed Move 11:
   * Outside board Move 12: Allowed Move 13: Allowed
   */
  // @GradedTest(name = "Test input including valid moves and invalid moves, game ends in tie", max_score = 3)
  @Test(timeout = 3000)
  public void testValidAndInvalidMovesGameEndsTie() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 2 2 1 1 1 2 1 3 5 2 3 1 2 1 fhgy 2 3 2 9 3 2 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 64,
        lines.length);
    assertEquals("Not a valid move: 1, 1", lines[lines.length - 46]);
    assertEquals("Not a valid move: 5, 2", lines[lines.length - 33]);
    assertEquals("Not a valid number: fhgy", lines[lines.length - 20]);
    assertEquals("Not a valid move: 2, 9", lines[lines.length - 13]);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  /**
   * Test input including valid moves interspersed with invalid moves, game is
   * played to completion (ends in win for X). Move 1: Non-int input Move 2:
   * Allowed Move 3: Occupied cell Move 4: Allowed Move 5: Allowed Move 6: Outside
   * board Move 7: Allowed Move 8: Allowed
   */
  // @GradedTest(name = "Test input including valid moves and invalid moves, ends in win for X", max_score = 3)
  @Test(timeout = 3000)
  public void testValidAndInvalidMovesGameXWins() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("7.5 1 1 1 1 2 2 2 1 1 7 1 2 3 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 39,
        lines.length);
    assertEquals("Not a valid number: 7.5", lines[6]);
    assertEquals("Not a valid move: 1, 1", lines[13]);
    assertEquals("Not a valid move: 1, 7", lines[26]);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test input including valid moves interspersed with invalid moves, game is
   * played to completion (ends in win for 0). Move 1: Outside board Move 2:
   * Allowed Move 3: Allowed Move 4: Allowed Move 5: Occupied cell Move 6: Non-int
   * input Move 7: Allowed Move 8: Allowed Move 9: Allowed
   */
  // @GradedTest(name = "Test input including valid moves and invalid moves, ends in win for O", max_score = 3)
  @Test(timeout = 3000)
  public void testValidAndInvalidMovesGameOWins() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("0 1 1 1 2 2 3 3 2 2 hello 2 3 1 3 2 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Handling invalid input incorrectly, incorrect number of lines in the output", 45,
        lines.length);
    assertEquals("Not a valid move: 0, 1", lines[6]);
    assertEquals("Not a valid move: 2, 2", lines[25]);
    assertEquals("Not a valid number: hello", lines[26]);
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  /** Testing invalid controller construction. */
  // @GradedTest(name = "Testing invalid controller construction", max_score = 2)
  @Test(timeout = 3000)
  public void testInvalidConstruction() {
    try {
      StringBuilder gameLog = new StringBuilder();
      new TicTacToeConsoleController(null, gameLog);
      fail("Controller construction should throw an "
          + "IllegalArgumentException for invalid input source");
    } catch (IllegalArgumentException ex) {
      // this is the expected behavior
    }
    try {
      StringReader input = new StringReader("0 1 1 1 2 2 3 3 2 2 hello 2 3 1 3 2 1");
      new TicTacToeConsoleController(input, null);
      fail("Controller construction should throw an "
          + "IllegalArgumentException for invalid output target");
    } catch (IllegalArgumentException ex) {
      // this is the expected behavior
    }
  }

  /** Testing invalid model when playGame is called. */
  // @GradedTest(name = "Testing invalid model when playGame is called", max_score = 2)
  @Test(timeout = 3000)
  public void testInvalidModel() {
    try {
      StringReader input = new StringReader("0 1 1 1 2 2 3 3 2 2 hello 2 3 1 3 2 1");
      StringBuilder gameLog = new StringBuilder();
      TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
      c.playGame(null);
      fail("Controller should throw an IllegalArgumentException for invalid model");
    } catch (IllegalArgumentException ex) {
      // this is the expected behavior
    }
  }
}
