
//Incomplete, 9/12 tests pass for Term, and 7/11 tests pass for Polynomial


/**
 * Takes in user input for a polynomial term and will parse that term by breaking it up into the positive/negative sign, Letter(ex: x), Power Symbol, and exponent
 */

import java.util.Scanner;

public class Parser {

    public static void main(String[] args) {

        System.out.println("Enter a term: ");

        //Read user input for a term and storing the value

        Scanner enterTerm = new Scanner(System.in);
        String userTerm = enterTerm.next();
        Term placeholderTerm = new Term();

        /**
         * Checking for exponent in the term; if it does have an exponent, it is stored in the placeholder, converted to an integer value, and the substring
         * is taken after the power symbol shifted over one space to take the value of the exponent
         */
        if (hasExponent(userTerm)) {
            placeholderTerm.setExponent(Integer.parseInt(userTerm.substring(userTerm.indexOf("^") + 1)));

            userTerm = userTerm.substring(0, userTerm.indexOf("^"));
        }

        /**
         * Checking for coefficient;
         * If there is also a variable term, it will be checked to see if it is a valid letter and stored; Then it will be removed from the term
         * Afterwards, the positive/negative value of the term is checked
         */
        if (onlyCoefficientLeft(userTerm)) {
            placeholderTerm.setCoefficient((Integer.parseInt(userTerm)));
        } else {
            char letter = userTerm.charAt(userTerm.length() - 1);

            if (isValidChar(letter)) {
                placeholderTerm.setLetter(letter);
            }
            userTerm = userTerm.substring(0, userTerm.length() - 1);

            if (signValue(userTerm)) {
                if (userTerm.charAt(userTerm.length() - 1) == '-') {
                    placeholderTerm.setCoefficient(-1);
                }
            } else if (userTerm.length() > 0) {
                placeholderTerm.setCoefficient(Integer.parseInt(userTerm));
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
        System.out.println(placeholderTerm);
    }

    //Methods to check if the associated variable to the term is a valid char(a-z), to check whether or not the polynomial is positive/negative, and to check if only a coefficient remains
    static boolean isValidChar(char polynomialChar) {
        String validChar = "abcdefghijklmnopqrstuvwxyz";

        if (validChar.contains(String.valueOf(polynomialChar))) {
            return true;
        } else {
            System.out.println("Invalid term, try (a-z)");
            System.exit(0);
            return false;
        }
    }

    static boolean signValue(String termSign) {
        String validSigns = "+-";
        if(termSign.length() > 0 && validSigns.contains(String.valueOf(termSign.charAt(termSign.length() - 1)))) {
            return true;
        } else {
            return false;
        }
    }

    static boolean onlyCoefficientLeft(String termCoefficient) {
        String validNumbers = "0123456789";
        if(validNumbers.contains(String.valueOf(termCoefficient.charAt(termCoefficient.length() - 1)))) {
            return true;
        } else {
            return false;
        }
    }

    static boolean hasExponent(String termToCheck) {
        if (termToCheck.contains("^")) return true;
        return false;
    }

}


