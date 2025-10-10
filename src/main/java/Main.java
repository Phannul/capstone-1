import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //a scanner prompt to collect users' menu choice
        Scanner userMenu = new Scanner(System.in);


        boolean running = true;
        while(running){
            System.out.println("What do you want to do today?\n D) Add deposit \n P) Make Payment \n L) Ledger \n X) Exit");
            String choice = userMenu.nextLine().trim();
            if(choice.equalsIgnoreCase("D")){
                System.out.println("Deposit method");
            } else if (choice.equalsIgnoreCase("P")) {
                System.out.println("Payment Method");

            } else if (choice.equalsIgnoreCase("L")) {
                System.out.println("ledger method");
            } else if (choice.equalsIgnoreCase("X")) {
                running = false;
            }else {
                System.err.println("invalid or empty selection detected");
                continue;

            }
        }


    }
}
