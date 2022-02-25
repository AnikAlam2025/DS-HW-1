import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
    ArrayList<Term> numTerms;


    /**
     * Full Constructor method to create a polynomial object
     */
    public Polynomial() {
        numTerms = new ArrayList<>();
    }

    /**
     * Copy Constructor method which should make a copy of a Polynomial object
     * @param original
     */
    public Polynomial(Polynomial original) {
        numTerms = new ArrayList<>();


        for (int i = 0; i < original.getNumTerms(); i++) {
            this.addTerm(new Term(original.getTerm(i)));
        }
    }

    //Getters
    public int getNumTerms() {
        return numTerms.size();
    }

    public Term getTerm(int i) {
        return numTerms.get(i);
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
        if(numTerms.size() == 0) {
            return "0";
        }

        for(int i = 0; i < numTerms.size(); i++) {
            coefficient = Integer.toString(numTerms.get(i).getCoefficient()); //the coefficient of numTerms at i is taken and wrapped by Integer class
            exponent = Integer.toString(numTerms.get(i).getExponent());//the exponent of numTerms at i is taken and wrapped by Integer class


            //exponent check; if 0 then the exponent has no value, if 1 then the exponent is simply the variable, if else then it is the variable + exponent
            if (numTerms.get(i).getExponent() == 0) {
                exponent = "";
            } else if (numTerms.get(i).getExponent() == 1) {
                exponent = "x";
            } else {
                exponent = "x^" + exponent;
            }

            //coefficient check
            if (numTerms.get(i).getCoefficient() == 0) {//if the term at i has a coefficient = 0, a blank space is returned indicating absence of a coefficient
                return "";
            } else if (numTerms.get(i).getCoefficient() == 1) { //if = 1, returns the coefficient of that term with an addition symbol afterwards
                coefficient = ((numTerms.get(i).getCoefficient() == 1)) + "+";
            } else if (numTerms.get(i).getCoefficient() == -1) { //if = -1, returns the coefficient with a negative symbol to indicate the value is negative
                coefficient = ((numTerms.get(i).getCoefficient() == -1)) + "-";
            } else if(numTerms.get(i).getCoefficient() > 0) { //any positive value above 1 will return an addition symbol with the coefficient
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
        numTerms.clear();
    }

    /**
     * addTerm Method which should add a term object to the polynomial list and checks
     *  if like terms can be combined
     * @param term
     */
    public void addTerm(Term term) {
        boolean comparedTerm = false;
        for (int i = 0; i < numTerms.size(); i++) {
            if(numTerms.get(i).getExponent() == term.getExponent()) { //if exponents are equal, combine like terms
                comparedTerm = true;
                numTerms.get(i).setCoefficient(term.getCoefficient() + numTerms.get(i).getCoefficient()); //sets coefficient of term at index i to the sum of term's coefficient and numTerm at index i

                if(numTerms.get(i).getCoefficient() == 0) { //removes term if coefficient is 0
                    numTerms.remove(i);
                }
            }
        }
        if(!comparedTerm) {
            numTerms.add(term);
        }
        Collections.sort(numTerms);
        Collections.reverse(numTerms);
    }

    /**
     * method adds addedPolynomial polynomial object to another polynomial
     */
    public Polynomial add(Polynomial addedPolynomial) {
        Polynomial original = new Polynomial(this);

        for (int i = 0; i < addedPolynomial.getNumTerms(); i++) {
            this.addTerm(addedPolynomial.getTerm(i));
        }
        return original;
    }
}
