package soccer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class implementation of team view.
 */
public class SwingTeamView extends JFrame implements TeamView {
  private JLabel messageTextView;
  private final JTextField yearInput;
  private final JTextField monthInput;
  private final JTextField dayInput;
  private JTextField skillInput;
  private JTextField positionInput;
  private JTextField firstNameInput;
  private JTextField lastNameInput;
  private JButton addPlayerBtn;
  private JButton createTeamBtn;
  private JButton getAddPlayerBtn;
  private JButton getStartingLineUpBtn;
  private JTextArea resultTextView;

  /**
   * Constructor for swing team view.
   *
   * @param caption Caption of the view.
   */
  public SwingTeamView(String caption) {
    super(caption);

    setSize(1000, 1000);
    this.setMinimumSize(new Dimension(600, 600));
    setLocation(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());

    messageTextView = new JLabel("");
    this.add(messageTextView);

    JLabel label1 = new JLabel("Enter player first name");
    this.add(label1);
    firstNameInput = new JTextField(10);
    this.add(firstNameInput);

    JLabel label2 = new JLabel("Enter player last name");
    this.add(label2);
    lastNameInput = new JTextField(10);
    this.add(lastNameInput);

    JLabel label3 = new JLabel("Enter player DOB year");
    this.add(label3);
    yearInput = new JTextField(10);
    this.add(yearInput);

    JLabel label4 = new JLabel("Enter player DOB month");
    this.add(label4);
    monthInput = new JTextField(10);
    this.add(monthInput);

    JLabel label5 = new JLabel("Enter player DOB day");
    this.add(label5);
    dayInput = new JTextField(10);
    this.add(dayInput);


    JLabel label6 = new JLabel("Enter player skill level (1-5)");
    this.add(label6);
    skillInput = new JTextField(10);
    this.add(skillInput);

    JLabel label7 = new JLabel("Enter player preferred position (1-4) :"
        + "1 - GOALIE, "
        + "2 - DEFENDER, "
        + "3 - MIDFIELDER, "
        + "4 - FORWARD");
    this.add(label7);
    positionInput = new JTextField(10);
    this.add(positionInput);

    addPlayerBtn = new JButton("Add Player");
    this.add(addPlayerBtn);

    createTeamBtn = new JButton("Create Team");
    this.add(createTeamBtn);

    getAddPlayerBtn = new JButton("Get All Player");
    this.add(getAddPlayerBtn);

    getStartingLineUpBtn = new JButton("Get Starting Line Up");
    this.add(getStartingLineUpBtn);

    resultTextView = new JTextArea("");
    this.add(resultTextView);

    pack();
    setVisible(true);
  }

  @Override public void setEventListener(TeamController controller) {
    addPlayerBtn.addActionListener(e -> controller.onAddPlayerBtnClick());
    createTeamBtn.addActionListener(e -> controller.onCreateTeamBtnClick());
    getAddPlayerBtn.addActionListener(e -> controller.onGetAllPlayerBtnClick());
    getStartingLineUpBtn.addActionListener(e -> controller.onGetStartingLineUpBtnClick());
  }

  @Override public void setMessageTextView(String text) {
    messageTextView.setText(text);
  }

  @Override public void setResultTextView(String text) {
    resultTextView.setText(text);
  }

  @Override public void setAllInputClear() {
    firstNameInput.setText("");
    lastNameInput.setText("");
    yearInput.setText("");
    monthInput.setText("");
    dayInput.setText("");
    skillInput.setText("");
    positionInput.setText("");
  }

  @Override public String getFirstNameInput() {
    return firstNameInput.getText();
  }

  @Override public String getLastNameInput() {
    return lastNameInput.getText();
  }

  @Override public LocalDate getDateOfBirthInput() {
    int year = Integer.parseInt(yearInput.getText());
    int month = Integer.parseInt(monthInput.getText());
    int day = Integer.parseInt(dayInput.getText());

    LocalDate dob = LocalDate.of(year, month, day);
    LocalDate curDate = LocalDate.now();
    long age = ChronoUnit.YEARS.between(dob, curDate);
    if (age >= 10) {
      messageTextView.setText("Add player failed: player greater than 10 years old");
      return null;
    }
    return dob;
  }

  @Override public int getSkillLevelInput() {
    int newSkillLevel = Integer.parseInt(skillInput.getText());
    return newSkillLevel;
  }

  @Override public Position getPositionInput() {
    int input = Integer.parseInt(skillInput.getText());
    if (input == 1) {
      return Position.GOALIE;
    } else if (input == 2) {
      return Position.DEFENDER;
    } else if (input == 3) {
      return Position.MIDFIELDER;
    } else if (input == 4) {
      return Position.FORWARD;
    } else {
      messageTextView.setText("Invalid input for position. "
          + "Please enter a number between 1 and 4.");
      return null;
    }
  }
}
