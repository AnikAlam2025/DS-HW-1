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
     * clear method should clear the list of terms
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
     * method adds test 2 polynomial object to another polynomial
     */
    public Polynomial add(Polynomial addedPolynomial) {
        Polynomial original = new Polynomial(this);

        for (int i = 0; i < addedPolynomial.getNumTerms(); i++) {
            this.addTerm(addedPolynomial.getTerm(i));
        }
        return original;
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

        for (Term numTerm : numTerms) {
            coefficient = Integer.toString(numTerm.getCoefficient()); //coefficient of term at index i
            exponent = Integer.toString(numTerm.getExponent());//exponent of term at index i

            //coefficient check
            if (numTerm.getCoefficient() == 0) {
                return "";
            } else if (numTerm.getCoefficient() == 1) {
                coefficient = ((numTerm.getCoefficient() == 1)) + "+";
            } else if (numTerm.getCoefficient() == -1) {
                coefficient = ((numTerm.getCoefficient() == -1)) + "-";
            } else if (numTerm.getCoefficient() > 1) {
                coefficient = coefficient;
            }

            //exponent check
            if (numTerm.getExponent() == 0) {
                exponent = "";
            } else if (numTerm.getExponent() == 1) {
                exponent = "x";
            } else {
                exponent = "x^" + exponent;
            }
            finalPolynomial += coefficient + exponent;
        }
        return finalPolynomial;
    }
}
