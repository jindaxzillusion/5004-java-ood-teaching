package tictactoe;

/**
 * Class implementation of tic-tac-toe contoller.
 */
public class SwingTicTacToeController implements TicTacToeController {
  private final TicTacToe model;
  private TicTacToeView view;

  /**
   * Constructor for swing tic-tac-toe controller.
   *
   * @param view view of tictactoe.
   * @param model model of tictactoe.
   */
  public SwingTicTacToeController(TicTacToeView view, TicTacToe model)
      throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("view or model is null");
    }
    this.model = model;
    this.view = view;
    view.setEventListener(this); // connect controller to view
  }

  @Override public void playGame() {
    view.setTurnTextView("Turn: " + this.model.getTurn().toString());
  }

  @Override public void onCellClick(int r, int c) {
    view.setPlayerMoveTextView(model.getTurn().toString(), r, c);
    model.move(r, c);

    if (model.isGameOver()) {
      if (model.getWinner() == null) {
        view.setTurnTextView("Draw!");
      } else if (model.getWinner() == Player.X) {
        view.setTurnTextView("X wins!");
      } else if (model.getWinner() == Player.O) {
        view.setTurnTextView("O wins!");
      }
      view.setAllButtonDisabled();
    } else {
      view.setTurnTextView("Turn: " + this.model.getTurn().toString());
    }
  }
}
