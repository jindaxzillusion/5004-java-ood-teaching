package cs5004.tictactoe.strategy;

import cs5004.tictactoe.model.Piece;
import cs5004.tictactoe.model.TicTacToeModel;

public class RandomPlay implements PositionStrategy {

    @Override
    public Position choosePosition(TicTacToeModel model, Piece player) {
        return new Position((int)(Math.random() * 3), (int)(Math.random() * 3));
    }
}
