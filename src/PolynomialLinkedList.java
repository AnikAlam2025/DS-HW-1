//Linked List implementation class for Polynomials

import java.util.LinkedList;
import java.util.Collections;

public class PolynomialLinkedList {
    LinkedList<Term> numTermsLinked;

    /**
     * Full Constructor method to create a polynomial object
     */
    public PolynomialLinkedList() {
        numTermsLinked = new LinkedList<>();
    }

    /**
     * Copy Constructor method which should make a copy of a Polynomial object
     * @param original
     */
    public PolynomialLinkedList(PolynomialLinkedList original) {
        numTermsLinked = new LinkedList<>();

        for (int i = 0; i < original.getNumTermsLinked(); i++) {
            this.addTerm(new Term(original.getTermLinked(i)));
        }
    }

    //Getters
    public int getNumTermsLinked() {
        return numTermsLinked.size();
    }

    public Term getTermLinked(int i) {
        return numTermsLinked.get(i);
    }

    /**
     * toString method
     * checks if the numTerms list is empty and will return "0" if so
     * Checks if coefficient is 0 which will equate to 0 and remove the term
     * Checks if exponent is 0 which means the term
     * @return
     */
    @Override
    public String toString() {
        String finalPolynomial = "", coefficient, exponent;

        //numTerm size check
        if(numTermsLinked.size() == 0) {
            return "0";
        }

        for(int i = 0; i < numTermsLinked.size(); i++) {
            coefficient = Integer.toString(numTermsLinked.get(i).getCoefficient()); //the coefficient of numTerms at i is taken and wrapped by Integer class
            exponent = Integer.toString(numTermsLinked.get(i).getExponent());//the exponent of numTerms at i is taken and wrapped by Integer class


            //exponent check; if 0 then the exponent has no value, if 1 then the exponent is simply the variable, if else then it is the variable + exponent
            if (numTermsLinked.get(i).getExponent() == 0) {
                exponent = "";
            } else if (numTermsLinked.get(i).getExponent() == 1) {
                exponent = "x";
            } else {
                exponent = "x^" + exponent;
            }

            //coefficient check
            if (numTermsLinked.get(i).getCoefficient() == 0) {//if the term at i has a coefficient = 0, a blank space is returned indicating absence of a coefficient
                return "";
            } else if (numTermsLinked.get(i).getCoefficient() == 1) { //if = 1, returns the coefficient of that term with an addition symbol afterwards
                coefficient = ((numTermsLinked.get(i).getCoefficient() == 1)) + "+";
            } else if (numTermsLinked.get(i).getCoefficient() == -1) { //if = -1, returns the coefficient with a negative symbol to indicate the value is negative
                coefficient = ((numTermsLinked.get(i).getCoefficient() == -1)) + "-";
            } else if(numTermsLinked.get(i).getCoefficient() > 0) { //any positive value above 1 will return an addition symbol with the coefficient
                coefficient = "+" + coefficient;
            }

            finalPolynomial += coefficient + exponent;
        }
//        return finalPolynomial;
        return finalPolynomial.substring(1);
    }

    /**
     * clear method should clear the list of terms in the list
     */
    public void clear() {
        numTermsLinked.clear();
    }

    /**
     * addTerm Method which should add a term object to the polynomial list and checks
     *  if like terms can be combined
     * @param term
     */
    public void addTerm(Term term) {

        boolean comparedTerm = false;

        for (int i = 0; i < numTermsLinked.size(); i++) {

            if(numTermsLinked.get(i).getExponent() == term.getExponent()) { //if exponents are equal, combine like terms
                comparedTerm = true;
                numTermsLinked.get(i).setCoefficient(term.getCoefficient() + numTermsLinked.get(i).getCoefficient()); //sets coefficient of term at index i to the sum of term's coefficient and numTerm at index i

                if(numTermsLinked.get(i).getCoefficient() == 0) { //removes term if coefficient is 0
                    numTermsLinked.remove(i);
                }
            }
        }
        if(!comparedTerm) {
            numTermsLinked.add(term);
        }

        Collections.sort(numTermsLinked);
        Collections.reverse(numTermsLinked);
    }

    /**
     * method adds addedPolynomial polynomial object to another polynomial
     */
    public PolynomialLinkedList add(PolynomialLinkedList addedPolynomial) {
        PolynomialLinkedList original = new PolynomialLinkedList(this);

        for (int i = 0; i < addedPolynomial.getNumTermsLinked(); i++) {
            this.addTerm(addedPolynomial.getTermLinked(i));
        }
        return original;
    }
}