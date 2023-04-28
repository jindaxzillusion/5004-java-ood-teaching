package commands;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * GUI Implementation of the View interface.
 */
public class FrameView extends JFrame implements View {
  // display echo string label
  private final JLabel display;
  // three buttons, alphabetically
  private final JButton getAnswerButton;
  private final JButton exitButton;
  private final JTextField input;


  /**
   * Initialize the window.
   *
   * @param caption Caption for the window.
   */
  public FrameView(String caption) {
    // get the caption from super
    super(caption);

    // init size, location
    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // flow layout
    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");

    this.add(display);

    // The text field
    input = new JTextField(10);
    this.add(input);

    // Echo button.
    getAnswerButton = new JButton("Shake Ball");
    getAnswerButton.setActionCommand("Shake Ball Button");
    this.add(getAnswerButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);

    pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    // lambda for each feature, from interface use function
    getAnswerButton.addActionListener(evt -> features.setAnswer());
    exitButton.addActionListener(evt -> features.exitProgram());

  }

  /*
    In order to make this frame respond to keyboard events, it must be within strong focus.
    Since there could be multiple components on the screen that listen to keyboard events,
    we must set one as the "currently focussed" one so that all keyboard events are
    passed to that component. This component is said to have "strong focus".

    We do this by first making the component focusable and then requesting focus to it.
    Requesting focus makes the component have focus AND removes focus from whoever had it
    before.
  */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setAnswer(String text) {
    display.setText(text);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }
}
