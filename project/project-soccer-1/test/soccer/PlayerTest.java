package soccer;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Player of soccer team.
 */
public class PlayerTest {
  private Player p1;
  private Player p2;

  /**
   * We create Person p1 for testing.
   */
  @Before public void setUp() {
    p1 = new Player("pop", "os",
        LocalDate.of(2017, 10, 27),
        Position.FORWARD, 3);
    p2 = new Player("linux", "mint",
        LocalDate.of(2020, 1, 1),
        Position.GOALIE, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAge() {
    Player p3 = new Player("linux", "ubuntu",
        LocalDate.of(1900, 10, 27),
        Position.DEFENDER, 3);
  }

  @Test public void getFirstName() {
    assertEquals("pop", p1.getFirstName());
    assertEquals("linux", p2.getFirstName());
  }

  @Test public void getLastName() {
    assertEquals("os", p1.getLastName());
    assertEquals("mint", p2.getLastName());
  }

  @Test public void getDateOfBirth() {
    assertEquals(LocalDate.of(2017, 10, 27),
        p1.getDateOfBirth());
    assertEquals(LocalDate.of(2020, 1, 1),
        p2.getDateOfBirth());
  }

  @Test public void getPreferredPosition() {
    assertEquals(Position.FORWARD, p1.getPreferredPosition());
    assertEquals(Position.GOALIE, p2.getPreferredPosition());
  }

  @Test public void getSkillLevel() {
    assertEquals(3, p1.getSkillLevel());
    assertEquals(2, p2.getSkillLevel());
  }

  @Test public void testSetGetJerseyNumber() {
    p1.setJerseyNumber(3);
    assertEquals(3, p1.getJerseyNumber());
  }

  @Test public void testSetGetPosition() {
    p1.setPosition(Position.MIDFIELDER);
    assertEquals(Position.MIDFIELDER, p1.getPosition());
  }
}