package polynomial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PolynomialImpl implements Polynomial {

    private ArrayList < Term > terms;

    /**
     * Constructor, Create no term polynomial.
     */
    public PolynomialImpl() {
        terms = new ArrayList < Term > ();
    }

    /**
     * Constructor, read and parse string input.
     */
    public PolynomialImpl(String s) {
        terms = new ArrayList < Term > ();
        Scanner sc = new Scanner(s);
        while (sc.hasNext()) {
            String termStr = sc.next();
            int coefficient;
            int power;
            if (termStr.contains("x^")) {
                String[] parts = termStr.split("x\\^");
                coefficient = Integer.parseInt(parts[0]);
                power = Integer.parseInt(parts[1]);
            } else if (termStr.contains("x")) {
                coefficient = Integer.parseInt(termStr.split("x")[0]);
                power = 1;
            } else {
                coefficient = Integer.parseInt(termStr);
                power = 0;
            }
            addTerm(coefficient, power);
        }
        sc.close();
    }

    /**
     * Add this polynomial to another and return the result as another polynomial.
     *
     * @param other the other polynomial to be added
     * @return the resulting polynomial
     * @throws IllegalArgumentException if parameter is not the same concrete type as the current
     * object.
     */
    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException {
        if (!(other instanceof PolynomialImpl)) {
            throw new IllegalArgumentException("Parameter is not the same concrete type as the current object.");
        }
        PolynomialImpl result = new PolynomialImpl();
        int thisIndex = 0;
        int otherIndex = 0;
        while (thisIndex < terms.size() && otherIndex < ((PolynomialImpl) other).terms.size()) {
            Term thisTerm = terms.get(thisIndex);
            Term otherTerm = ((PolynomialImpl) other).terms.get(otherIndex);
            if (thisTerm.power == otherTerm.power) {
                int sum = thisTerm.coefficient + otherTerm.coefficient;
                if (sum != 0) {
                    result.terms.add(new Term(sum, thisTerm.power));
                }
                thisIndex++;
                otherIndex++;
            } else if (thisTerm.power > otherTerm.power) {
                result.terms.add(thisTerm);
                thisIndex++;
            } else {
                result.terms.add(otherTerm);
                otherIndex++;
            }
        }
        while (thisIndex < terms.size()) {
            result.terms.add(terms.get(thisIndex));
            thisIndex++;
        }
        while (otherIndex < ((PolynomialImpl) other).terms.size()) {
            result.terms.add(((PolynomialImpl) other).terms.get(otherIndex));
            otherIndex++;
        }
        return result;
    }

    /**
     * Add a term to this polynomial with the specified coefficient and power.
     *
     * @param coefficient the coefficient of the term to be added
     * @param power       the power of the term to be added
     * @throws IllegalArgumentException if the power is negative
     */
    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException {
        if (power < 0) {
            throw new IllegalArgumentException("Power cannot be negative.");
        }
        if (coefficient != 0) {
            boolean added = false;
            for (int i = 0; i < terms.size(); i++) {
                Term term = terms.get(i);
                if (term.power == power) {
                    term.coefficient += coefficient;
                    if (term.coefficient == 0) {
                        terms.remove(i);
                    }
                    added = true;
                    break;
                } else if (term.power < power) {
                    terms.add(i, new Term(coefficient, power));
                    added = true;
                    break;
                }
            }
            if (!added) {
                terms.add(new Term(coefficient, power));
            }
        }
    }

    /**

    Multiply this polynomial by another and return the result as another polynomial.
    @param other the other polynomial to be multiplied
    @return the resulting polynomial
    @throws IllegalArgumentException if parameter is not the same concrete type as the current
    object.
    */
    @Override
    public Polynomial multiply(Polynomial other) throws IllegalArgumentException {
        if (!(other instanceof PolynomialImpl)) {
            throw new IllegalArgumentException("Parameter is not the same concrete type as the current object.");
        }
        PolynomialImpl result = new PolynomialImpl();
        for (Term thisTerm: terms) {
            for (Term otherTerm: ((PolynomialImpl) other).terms) {
                int coefficient = thisTerm.coefficient * otherTerm.coefficient;
                int power = thisTerm.power + otherTerm.power;
                result.addTerm(coefficient, power);
            }
        }
        return result;
    }
    /**

    Evaluate the polynomial with the given value for x.
    @param x the value of x
    @return the result of the evaluation
    */
    @Override
    public double evaluate(double x) {
        double result = 0;
        for (Term term: terms) {
            result += term.coefficient * Math.pow(x, term.power);
        }
        return result;
    }
    /**

    Get the degree of the polynomial.
    @return the degree of the polynomial
    */
    @Override
    public int getDegree() {
        if (terms.isEmpty()) {
            return 0;
        }
        return terms.get(terms.size() - 1).power;
    }
    /**

    Get the coefficient of the term with the specified power.
    @param power the power of the term
    @return the coefficient of the term
    */
    @Override
    public int getCoefficient(int power) {
        for (Term term: terms) {
            if (term.power == power) {
                return term.coefficient;
            }
        }
        return 0;
    }
    /**

    Convert the polynomial to a string representation.
    @return the string representation of the polynomial
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(terms);
        for (Term term: terms) {
            if (term.coefficient > 0 && sb.length() > 0) {
                sb.append("+");
            }
            if (term.coefficient != 1 || term.power == 0) {
                sb.append(term.coefficient);
            }
            if (term.power > 0) {
                sb.append("x");
                if (term.power > 1) {
                    sb.append("^").append(term.power);
                }
            }
        }
        if (sb.length() == 0) {
            sb.append("0");
        }
        return sb.toString();
    }
    /**

    Term class represents a term in a polynomial.
    */
    private static class Term implements Comparable < Term > {
        int coefficient;
        int power;

        /**

        Constructor.
        @param coefficient the coefficient of the term
        @param power the power of the term
        */
        public Term(int coefficient, int power) {
            this.coefficient = coefficient;
            this.power = power;
        }
        /**

        Compare terms based on their power.
        */
        @Override
        public int compareTo(Term other) {
            return Integer.compare(power, other.power);
        }
    }
}