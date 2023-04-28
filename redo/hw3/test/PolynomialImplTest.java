import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import polynomial.Polynomial;
import polynomial.PolynomialImpl;

/**
 * These are the unit tests for the Polynomials.
 */
public class PolynomialImplTest {
  private PolynomialImpl p1;
  private PolynomialImpl p2;
  private PolynomialImpl p3;
  private PolynomialImpl p4;
  private PolynomialImpl p5;
  private PolynomialImpl p6;
  private PolynomialImpl p7;

  /**
   * Set up Polynomials to use for our tests.
   */
  @Before
  public void setUp() {
    p1 = new PolynomialImpl("4x^3 +3x^1 -5");
    p2 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    p3 = new PolynomialImpl();
    p4 = new PolynomialImpl("-3x^4 +3x^4 -6");
    p5 = new PolynomialImpl("3");
    p6 = new PolynomialImpl("");
    p7 = new PolynomialImpl("2x^2");
  }

  @org.junit.Test
  public void highestTermBecomesZero() {
    Polynomial p1 = new PolynomialImpl("-10x^98 +8x^96 -10x^93 -13x^90 -16x^89 +8x^87");
    Polynomial p2 = new PolynomialImpl("-5x^99 -10x^98 +8x^96 -10x^93 -13x^90 -16x^89 +8x^87");
    assertFalse(p1.isSame(p2));
    p2.addTerm(5, 99);
    assertTrue(p1.isSame(p2));
  }

  @org.junit.Test public void testNoArgument() {
    assertEquals("0", p6.toString());
  }

  @org.junit.Test public void testSamePower() {
    assertEquals("-6", p4.toString());
  }

  @org.junit.Test public void testConstant() {
    assertEquals("3", p5.toString());
  }

  @org.junit.Test public void testEmptyString() {
    assertEquals("0", p3.toString());
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testInvalidString() {
    Polynomial p8 = new PolynomialImpl("2x^-2");
  }

  @org.junit.Test public void testAddSamePower() {
    Polynomial p3 = p1.add(p2);
    assertEquals("-2x^5 -3x^4 +4x^3 +14x^1 -10", p3.toString());
  }

  @org.junit.Test public void testAddDifferentPower() {
    Polynomial p4 = p1.add(p7);
    assertEquals("4x^3 +2x^2 +3x^1 -5", p4.toString());
  }

  @org.junit.Test public void addTerm() {
    p1.addTerm(2, 3);
    assertEquals("6x^3 +3x^1 -5", p1.toString());
  }

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void addNegativeTerm() {
    p1.addTerm(2, -3);
  }

  @org.junit.Test public void isSame() {
    assertFalse(p1.isSame(p2));
    PolynomialImpl p3 = new PolynomialImpl("4x^3 +3x^1 -5");
    assertTrue(p1.isSame(p3));
  }

  @org.junit.Test public void testPositiveEvaluate() {
    assertEquals(2, p1.evaluate(1), 0.1);
  }

  @org.junit.Test public void testNegativeEvaluate() {
    assertEquals(2, p7.evaluate(-1), 0.1);
  }

  @org.junit.Test public void getCoefficient() {
    assertEquals(3, p1.getCoefficient(1));
    assertEquals(11, p2.getCoefficient(1));
  }

  @org.junit.Test public void getInvalidCoefficient() {
    assertEquals(0, p1.getCoefficient(100));
  }

  @org.junit.Test public void getDegree() {
    assertEquals(3, p1.getDegree());
    assertEquals(5, p2.getDegree());
  }

  @org.junit.Test public void getConstantDegree() {
    assertEquals(0, p5.getDegree());
  }

  @org.junit.Test public void testToString() {
    assertEquals("4x^3 +3x^1 -5", p1.toString());
    assertEquals("-2x^5 -3x^4 +11x^1 -5", p2.toString());
  }
}