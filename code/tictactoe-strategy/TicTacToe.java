import cs5004.tictactoe.controller.PlayerImpl;
import cs5004.tictactoe.controller.TTTController;
import cs5004.tictactoe.controller.TicTacToeController;
import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TTTModelImpl;
import cs5004.tictactoe.model.TicTacToeModel;
import cs5004.tictactoe.strategy.*;

/**
 * Driver for the TicTacToe game
 */
public class TicTacToe {
  public static void main(String[] args) {
    int h = 3;
    int w = 3;
    int g = 3;
    if (args.length == 3) {
      h = Integer.parseInt(args[0]);
      w = Integer.parseInt(args[1]);
      g = Integer.parseInt(args[2]);
    }
    TicTacToeModel model = new TTTModelImpl.Builder().setHeight(h).setWidth(w).setGoal(g).build();
    TicTacToeController control = new TTTController(model, System.out);
    control.addPlayer(new PlayerImpl(Piece.X, new RandomPlay()));
    control.addPlayer(new PlayerImpl(Piece.O, new BlockWin()));
    control.play();
  }
}
