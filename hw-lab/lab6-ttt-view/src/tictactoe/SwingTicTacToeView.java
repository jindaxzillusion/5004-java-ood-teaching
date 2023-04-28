package tictactoe;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class implementation of tic-tac-toe view.
 */
public class SwingTicTacToeView extends JFrame implements TicTacToeView {
  private final JLabel turnTextView;
  private final JPanel gameBoardTextView;
  private final JButton[][] buttons;

  /**
   * Constructor for swing tic-tac-toe view.
   *
   * @param caption Caption of the view.
   */
  public SwingTicTacToeView(String caption) {
    super(caption);

    setSize(1900, 1900);
    this.setMinimumSize(new Dimension(600, 600));
    setLocation(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    turnTextView = new JLabel("");
    this.add(turnTextView); // add display label

    gameBoardTextView = new JPanel(new GridLayout(3, 3));
    buttons = new JButton[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        buttons[r][c] = new JButton();
        gameBoardTextView.add(buttons[r][c]);
      }
    }
    this.add(gameBoardTextView);

    pack();
    setVisible(true);
  }

  @Override public void setEventListener(TicTacToeController tttController) {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        int finalC = c;
        int finalR = r;
        buttons[r][c].addActionListener(evt -> tttController.onCellClick(finalR, finalC));
      }
    }
  }

  @Override public void setTurnTextView(String text) {
    turnTextView.setText(text);
  }

  @Override
  public void setPlayerMoveTextView(String player, int r, int c) {
    buttons[r][c].setText(player);
    buttons[r][c].setEnabled(false);
  }

  @Override public void setAllButtonDisabled() {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        buttons[r][c].setEnabled(false);
      }
    }
  }
}
