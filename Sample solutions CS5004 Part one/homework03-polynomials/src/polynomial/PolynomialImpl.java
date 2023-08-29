package polynomial;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Polynomial Implementation of the Polynomial Interface.
 */
public class PolynomialImpl implements Polynomial {
  Map<Integer, Integer> polynomialMap;

  /**
   * Default constructor, provides the "0" polynomial.
   */
  public PolynomialImpl() {
    this.polynomialMap = new TreeMap<>();
  }

  /**
   * Constructor takes a string and creates a polynomial.
   *
   * @param polyString the polynomial in String form.
   */
  public PolynomialImpl(String polyString) {
    this.polynomialMap = new TreeMap<>(); // Create a new TreeMap to store the polynomial terms
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

@Override
public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (this.getClass() != other.getClass()) { // Check if the two polynomials belong to the same concrete class
        throw new IllegalArgumentException("The two polynomials belong to different concrete classes!");
    }

    PolynomialImpl newPoly = new PolynomialImpl(); // Create a new PolynomialImpl object to store the result of the addition

    // Iterate over the polynomial terms of 'this' polynomial
    for (Map.Entry<Integer, Integer> entry : this.polynomialMap.entrySet()) {
        Integer key = entry.getKey(); // Get the power of the current term
        Integer value = entry.getValue(); // Get the coefficient of the current term

        if (((PolynomialImpl) other).polynomialMap.containsKey(key)) {
            // If the other polynomial also contains a term with the same power, add the coefficients
            value += ((PolynomialImpl) other).polynomialMap.get(key);
        }
        newPoly.addTerm(value, key); // Add the new term (coefficient, power) to the new polynomial
    }
    // Iterate over the polynomial terms of the 'other' polynomial
    for (Map.Entry<Integer, Integer> entry : ((PolynomialImpl) other).polynomialMap.entrySet()) {
        Integer key = entry.getKey(); // Get the power of the current term
        Integer value = entry.getValue(); // Get the coefficient of the current term

        newPoly.polynomialMap.putIfAbsent(key, value);
        // Add the term from 'other' polynomial to the new polynomial if it doesn't have a term with the same power already
    }
    return newPoly; // Return the new polynomial after addition
}

  @Override public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Only non-negative power accepted");
    } else {
      if (polynomialMap.containsKey(power)) {
        coefficient += polynomialMap.get(power);
      }
      if (coefficient != 0) {
        polynomialMap.put(power, coefficient);
      } else {
        polynomialMap.remove(power);
      }
    }
  }

  @Override public boolean isSame(Polynomial poly) {
    return this.equals(poly);
  }

  @Override public double evaluate(double x) {
    double answer = 0.;
    for (Map.Entry<Integer, Integer> entry : polynomialMap.entrySet()) {
      Integer key = entry.getKey();
      Integer value = entry.getValue();
      answer += value * Math.pow(x, key);
    }
    return answer;
  }

  @Override public int getCoefficient(int power) {
    int coefficient = 0;
    if (polynomialMap.containsKey(power)) {
      coefficient = polynomialMap.get(power);
    }
    return coefficient;
  }

  @Override public int getDegree() {
    Object[] keys = polynomialMap.keySet().toArray();
    return (int) keys[keys.length - 1];
  }

  @Override
  public String toString() {
      String result = "0"; // Default result is "0" if polynomialMap is empty
      if (polynomialMap.size() != 0) { // Check if the polynomialMap is not empty
          StringBuilder polyString = new StringBuilder(); // Create a StringBuilder to construct the polynomial string
  
          // Iterate over the terms of the polynomialMap
          for (Map.Entry<Integer, Integer> entry : polynomialMap.entrySet()) {
              Integer key = entry.getKey(); // Get the power of the current term
              Integer value = entry.getValue(); // Get the coefficient of the current term
  
              polyString.insert(0, termToString(key, value));
              // Insert the string representation of the term at the beginning of the StringBuilder
          }
          result = polyString.toString(); // Convert the StringBuilder to a String
      }
      return result; // Return the resulting polynomial string
  }
  
  /**
   * Get the right String for an individual term (based on its location).
   *
   * @param power       the power of the term.
   * @param coefficient the coefficient of the term.
   * @return the String representing the term.
   */
  private String termToString(int power, int coefficient) {
      String termString = "";
  
      if (power != this.getDegree()) { // Check if the power is not equal to the degree of the polynomial
          termString += " ";
          // Add a space at the beginning of the termString if it's not the leading term
  
          if (coefficient > 0) {
              termString += "+"; // Add a plus sign if the coefficient is positive
          }
      }
  
      termString += String.valueOf(coefficient); // Append the coefficient to the termString
  
      if (power != 0) {
          termString += "x^" + power; // Append the power and "x^" to the termString if the power is not zero
      }
  
      return termString; // Return the resulting term string
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolynomialImpl that = (PolynomialImpl) o;
    return Objects.equals(polynomialMap, that.polynomialMap);
  }

  @Override public int hashCode() {
    return Objects.hash(polynomialMap);
  }
}
