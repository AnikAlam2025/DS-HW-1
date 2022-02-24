//Incomplete, 10/12 tests pass for Term, and 9/11 tests pass for Polynomial

import java.util.Scanner;

/**
 * Takes in user input for a polynomial term and will parse that term by breaking it up into the positive/negative sign, Letter(ex: x), Power Symbol, and exponent
 * will check values of each of these components to make sure they're valid(like the letter) and will return the parsed term
 */
public class Parser {

    public static void main(String[] args) {

        //Read user input for the menu option

        Scanner enterTerm = new Scanner(System.in);
        String userTerm = enterTerm.next();
        Term placeholderTerm = new Term();

        /*
         * Checking for exponent in the term; if it does have an exponent, it is stored in the placeholder
         */
        if (hasExponent(userTerm)) {
            placeholderTerm.setExponent(Integer.parseInt(userTerm.substring(userTerm.indexOf("^") + 1)));//reading the term past the exp carrot
            userTerm = userTerm.substring(0, userTerm.indexOf("^"));//After removing exponent, userTerm is set to subString up to the carrot to parse the coefficient
        }

        /*
         * Checking for coefficient in userTerm which is a String(String termChecked in helper method) to see if it has one of the valid values in the coefficient(0-9)
         *  if it does, then the condition is met
         */
        if (coefficientValidOnly(userTerm)) {
            placeholderTerm.setCoefficient((Integer.parseInt(userTerm))); //placeholderTerm coefficient is set to the parsed String userTerm which is returned as an integer + wrapped
        } else { //if the userTerm string has a variable before the carrot instead of 0-9(ex: 5x^2)
            char letter = userTerm.charAt(userTerm.length() - 1);

            if (isValidChar(letter)) {//checks if letter has valid character from the validChar String
                placeholderTerm.setLetter(letter);
            }
            userTerm = userTerm.substring(0, userTerm.length() - 1);//userTerm substring now updated to the original term up until before carrot

            if (signValue(userTerm)) { //if String userTerm has a char from the validSigns string, then it is true
                if (userTerm.charAt(userTerm.length() - 1) == '-') { //checking to see if the input term has a negative, will set the coefficient to -1 if so
                    placeholderTerm.setCoefficient(-1);
                }
            } else if (userTerm.length() > 0) { //if greater, it will set the coefficient to the userTerm up to the carrot
                placeholderTerm.setCoefficient(Integer.parseInt(userTerm));
            }
        }

        if (placeholderTerm.getCoefficient() == 0) {
            placeholderTerm.setLetter('\u0000'); //variable is set to a null value if variable == 0
            placeholderTerm.setExponent(1);//exponent is set to 1, if set to 0, the term will return 1 which is incorrect
        }

        //if the term is only the coefficient + an exponent with no variable
        if (placeholderTerm.getLetter() == '\u0000') {
            double answer = Math.pow(placeholderTerm.getCoefficient(), placeholderTerm.getExponent());//value is set to the number to the power of the exponent(5^2 = 25)
            placeholderTerm.setCoefficient((int) answer);
            placeholderTerm.setExponent(1); //exponent is then set to one so the value remains the same
        }

        //if the coefficient is 0, the turn will return 0 otherwise it will return the value in placeholderTerm
        if(placeholderTerm.getCoefficient() == 0) {
            //System.out.println("0");
        } else {
            //System.out.println(placeholderTerm);
        }
    }

    /**
     * Methods to check if polynomialChar has one of the valid characters in validChar, if so then true
     */
    static boolean isValidChar(char polynomialChar) {
        String validChar = "abcdefghijklmnopqrstuvwxyz";

        if (validChar.contains(String.valueOf(polynomialChar))) {//if polynomialChar/letter have a valid char from the validChar string, then true
            return true;
        } else {
            System.out.println("Invalid term, try (a-z)");
            System.exit(0);
            return false;
        }
    }

    /**
     * Method to check if input String contains a valid char from validSigns string, true if it does
     * @param termSign
     * @return
     */
    static boolean signValue(String termSign) {
        String validSigns = "+-";
        if(validSigns.contains(String.valueOf(termSign.charAt(termSign.length() - 1)))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that checks if there is a only valid coefficient remaining with no variable or exponent
     * @param termCoefficient
     * @return
     */
    static boolean coefficientValidOnly(String termCoefficient) {
        String validNumbers = "0123456789";
        //checking if String termCoefficient contains a valid character from validNumbers at c
        if(validNumbers.contains(String.valueOf(termCoefficient.charAt(termCoefficient.length() - 1)))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if a term contains an exponent carrot and will return true if so, false if it does not
     * @param termChecked
     * @return
     */
    static boolean hasExponent(String termChecked) {
        if (termChecked.contains("^")){
            return true;
        } else {
            return false;
        }
    }

}


