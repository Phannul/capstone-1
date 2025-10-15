import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Al scanner prompt to collect users' menu choice
        Scanner userMenuInput = new Scanner(System.in);

        //A while loop to run the menu window until it's exited
        boolean running = true;
        while(running){
            System.out.println("What do you want to do today?\n D) Add deposit \n P) Make Payment \n L) Ledger \n X) Exit");
            String choice = userMenuInput.nextLine().trim();
            if(choice.equalsIgnoreCase("D")){
                System.out.println("Deposit method");
            } else if (choice.equalsIgnoreCase("P")) {
                System.out.println("Payment Method");

            } else if (choice.equalsIgnoreCase("L")) {
                Ledger ledger = new Ledger();
                ledger.ledgerMenuPrompter(userMenuInput);
                //the ledger screen
            } else if (choice.equalsIgnoreCase("X")) {
                System.out.println("You've chosen to exit the program, Good Bye! ");
                running = false;
            }
            // error statement for invalid or no entries
            else {
                System.err.println("invalid or empty selection detected");
            }

        }


    }
}
