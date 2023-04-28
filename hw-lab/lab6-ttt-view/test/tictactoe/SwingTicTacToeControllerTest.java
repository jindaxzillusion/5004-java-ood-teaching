package tictactoe;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the tic-tac-toe controller.
 */
public class SwingTicTacToeControllerTest {
  private SwingTicTacToeController controller;
  private SwingTicTacToeView view;
  private TicTacToeModel model;

  /**
   * Initiate model, view, controller, and add view, model
   * to controller.
   */
  @Before public void setUp() {
    model = new TicTacToeModel();
    view = new SwingTicTacToeView("Tic-Tac-Toe");
    controller = new SwingTicTacToeController(view, model);
  }

  /**
   * Testing play tic tac toe game.
   */
  @Test public void playGame() {
    controller.playGame();
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", model.toString());
    controller.onCellClick(1, 1);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  ", model.toString());
  }

  /**
   * Testing handleClick method.
   */
  @Test public void handleClick() {
    controller.onCellClick(0, 0); // X moves
    controller.onCellClick(1, 0);
    controller.onCellClick(0, 1); // X moves
    controller.onCellClick(2, 0);
    controller.onCellClick(0, 2); // X moves
    assertEquals(Player.X, model.getWinner());
  }
}