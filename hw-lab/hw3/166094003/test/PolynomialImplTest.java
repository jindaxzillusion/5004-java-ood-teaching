import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import polynomial.Polynomial;
import polynomial.PolynomialImplSol;

/**
 * These are the unit tests for the Polynomials.
 */
public class PolynomialImplTest {
  private PolynomialImplSol p1;
  private PolynomialImplSol p2;
  private PolynomialImplSol p3;
  private PolynomialImplSol p4;
  private PolynomialImplSol p5;
  private PolynomialImplSol p6;
  private PolynomialImplSol p7;

  /**
   * Set up Polynomials to use for our tests.
   */
  @Before
  public void setUp() {
    p1 = new PolynomialImplSol("4x^3 +3x^1 -5");
    p2 = new PolynomialImplSol("-3x^4 -2x^5 -5 +11x^1");
    p3 = new PolynomialImplSol();
    p4 = new PolynomialImplSol("-3x^4 +3x^4 -6");
    p5 = new PolynomialImplSol("3");
    p6 = new PolynomialImplSol("");
    p7 = new PolynomialImplSol("2x^2");
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
    Polynomial p8 = new PolynomialImplSol("2x^-2");
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

  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testAddNegativePower() {
    p1.addTerm(2, -3);
    assertEquals("6x^3 +3x^1 -5", p1.toString());
  }

  @org.junit.Test public void isSame() {
    assertFalse(p1.isSame(p2));
    PolynomialImplSol p3 = new PolynomialImplSol("4x^3 +3x^1 -5");
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