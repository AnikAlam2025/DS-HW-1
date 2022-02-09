/**
 * Contains polynomial terms that are broken up into: positive/negative, Coefficient, Letter(ex: x), Power symbol, exponent
 *
 *
 */

public class Term {
    //variables
    private int coefficient, exponent;
    private char letter;

    /**
     * setALL for coefficient, exponent, and letter for the polynomial
     * @param coefficient
     * @param exponent
     * @param letter
     */
    public Term(int coefficient, int exponent, char letter) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.letter = letter;
    }

    //Constructor
    public Term() {

    }

    //Setters

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public void setLetter(char letter) {
        this.letter = letter;
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

    //toString
    @Override
    public String toString() {
        //converting the coefficient and the letter to string values
        String coefficientString = String.valueOf(coefficient), letterString = String.valueOf(letter); //convert coefficient/letter to strings

        //if the polynomial doesn't have a variable attached to it, the coefficient itself should be returned
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


}
