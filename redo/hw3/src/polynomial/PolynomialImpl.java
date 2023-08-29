package polynomial;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class PolynomialImpl implements Polynomial {
  private TreeMap<Integer, Integer> pw2cof;
  public PolynomialImpl() {
    pw2cof = new TreeMap<>(Collections.reverseOrder());
  }


  public PolynomialImpl(String s) throws IllegalArgumentException {
    this();
    Scanner sc = new Scanner(s);
    while (sc.hasNext()) {
      String ss = sc.next();
      int cof;
      int power = 0;
      int indexOfX = ss.indexOf("x");
      if (indexOfX >= 0) {
        if (indexOfX == 0) {
          cof = 1;
        } else if (indexOfX == 1 && ss.charAt(0) == '-') {
          cof = -1;
        } else {
          cof = Integer.parseInt(ss.substring(0, indexOfX));
        }
        int powerIndex = ss.indexOf("^");
        if (powerIndex >= 0) {
          power = Integer.parseInt(ss.substring(powerIndex + 1));
          if (power < 0) {
            throw new IllegalArgumentException("power cannot be smaller than 0");
          }
        } else {
          power = 1;
        }
      } else {
        cof = Integer.parseInt(ss);
      }
      addTerm(cof, power);
    }
    sc.close();
  }

  /**
   * Add this polynomial to another and return the result as another polynomial.
   *
   * @param other the other polynomial to be added
   * @return the resulting polynomial
   * @throws IllegalArgumentException if parameter is not the same concrete type as the current
   *                                  object.
   */
  @Override public Polynomial add(Polynomial other) throws IllegalArgumentException {
    // if not same obj, error, cast
    // create result, iterate two and add term
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("you didn't pass in a polynomial");
    }
    PolynomialImpl otherP = (PolynomialImpl) other;
    PolynomialImpl res = new PolynomialImpl();

    for (int power : pw2cof.keySet()) {
      int coefficient = pw2cof.get(power);
      res.addTerm(coefficient, power);
    }

    for (int power : otherP.pw2cof.keySet()) {
      int coefficient = otherP.pw2cof.get(power);
      res.addTerm(coefficient, power);
    }
    return res;
  }

  /**
   * Add a term to this polynomial with the specified coefficient and power.
   *
   * @param coefficient the coefficient of the term to be added
   * @param power       the power of the term to be added
   * @throws IllegalArgumentException if the power is negative
   */
  @Override public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("e");
    }
    if (pw2cof.containsKey(power)) {
      int oldCof = pw2cof.get(power);
      int newCof = oldCof + coefficient;
      pw2cof.put(power, newCof);
    } else {
      pw2cof.put(power, coefficient);
    }
  }

  /**
   * Determines if this polynomial is the same as the parameter polynomial.
   *
   * @param poly the polynomial to use
   * @return true if this polynomial is of the same concrete type and has the same terms as the
   * parameter, false otherwise
   */
  @Override public boolean isSame(Polynomial poly) throws IllegalArgumentException{
    if (! (poly instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("e");
    }
    PolynomialImpl otherP = (PolynomialImpl) poly;
    for (int pw: otherP.pw2cof.keySet()) {
      if (!this.pw2cof.containsKey(pw)){
        return false;
      } else {
        if (this.pw2cof.get(pw) != otherP.pw2cof.get(pw)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Evaluate the value of this polynomial at the given value of the variable.
   *
   * @param x the value at which the polynomial is to be evaluated.
   * @return the value of the polynomial at x
   */
  @Override public double evaluate(double x) {
    double res = 0.0;
    for (int pw : this.pw2cof.keySet()) {
      int cof = this.pw2cof.get(pw);
      res += cof * Math.pow(x, pw);
    }
    return res;
  }

  /**
   * Return the coefficient of the term with the given power.
   *
   * @param power the power whose coefficient is sought
   * @return the coefficient at the given power
   */
  @Override public int getCoefficient(int power) {
    return this.pw2cof.getOrDefault(power, 0);
  }

  /**
   * Get the degree of this polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  @Override public int getDegree() {
    int deg = 0;
    for (int pw : this.pw2cof.keySet()) {
      if (pw > deg) {
        deg = pw;
      }
    }
    return deg;
  }


  @Override
  public String toString() {
    StringBuilder ss = new StringBuilder();
    boolean hasFirstTerm = false;



    for (int pw : pw2cof.keySet()) {
      int cof = pw2cof.get(pw);
      if (cof == 0) {
        continue;
      }
      if (hasFirstTerm && cof > 0) {
        ss.append(" +");
      }
      if (hasFirstTerm && cof < 0) {
        ss.append(" -");
      } else if (!hasFirstTerm && cof < 0) {
        ss.append("-");
      }
      if (cof != 1 || pw == 0) {
        ss.append(Math.abs(cof));
      }
      if (pw > 0) {
        ss.append("x");
        ss.append("^" + pw);
      }
      hasFirstTerm = true;
    }
    if (!hasFirstTerm) {
      ss.append("0");
    }
    return ss.toString();
  }
}