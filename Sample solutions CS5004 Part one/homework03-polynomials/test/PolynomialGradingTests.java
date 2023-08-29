import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.Test;
import polynomial.Polynomial;
import polynomial.PolynomialImpl;

/**
 * Unit tests for the polynomial implementation.
 */
public class PolynomialGradingTests {

  private Polynomial createPolynomial(String exp) {
    Polynomial poly = new PolynomialImpl();
    Scanner sc = new Scanner(exp);

    while (sc.hasNext()) {
      String token = sc.next();

      int index = token.indexOf('x');
      if (index == -1) {
        poly.addTerm(Integer.parseInt(token), 0);
        continue;
      }
      String num = token.substring(0, index);
      int c = Integer.parseInt(num);
      index = token.indexOf('^');
      String pow = token.substring(index + 1);
      int power = Integer.parseInt(pow);
      poly.addTerm(c, power);
    }
    return poly;
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test polynomial's degree", max_score = 3)
  public void testDegree() {
    List<Integer> powers;
    List<Integer> coefficients;
    Polynomial a = new PolynomialImpl();

    powers = new ArrayList<>();
    coefficients = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      powers.add(i);
      coefficients.add((int) (Math.random() * 40 - 20));
    }

    Collections.shuffle(powers);

    int degree = -1;

    for (int i = 0; i < powers.size(); i++) {
      if (coefficients.get(i) != 0) {
        a.addTerm(coefficients.get(i), powers.get(i));
        if (degree < powers.get(i)) {
          degree = powers.get(i);
        }
        assertEquals("Degree is not correct", degree, a.getDegree());
      }
    }
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test invalid polynomial creation by term", max_score = 3)
  public void testInvalidPolynomialCreationByTerm() {
    List<Integer> powers;
    List<Integer> coefficients;
    Polynomial a = new PolynomialImpl();

    powers = new ArrayList<>();
    coefficients = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      powers.add(i - 50);
      coefficients.add((int) (Math.random() * 40 - 20));
    }

    Collections.shuffle(powers);

    int degree = -1;
    Map<Integer, Integer> presentPowers = new HashMap<>();

    for (int i = 0; i < powers.size(); i++) {
      try {
        a.addTerm(coefficients.get(i), powers.get(i));
        if (powers.get(i) < 0) {
          fail("A term with negative power should not work, but did.");
        }
      } catch (IllegalArgumentException e) {
        if (powers.get(i) > 0) {
          fail("A term with positive power should have worked, but did not.");
        }
      }
      presentPowers.put(powers.get(i), coefficients.get(i));
      if (degree < powers.get(i)) {
        degree = powers.get(i);
      }
      for (int j = 0; j <= degree; j++) {
        //look for the coefficient of power j
        int expected = 0;
        if (presentPowers.containsKey(j)) {
          expected = presentPowers.get(j);
        }
        assertEquals(
            "The coefficients do not match when creating a " + "polynomial one term at a time",
            expected, a.getCoefficient(j));
      }
    }
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test polynomial's toString method", max_score = 3)
  public void testPolynomialString() {
    String[] inputs = { "3x^2", "3x^2 +4x^1 -2", "-5x^4 -3x^2 +2x^1 +12", "5x^1 -3x^3 +2x^5 +12x^1",
        "-3x^3 +2x^5 -3 +12x^1", "" };

    String[] expected = { "3x^2", "3x^2 +4x^1 -2", "-5x^4 -3x^2 +2x^1 +12", "2x^5 -3x^3 +17x^1",
        "2x^5 -3x^3 +12x^1 -3", "0" };

    for (int i = 0; i < inputs.length; i++) {
      Polynomial p = createPolynomial(inputs[i]);
      assertEquals("Incorrect string from toString, should be " + expected[i] + " " + "but " + "is "
          + p, expected[i], p.toString());
    }
  }

  @Test(timeout = 30000)
  // @GradedTest(name = "Test polynomial's evaluate method", max_score = 3)
  public void testEvaluate() {
    Polynomial a = new PolynomialImpl();
    List<Integer> powers;
    List<Integer> coefficients;

    powers = new ArrayList<>();
    coefficients = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      powers.add(i);
      coefficients.add((int) (Math.random() * 40 - 20));
    }

    Collections.shuffle(powers);

    double answer = 0;
    for (int i = 0; i < powers.size(); i++) {
      answer += coefficients.get(i) * Math.pow(1.01, powers.get(i));
      a.addTerm(coefficients.get(i), powers.get(i));
      assertEquals("For f(x)=" + a + " f(1.01) should be " + answer + " but"
          + " is "
          + a.evaluate(1.01), answer, a.evaluate(1.01), 0.001);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  // @GradedTest(name = "Test that polynomial from different concrete classes cannot be added",
  // max_score = 3)
  public void testAddIllegalPolynomial() {
    Polynomial real = new PolynomialImpl();
    Polynomial other = new TestingPolynomial();
    real.add(other);
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test polynomial's add method", max_score = 3)
  public void testAddPolynomials() {
    Polynomial a;
    Polynomial b;
    Polynomial c;
    int maxDegree = 200;
    int[] one = new int[maxDegree];
    int[] two = new int[maxDegree];

    for (int i = 0; i < maxDegree; i++) {
      one[i] = (int) (200 * Math.random() - 100);
      two[i] = (int) (200 * Math.random() - 100);
    }

    for (int trials = 0; trials < 10; trials++) {
      int[] result = new int[maxDegree];
      a = new PolynomialImpl();
      b = new PolynomialImpl();
      for (int i = 0; i < maxDegree / 2; i++) {
        int i1 = (int) (Math.random() * (maxDegree - 1));
        int i2 = (int) (Math.random() * (maxDegree - 1));

        a.addTerm(one[i1], i1);
        b.addTerm(two[i2], i2);
        result[i1] += one[i1];
        result[i2] += two[i2];
      }
      c = a.add(b);

      for (int i = 0; i < result.length; i++) {
        assertEquals("Adding polynomials " + a + " and " + b + " produces "
            + c.toString() + " which is incorrect", result[i], c.getCoefficient(i));
      }
    }
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test polynomial can be created one term at a time", max_score = 3)
  public void testValidPolynomialCreationByTerm() {
    List<Integer> powers;
    List<Integer> coefficients;
    Polynomial a = new PolynomialImpl();

    powers = new ArrayList<>();
    coefficients = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      powers.add(i);
      coefficients.add((int) (Math.random() * 40 - 20));
    }

    Collections.shuffle(powers);

    int degree = -1;
    Map<Integer, Integer> presentPowers = new HashMap<>();

    for (int i = 0; i < powers.size(); i++) {
      a.addTerm(coefficients.get(i), powers.get(i));
      presentPowers.put(powers.get(i), coefficients.get(i));
      if (degree < powers.get(i)) {
        degree = powers.get(i);
      }

      for (int j = 0; j <= degree; j++) {
        //look for the coefficient of power j
        int expected = 0;
        if (presentPowers.containsKey(j)) {
          expected = presentPowers.get(j);
        }
        assertEquals(
            "The coefficients do not match when creating a " + "polynomial one term at a time",
            expected, a.getCoefficient(j));
      }
    }
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test polynomial is not created with invalid input String", max_score = 3)
  public void testInvalidPolynomialCreationByString() {

    try {
      new PolynomialImpl("3x^2+4x^1 -2");
      fail("Creating polynomial 3x^2+4x^1 -2 should have failed but did not.");
    } catch (IllegalArgumentException e) {
      //should happen if correct, ignore
    }

    try {
      new PolynomialImpl("-5x^-4 -3x^2 +2x^1 +12");
      fail("Creating polynomial -5x^-4 -3x^2 +2x^1 +12 should have failed but" + " did not.");
    } catch (IllegalArgumentException e) {
      //should happen if correct, ignore
    }

    try {
      new PolynomialImpl("5x^1 -3x^3 +2.1x^5 +12x^1");
      fail("Creating polynomial 5x^1 -3x^3 +2.1x^5 +12x^1 should have failed " + "but did not.");
    } catch (IllegalArgumentException e) {
      //should happen if correct, ignore
    }

    try {
      new PolynomialImpl("-3x^3 +ax^5 -3 +12x^1");
      fail("Creating polynomial -3x^3 +ax^5 -3 +12x^1 should have failed but " + "did not.");
    } catch (IllegalArgumentException e) {
      //should happen if correct, ignore
    }

    try {
      new PolynomialImpl("-3x^3 +-2x^5 -3 +12x^1");
      fail("Creating polynomial -3x^3 +-2x^5 -3 +12x^1 should have failed but " + "did not.");
    } catch (IllegalArgumentException e) {
      //should happen if correct, ignore
    }

  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test illegal sameness", max_score = 3)
  public void testAddIllegalSame() {
    Polynomial real = new PolynomialImpl();
    Polynomial other = new TestingPolynomial();
    assertFalse(real.isSame(other));
  }

  @Test(timeout = 3000)
  // @GradedTest(name = "Test sameness", max_score = 3)
  public void testSameness() {
    Polynomial a = new PolynomialImpl();
    Polynomial b = new PolynomialImpl();
    Polynomial c = new PolynomialImpl();
    List<Integer> powers;
    List<Integer> coefficients;

    powers = new ArrayList<>();
    coefficients = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      powers.add(i);
      coefficients.add((int) (Math.random() * 40 - 20));
    }

    Collections.shuffle(powers);

    for (int i = 0; i < powers.size(); i++) {
      a.addTerm(coefficients.get(i), powers.get(i));
      //split term for b
      int offset = (int) (Math.random() * 10);
      b.addTerm(coefficients.get(i) - offset, powers.get(i));
      b.addTerm(offset, powers.get(i));

      assertTrue("Polynomials are not equal but they should be", a.isSame(a));
      assertTrue("Polynomials are not equal but they should be", a.isSame(b));
      assertTrue("Polynomials are not equal but they should be", b.isSame(a));
    }

    for (int i = powers.size() - 1; i >= 0; i--) {
      c.addTerm(coefficients.get(i), powers.get(i));
    }

    assertTrue("Polynomials are not equal but they should be", a.isSame(b));
    assertTrue("Polynomials are not equal but they should be", b.isSame(a));
    assertTrue("Polynomials are not equal but they should be", a.isSame(c));
    assertTrue("Polynomials are not equal but they should be", c.isSame(a));
    assertTrue("Polynomials are not equal but they should be", b.isSame(c));
    assertTrue("Polynomials are not equal but they should be", c.isSame(b));

  }

  /**
   * Check that the highest term gets removed if it becomes zero.
   */
  @Test
  public void highestTermBecomesZero() {
    Polynomial p1 = new PolynomialImpl("-10x^98 +8x^96 -10x^93 -13x^90 -16x^89 +8x^87");
    Polynomial p2 = new PolynomialImpl("-5x^99 -10x^98 +8x^96 -10x^93 -13x^90 -16x^89 +8x^87");
    assertFalse(p1.isSame(p2));

    p2.addTerm(5, 99);
    assertTrue(p1.isSame(p2));
  }

  /**
   * A different implementation of Polynomial for testing.
   */
  public static class TestingPolynomial implements Polynomial {

    @Override public Polynomial add(Polynomial other) throws IllegalArgumentException {
      return null;
    }

    @Override public void addTerm(int coefficient, int power) throws IllegalArgumentException {
      // no implementation
    }

    @Override public boolean isSame(Polynomial poly) {
      return false;
    }

    @Override public double evaluate(double x) {
      return 0;
    }

    @Override public int getCoefficient(int power) {
      return 0;
    }

    @Override public int getDegree() {
      return 0;
    }
  }
}
