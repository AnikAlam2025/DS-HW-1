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
            System.out.println("Null data given to copy constructor"); //checks to see if term object being copied contains data to be copied
            System.exit(0);
        } else {
            this.setAll(original.coefficient, original.exponent);
        }
    }

    /**
     *Default constructor using default coefficient & exponent values
     */
    public Term() {
        this(DEFAULT_COEFFICIENT, DEFAULT_EXPONENT);
    }

    /**
     * String constructor
     * @param termString
     */
    //initialize scanner > pass in termString > run parser methods for termString > get coefficient & exponent
    public Term(String termString) {
        Scanner testTerm = new Scanner(termString);
        Term placeholderTerm = new Term();

        if (Parser.hasExponent(termString)) { //if termString has an exponent, parse up to one past the carrot and set that value as the exponent for placeholder
            placeholderTerm.setExponent(Integer.parseInt(termString.substring(termString.indexOf("^") + 1)));

            termString = termString.substring(0, termString.indexOf("^"));//termString is set to the input up to the carrot symbol of original termString
        }
        if (Parser.coefficientValidOnly(termString)) { //if termString(the test entered string) has only a coefficient and not a variable nor/exponent
            placeholderTerm.setCoefficient((Integer.parseInt(termString))); //then set the term to the termString since it only has the coefficient
        } else {
            char letter = termString.charAt(termString.length() - 1); //parses up to one before the last term which is before the carrot which is where the exponent is placed

            if (Parser.isValidChar(letter)) {//checks if the letter variable is one of the valid chars(a-z)
                placeholderTerm.setLetter(letter);//if it is, then placeholderTerm's letter is set to that value
            }
            termString = termString.substring(0, termString.length() - 1); //termString is set to itself up to the second to last value(just the coefficient and symbol)

            if (Parser.signValue(termString)) { //checks if termString has a valid char from validSigns(+/-)
                if (termString.charAt(termString.length() - 1) == '-') { //if it does and is negative, the coefficient is set to
                    placeholderTerm.setCoefficient(-1);
                }
            } else if (termString.length() > 0) {
                placeholderTerm.setCoefficient(Integer.parseInt(termString));//sets the remaining portion of the term to the coefficient as the rest has been assigned to other values
            }
        }

        //if the term is only the coefficient + an exponent with no variable term, it will take the power and apply it to the term to find the answer
        if (placeholderTerm.getLetter() == '\u0000') {
            double answer = Math.pow(Double.valueOf(placeholderTerm.getCoefficient()), Double.valueOf(placeholderTerm.getExponent()));
            placeholderTerm.setCoefficient((int) answer);

            placeholderTerm.setExponent(0);
        }

        this.setCoefficient(placeholderTerm.getCoefficient());
        this.setExponent(placeholderTerm.getExponent());
    }

    //setters
    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setAll(int coefficient, int exponent) {
        this.setCoefficient(coefficient);
        this.setExponent(exponent);
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

        //if the term doesn't have a variable attached to it, the coefficient itself is returned
        if(letter == '\u0000'){
            return coefficientString;
        }

        //Polynomial terms with a positive or negative coefficient of 1 are handled by adding a negative symbol or leaving the coefficient as is
        if(coefficient == 0) {
            coefficientString = "0";
            return coefficientString;
        } else if(coefficient == -1) {
            coefficientString = "-";
        } else if(coefficient == 1) {
            return coefficientString;
        } else if(coefficient == '\u0000') {
            coefficientString = "";
        }

        //if term's exponent is either 0/1 but has a coefficient + variable, the term will just return the coefficient and variable
        if(exponent == 0 || exponent == 1) {
            return coefficientString + letterString;
        }

        return coefficientString + letterString + "^" + exponent;
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
