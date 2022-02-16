import java.util.Scanner;

/**
 * Contains polynomial terms that are broken up into: positive/negative, Coefficient, Letter(ex: x), Power symbol, exponent
 *
 */

public class Term implements Comparable<Term>{
    //variables
    private int coefficient, exponent;
    private static final int DEFAULT_COEFFICIENT = 1, DEFAULT_EXPONENT = 1;
    private char letter;


    /**
     * Full Constructor for coefficient, and exponent for the polynomial
     * @param coefficient
     * @param exponent
     */
    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /**
     * copy constructor for term
     * @param original
     */
    public Term(Term original) {
        if(original == null) {
            System.out.println("Invalid data given to copy constructor");
            System.exit(0);
        } else {
            this.setAll(original.coefficient, original.exponent);
        }
    }

    /**
     * String constructor
     * @param termString
     */
    //initialize scanner > pass in termString > run parser methods for termString > get coefficient & exponent
    public Term(String termString) { //need to figure this one out
        Scanner testTerm = new Scanner(termString);
        Term placeholderTerm = new Term();

        if (Parser.hasExponent(termString)) {
            placeholderTerm.setExponent(Integer.parseInt(termString.substring(termString.indexOf("^") + 1)));

            termString = termString.substring(0, termString.indexOf("^"));
        }
        if (Parser.onlyCoefficientLeft(termString)) {
            placeholderTerm.setCoefficient((Integer.parseInt(termString)));
        } else {
            char letter = termString.charAt(termString.length() - 1);

            if (Parser.isValidChar(letter)) {
                placeholderTerm.setLetter(letter);
            }
            termString = termString.substring(0, termString.length() - 1);

            if (Parser.signValue(termString)) {
                if (termString.charAt(termString.length() - 1) == '-') {
                    placeholderTerm.setCoefficient(-1);
                }
            } else if (termString.length() > 0) {
                placeholderTerm.setCoefficient(Integer.parseInt(termString));
            }
        }

        //If the coefficient is 0, variable term will be changed to a null value
        if (placeholderTerm.getCoefficient() == 0) {
            placeholderTerm.setLetter('\u0000');
            placeholderTerm.setExponent(0);
        }

        //if the term is only the coefficient + an exponent with no variable term
        if (placeholderTerm.getLetter() == '\u0000') {
            double answer = Math.pow(Double.valueOf(placeholderTerm.getCoefficient()), Double.valueOf(placeholderTerm.getExponent()));
            placeholderTerm.setCoefficient((int) answer);

            placeholderTerm.setExponent(0);
        }

        //if the exponent is equal to 0, the variable tied to that exponent will be set to a value of 1/is removed because it doesnt change coefficient value
        if (placeholderTerm.getLetter() == 0) {
            if (placeholderTerm.getLetter() != '\u0000') {
                placeholderTerm.setLetter('\u0000');
            }
        }
        this.setCoefficient(placeholderTerm.getCoefficient());
        this.setExponent(placeholderTerm.getExponent());
    }

    /**
     *Default constructor
     */
    public Term() {
        this(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT);
    }

    public boolean setCoefficient(int coefficient) {
        if(coefficient >= 0 || coefficient <= 0) {
            this.coefficient = coefficient;
            return true;
        } else {
            return false;
        }
    }

    public boolean setExponent(int exponent) {
        if(exponent >= 0 || exponent <= 0) {
            this.exponent = exponent;
            return true;
        } else {
            return false;
        }
    }
    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean setAll(int c, int e) {
        return this.setCoefficient(c) && this.setExponent(e);
    }

    //Getters
    public int getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public char getLetter() {
        return letter;
    }

    /**
     * toString method
     * @return
     */
    @Override
    public String toString() {

        //converting the coefficient and the letter to string values
        String coefficientString = String.valueOf(coefficient), letterString = String.valueOf(letter); //convert coefficient/letter to strings

        //if the term doesn't have a variable attached to it, the coefficient itself should be returned
        if(letter == '\u0000'){
            return coefficientString;
        }

        //Polynomial terms with a positive or negative coefficient of 1 are handled by adding a negative symbol or leaving the coefficient as is
        if(coefficient == 0) {
            coefficientString = "0";
            return coefficientString;
        } else if(coefficient == -1) {
            coefficientString = "-";
        } else if(coefficient == '\u0000'){
            coefficientString = "";
        }

        //if exponent is either 0/1, the term will
        if(exponent == 0 || exponent == 1) {
            return coefficientString + letterString;
        }


        //return full polynomial
        if(coefficient == 0) {
            return "0";
        } else {
            return coefficientString + letterString + "^" + exponent;
        }
    }

    /**
     * compareTo method
     * @param test
     * @return Integer comparison between term test's exponent and exponent
     */
    public int compareTo(Term test) {
        return Integer.compare(exponent, test.exponent);
    }

    /**
     * clone method to take an existing term and make a duplicate of that term
     * @return termCopy;
     */
    public Object clone() {
        Term termCopy = new Term(this);
        return termCopy;
    }
}
