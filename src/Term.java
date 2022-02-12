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
     * setALL for coefficient, exponent, and letter for the polynomial
     * @param coefficient
     * @param exponent
     * @param letter
     */
//    public Term(int coefficient, int exponent, char letter) {
//        this.coefficient = coefficient;
//        this.exponent = exponent;
//        this.letter = letter;
//    }

    /**
     *
     * @param coefficient
     * @param exponent
     */
    public Term(int coefficient, int exponent) {
        if(!this.setAll(coefficient, exponent)) {
            System.out.println("invalid data given to term constructor");
            System.exit(0);
        }
    }

    /**
     *
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
     *
     * @param term
     */
    public Term(String term) {
        if(!this.setAll(coefficient, exponent)) {
            System.out.println("invalid data given to term constructor");
            System.exit(0);
        } else {

        }
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
        if(coefficient == 1) {
            coefficientString = "";
        } else if(coefficient == -1) {
            coefficientString = "-";
        }

        //if exponent is either 0/1, the term will
        if(exponent == 0 || exponent == 1) {
            return coefficientString + letterString;
        }

        //return full polynomial
        return coefficientString + letterString + "^" + exponent;
    }

    /**
     * compareTo method
     * @param test
     * @return
     */
    public int compareTo(Term test) {
        return Integer.compare(exponent, test.exponent);
    }

    public Object duplicate() {
        Term term = new Term(this);
        return term;
    }
}
