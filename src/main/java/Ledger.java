import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    private ArrayList<Transaction> transactionLists;

    public Ledger() {
        this.transactionLists = new ArrayList<>();
    }
    public void addTransaction(Transaction t) {
        transactionLists.add(t);
        // commands deposit and payment
    }
    // shows every transaction
    public void showEverything(){
        //a conditional statement to notify users if the list is empty
        if (transactionLists.isEmpty()){
            System.out.println("No Transaction History Found");
            return;
        }
        // Sort the transaction history via date
        transactionLists.sort(
                (t1, t2)-> t2.getDateTime().compareTo(t1.getDateTime())
    );
        // A for loop to iterate through all the objects in the transaction class
        for (Transaction t : transactionLists) {
            System.out.printf("%s %s %.2f"+"$"+"%s %s", t.getDate(), t.getCurrentTime(), t.getAmount(), t.getVendor(), t.getDescription());
        }


    }
    public void showDeposits(){
        System.out.println("showing all Deposits ...");
        // shows deposited lists
    }
    public void showPayments(){
        System.out.println("showing all Payments...");
        //shows only payment or credited amounts
    }
    public void showReports(){
        System.out.println("showing the reports...");
        //shows reports

    }
    public void ledgerMenuPrompter(Scanner userMenuInput){
        boolean running = true;


        while(running){
            System.out.println("A) ALL\nD) Depostis\nP) Payments \nR) Reports\nH) Home ");
            String choice = userMenuInput.nextLine();
            if(choice.equalsIgnoreCase("A")){
                showEverything();
            } else if (choice.equalsIgnoreCase("D")) {
                showDeposits();
            } else if (choice.equalsIgnoreCase("P")) {
                showPayments();
            } else if (choice.equalsIgnoreCase("R")) {
                showReports();
            }else if (choice.equalsIgnoreCase("H")){
                running = false;
            }else {
                System.err.println("Invalid selection or empty entry");
            }


        }
    }

}
