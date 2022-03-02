import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class to create, edit, and display polynomials
 */

public class PolynomialDriver {
    public static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Polynomial> polynomial1 = new ArrayList<>();
        ArrayList<Polynomial> polynomial2 = new ArrayList<>();
        Polynomial sumOfPolynomials;
        int menuChoice, subMenuChoice, polynomialSelection, polynomial1Selection, polynomial2Selection;

        //main menu switch case
        do { //dowhile loop will run the menu until the user input is any of the valid options besides 4 which exits the program
            System.out.println("Polynomial 1: " + polynomial1);
            System.out.println("Polynomial 2: " + polynomial2);
            System.out.println("1)Edit Polynomial 1\n2)Edit Polynomial 2\n3)Combine Polynomial 1 & 2\n4) Exit Program");

            menuChoice = userInput.nextInt();
            switch (menuChoice) {
                case 1: //Editing polynomial 1
                    System.out.println("Polynomial 1: " + polynomial1);

                    System.out.println("1)Create A Polynomial\n2)Add a term to the Polynomial\n3)Clear Polynomial");
                    subMenuChoice = userInput.nextInt();
                    switch (subMenuChoice) {
                        case 1: //create a polynomial and add it to the polynomial arraylist
                            Polynomial p1 = new Polynomial();
                            polynomial1.add(p1);
                            addTermToPolynomial(p1);
                            break;
                        case 2: //add terms to the selected polynomial from the arraylist
                            System.out.println("Choose which polynomial you wish to add to");
                            if(polynomial1.size() > 0) {
                                System.out.println(polynomial1);
                                polynomialSelection = userInput.nextInt() - 1;//because indexing starts at 0
                                userInput.nextLine();
                                addTermToPolynomial(polynomial1.get(polynomialSelection));
                            } else {
                                System.out.println("No Polynomials within the array");
                            }
                            break;
                        case 3: //clear polynomial arraylist
                            polynomial1.clear();
                            break;
                        default:
                            System.out.println("Invalid input, please enter a number between 1-3");
                    }
                    break;
                case 2: //editing polynomial 2
                    System.out.println("Polynomial 2: " + polynomial2);

                    System.out.println("1)Create A Polynomial\n2)Add a term to the Polynomial\n3)Clear Polynomial");
                    subMenuChoice = userInput.nextInt();
                    switch (subMenuChoice) {
                        case 1: //create a polynomial and add it to the polynomial arraylist
                            Polynomial p2 = new Polynomial();
                            polynomial2.add(p2);
                            addTermToPolynomial(p2);
                            break;
                        case 2: //add terms to the selected polynomial from the arraylist
                            System.out.println("Choose which polynomial you wish to add to");
                            if(polynomial2.size() > 0) {
                                System.out.println(polynomial2);
                                polynomialSelection = userInput.nextInt() - 1;
                                userInput.nextLine();
                                addTermToPolynomial(polynomial2.get(polynomialSelection));
                            } else {
                                System.out.println("No Polynomials within the array");
                            }
                            break;
                        case 3: //clear polynomial arraylist
                            polynomial2.clear();
                            break;
                        default:
                            System.out.println("Invalid input, please enter a number between 1-3");
                    }
                    break;
                case 3: //combining a polynomial from 1 and a polynomial from 2

                    System.out.println("Choose a polynomial from polynomial1: " + polynomial1);
                    polynomial1Selection = userInput.nextInt() - 1;
                    Polynomial placeHolder1 = new Polynomial(polynomial1.get(polynomial1Selection)); //makes a copy of the selected polynomial in poly1

                    System.out.println("Choose a polynomial from polynomial2: " + polynomial2);
                    polynomial2Selection = userInput.nextInt() - 1;
                    Polynomial placeHolder2 = new Polynomial(polynomial2.get(polynomial2Selection));


                    placeHolder1.add(placeHolder2);
                    System.out.println("Combined Polynomial: " + placeHolder1);

                    break;
                case 4:
                    System.out.println("Exiting program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please enter a number between 1-4");
            }
        } while (menuChoice != 4); //if menuChoice = 4, the menu loop will end
    }

    /**
     * 
     * @param p
     */
    public static void addTermToPolynomial(Polynomial p) {
        String userTerm;
        System.out.println("Enter in a term");
        userTerm = userInput.next();
        p.addTerm(new Term(userTerm));
    }
}
//What am i doing????: Need to create a driver that allows a user to edit poly1, edit poly2, sum both polys, exit program
//needs to be a do while loop so that the program runs until the user exits it via option 4
//need an inner menu for options 1 and 2 to create/edit/clear each of the polynomials
//Need to make the following methods: addTermToPolynomial