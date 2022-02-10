
public class Polynomial {
    private int numTerms;

    //copy constructor
    public Polynomial(Polynomial original) {
        if (original == null) {
            System.out.println("Invalid data given to polynomial");
            System.exit(0);
        } else {
            this.setAll(original.numTerms);
        }
    }

    private void setAll(int numTerms) {
        this.setNumTerms(numTerms);
    }

    //default constructor
    public Polynomial() {

    }

    public void setNumTerms(int numTerms) {
        this.numTerms = numTerms;
    }

    public int getNumTerms() {
        return this.numTerms;
    }

    public Term getTerm(int i) {
        return null;
    }

    public void addTerm(Term term) {
    }

    public void clear() {
    }

    public void add(Polynomial test2) {
    }


}
