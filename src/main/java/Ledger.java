import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    private ArrayList<Transaction> transactionList;

    public Ledger() {
        this.transactionList = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
        // commands deposit and payment
    }

    // shows every transaction
    public void showEverything() {
        //a conditional statement to notify users if the list is empty
//        if (transactionList.isEmpty()) {
//            System.out.println("No Transaction History Found");
//            return;
//        }

        // A for loop to iterate through all the objects in the transaction class
//        for (Transaction t : transactionList) {
//            System.out.printf("%s %s %.2f" + "$" + "%s %s \n", t.getDate(), t.getCurrentTime(), t.getAmount(), t.getVendor(), t.getDescription());
//        }


    }

    // shows deposited lists
    public void showDeposits() {
        System.out.println("showing all Deposits ...");
        // A conditional statement to notify the user they have not deposited any money yet
        if (transactionList.isEmpty()) {
            System.out.println("No Recorded Deposits yet");
            return;
        }
        //Sorts deposits in a timely manner

        // Filters only positive transactions so that it can only show deposited amount
        for (Transaction t : transactionList) {
            if (t.getAmount() > 0) {
                System.out.println(t.toString());
            } else {
                System.out.println(" No Recorded Deposits yet");
            }
        }
    }

    //shows only payment or credited amounts
    public void showPayments() {
        // a conditional statement to address an empty transaction list
        if (transactionList.isEmpty()) {
            System.out.println("There are No payments credited");
        }
//        transactionList.sort(
//                (t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime())
//        );
        for (Transaction t : transactionList) {
            if (t.getAmount() < 0) {
                System.out.println(t.toString());
            }
        }

    }

    //shows reports of the desired timeline
    public void showReports(Scanner userMenuInput) {
        boolean running = true;
        while (running) {
            System.out.println("1) Month To Date \n 2) Previous Month \n 3) Year to Date \n 4) Previous Year \n 5) Search by Vendor \n 0) Back");
            String choice = userMenuInput.nextLine();
            int menuChoice = Integer.parseInt(choice);
            // Switch statemn
            switch (menuChoice) {
                case 1:
                    showMonthToDate();
                    break;
                case 2:
                    showPreviousMonths();
                    break;
                case 3:
                    showYearToDate();
                    break;
                case 4:
                    //showPreviousYear();
                    break;
                case 5:
//                    searchByVendorName();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.err.println("Invalid Selection or Empty Entry");
            }
        }
    }



//   private void showPreviousYear() {
//       for(Transaction transaction : transactionList) {
//           LocalDate today = LocalDate.now();
//           int currentYear = today.getYear();
//           int lastYear = currentYear - 1;
//
//           String transactionDate = transaction.getDate();
//           int transactionYear = transactionDate.getYear();
//
//           if(transactionYear == lastYear) {
//               System.out.printf("%s %s %s %s %.2f", transaction.getDate(), transaction.getCurrentTime(), transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
//           }
//       }
//   }

    private void showYearToDate() {

    }

    private void showPreviousMonths() {
        for(Transaction t : transactionList) {

            System.out.printf("%s %s %s %s %.2f", t.getDate(), t.getCurrentTime(), t.getVendor(), t.getDescription(),t.getAmount() );
        }
    }

    public void showMonthToDate(){

    }



}
