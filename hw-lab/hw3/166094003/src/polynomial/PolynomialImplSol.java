package polynomial;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This class represents a polynomial.
 */
public class PolynomialImplSol implements Polynomial {
  private TreeMap<Integer, Integer> power2cof;

  /**
   * Constructor, init no term polynomial.
   */
  public PolynomialImplSol() {
    power2cof = new TreeMap<>();
  }

  /**
   * Constructor, read and parse string input into polynomial.
   * @param s input String
   */
  public PolynomialImplSol(String polyString) {
    this.power2cof = new TreeMap<>(); // Create a new TreeMap to store the polynomial terms
    Scanner sc = new Scanner(polyString); // Create a scanner to read the polynomial string

    while (sc.hasNext()) { // Continue until there are no more tokens in the string
      String token = sc.next(); // Read the next token from the string

      int index = token.indexOf('x'); // Find the index of 'x' in the token
      
      if (index == -1) { // If 'x' is not found in the token
        addTerm(Integer.parseInt(token), 0); // Convert the token to integer and add it as a term with power 0
        continue; // Move to the next iteration of the loop
      }

      String num = token.substring(0, index); // Extract the substring before 'x' as a string
      int c = Integer.parseInt(num); // Convert the substring to integer, which represents the coefficient
      index = token.indexOf('^'); // Find the index of '^' in the token
      String pow = token.substring(index + 1); // Extract the substring after '^' as a string
      int power = Integer.parseInt(pow); // Convert the substring to integer, which represents the power
      addTerm(c, power); // Add the term with coefficient 'c' and power 'power'
    }
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
    if (!(other instanceof PolynomialImplSol)) {
      throw new IllegalArgumentException("you didn't pass in a polynomial");
    }
    PolynomialImplSol otherP = (PolynomialImplSol) other;
    PolynomialImplSol res = new PolynomialImplSol();

    for (int power : power2cof.keySet()) {
      int coefficient = power2cof.get(power);
      res.addTerm(coefficient, power);
    }

    for (int power : otherP.power2cof.keySet()) {
      int coefficient = otherP.power2cof.get(power);
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
    // if it not exists
    // add term
    // if it exists
    // add coefficient
    if (power < 0) {
      throw new IllegalArgumentException("power cannot be < 0");
    }
    if (power2cof.get(power) == null) {
      power2cof.put(power, coefficient);
    } else {
      int originalCoefficient = power2cof.get(power);
      int newCoefficient = originalCoefficient + coefficient;
      power2cof.put(power, newCoefficient);
    }
  }

  /**
   * Determines if this polynomial is the same as the parameter polynomial.
   *
   * @param poly the polynomial to use
   * @return true if this polynomial is of the same concrete type and has the same
   *         terms as the parameter, false otherwise
   */
  @Override public boolean isSame(Polynomial poly) {
    // iterate through the dictionary, if not have same coefficient
    // ret false, else ret true
    if (!(poly instanceof PolynomialImplSol)) {
      return false;
    }
    PolynomialImplSol otherP = (PolynomialImplSol) poly;
    if (power2cof.size() != otherP.power2cof.size()) {
      return false;
    }
    for (int power : power2cof.keySet()) {
      if (otherP.power2cof.get(power) == null) {
        return false;
      } else {
        if (power2cof.get(power) != otherP.power2cof.get(power)) {
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
    for (int power : power2cof.keySet()) {
      int coefficient;
      coefficient = power2cof.get(power);
      res += coefficient * Math.pow(x, power);
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
    for (int pw : power2cof.keySet()) {
      int coefficient;
      if (pw == power) {
        coefficient = power2cof.get(pw);
        return coefficient;
      }
    }
    return 0;
  }

  /**
   * Get the degree of this polynomial.
   *
   * @return the degree of this polynomial as a whole number
   */
  @Override public int getDegree() {
    int maxPower = -1;
    for (int pw : power2cof.keySet()) {
      if (pw > maxPower) {
        maxPower = pw;
      }
    }
    return maxPower;
  }


  /**
   * Get the String output of PolynomialImpl class.
   *
   * @return string output
   */
  @Override
  public String toString() {
    StringBuilder ss = new StringBuilder();
    boolean hasFirstTerm = false;
    
    TreeMap<Integer, Integer> reverseDictionary = new TreeMap<>(Collections.reverseOrder());
    reverseDictionary.putAll(power2cof);

    for (int pw : reverseDictionary.keySet()) {
      int cof = reverseDictionary.get(pw);
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
