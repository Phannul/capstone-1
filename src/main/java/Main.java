import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    //An array list to hold all the transactions
     static ArrayList<Transaction> transactionList;
    // A scanner to prompt the user to input their information
    static Scanner myScanner = new Scanner(System.in);
    // The path of the csv file declared as a field to grant access to every method
    static String path = "src/main/resources/transactions.csv";
    public static void main(String[] args) {
        transactionList = readTransactionFile(path);
        Collections.sort(transactionList);
        mainMenu();
    }
    public static void mainMenu() {
        boolean working = true;
        //while loop to iterate the main menu until it's exited
        while (working) {
            String prompt = """
                    Enter a choice:
                    D) Deposit
                    P) Payment
                    L) Ledger menu
                    X) Exit
                    """;
            System.out.println(prompt);
            String userInput = myScanner.nextLine();

            userInput = userInput.toUpperCase().trim();

            switch (userInput) {
                case "D":
                    makeTransactions(true);
                    break;
                case "P":
                    makeTransactions(false);
                    break;
                case "L":
                    ledgerMenuPrompter(myScanner);
                    break;
                case "X":
                    working = false;
                    System.out.println("You have chosen to exit the menu");
                    break;
                default:
                    System.out.println("invalid entry, Try Again");
            }
        }
    }
    /* A parametrized method that prompts the user to write their transaction information
     and save it into the csv file
     */
    public static void makeTransactions(boolean isDeposit) {
        /* using a scanner to create the prompter that asks the user to input
        the needed information
         */
        System.out.println("Enter date (yyy-MM-dd):");
        String userInput = myScanner.nextLine();
        LocalDate date = LocalDate.parse(userInput);
        System.out.println("Enter time(HH:mm:ss):");
        userInput = myScanner.nextLine();
        LocalTime time = LocalTime.parse(userInput);
        System.out.println("Enter description");
        String description = myScanner.nextLine();
        System.out.println("Enter vendor");
        String vendor = myScanner.nextLine();
        System.out.println("Enter amount");
        userInput = myScanner.nextLine();
        double amount = Double.parseDouble(userInput);
        /* a conditional statement that changes the signs of the amount during payments
        and keeps it positive if it's a deposit
         */
        if (isDeposit == false) {
            amount = amount * -1;
        }
        //A transaction type variable that compiles the user inputs and able to be added into the array list
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactionList.add(newTransaction);
        //A writer that write the entered information into the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            String transactionFormat = String.valueOf(date) + "|" + String.valueOf(time) + "|" + description + "|" + vendor + "|" + amount + "\n";
            writer.write(transactionFormat);
        } catch (Exception e) {
            System.out.println("Error with the writer" + e);
        }
    }
    //A menu prompter for the ledger submenu
    public static void ledgerMenuPrompter(Scanner myScanner) {
        boolean running = true;
        while (running) {
            System.out.println("What do you want to see \nA) All Transactions\nD) Deposits\nP) Payments \nR) Reports\nH) Home ");
            String choice = myScanner.nextLine();
            if (choice.equalsIgnoreCase("A")) {
                printTransactions();
            } else if (choice.equalsIgnoreCase("D")) {
                printDeposits();
            } else if (choice.equalsIgnoreCase("P")) {
                printPayments();
            } else if (choice.equalsIgnoreCase("R")) {
                showReports(myScanner);
            } else if (choice.equalsIgnoreCase("H")) {
                running = false;
            } else {
                System.err.println("Invalid selection or empty entry");
            }
        }
    }
    /* A reader method that reads through each line on the csv file and saves them temporarily as a string  and returns an array list
    and creates a transaction object that populates into an array list
     */
    public static ArrayList<Transaction> readTransactionFile(String path) {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();
            String input;
            while ((input = reader.readLine()) != null) {
                String[] section = input.split("\\|");
                LocalDate date = LocalDate.parse(section[0]);
                LocalTime time = LocalTime.parse(section[1]);
                String description = section[2];
                String vendor = section[3];
                double amount = Double.parseDouble(section[4]);
                Transaction transactions = new Transaction(date, time, description, vendor, amount);
                transactionList.add(transactions);
            }
        } catch (Exception e) {
            System.err.println("Error with the reader: " + e);
        }
        return transactionList;
    }
    // A method that goes through all the elements in the formerly populated array list and print them into the console
    public static void printTransactions() {

        for (Transaction transaction : transactionList) {

            System.out.printf("%s | %s | %s | %s | %.2f \n", transaction.getDate(), transaction.getCurrentTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
    }
    // A method that only prints transactions with positive amount value(deposit)
    public static void printDeposits() {
        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | %.2f \n", transaction.getDate(), transaction.getCurrentTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
    }
    // A method that only prints transactions with negative amount value(payment)
    public static void printPayments() {
        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                System.out.printf("%s | %s | %s | %s | %.2f \n", transaction.getDate(), transaction.getCurrentTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }
    }
    // A menu to allow user the type of report they want to see
    public static void showReports(Scanner myScanner) {
        boolean running = true;
        while (running) {
            System.out.println(" Choose Filter: \n 1) Month To Date \n 2) Previous Month \n 3) Year to Date \n 4) Previous Year \n 5) Search by Vendor \n 0) Back");
            String choice = myScanner.nextLine();
            int menuChoice = Integer.parseInt(choice);
            switch (menuChoice) {
                case 1:
                    showMonthToDate();
                    break;
                case 2:
                    showPreviousMonth();
                    break;
                case 3:
                    showYearToDate();
                    break;
                case 4:
                    showPreviousYear();
                    break;
                case 5:
                    searchByVendorName();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.err.println("Invalid Selection or Empty Entry");
            }
        }
    }
    //shows the reports in the span of current month up to date
    public static void showMonthToDate(){
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();
        System.out.println("Transaction for month " + currentMonth + " till date " + today.getDayOfMonth());

        for (Transaction transaction : transactionList){
            LocalDate transactionDate = transaction.getDate();
            int transactionMonth = transaction.getDate().getMonthValue();
            int transactionYear = transaction.getDate().getYear();

            if(transactionYear == currentYear && transactionMonth == currentMonth){
                if(transactionDate.isBefore(today) || transactionDate.isEqual(today)){
                    System.out.printf("%s | %s | %s | %s | %.2f", transaction.getDate(), transaction.getCurrentTime(), transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
                }
            }
        }

    }
    public static void showPreviousMonth(){
        LocalDate today = LocalDate.now();
        int thisMonth = today.getMonthValue();
        int lastMonth = thisMonth - 1;
        System.out.println("Transaction for Month " + lastMonth);
        for (Transaction transaction : transactionList){
            int transactionMonth = transaction.getDate().getMonthValue();
            if (transactionMonth == lastMonth){
                System.out.printf("%s | %s | %s | %s | %.2f", transaction.getDate(), transaction.getCurrentTime(), transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
            }
        }
    }
    public static void showYearToDate(){
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();
        LocalDate firstDateOfTheYear = LocalDate.of(thisYear, 1 ,1);
        System.out.println("Transaction for the year " + thisYear + " till " + today);
        for (Transaction transaction : transactionList){
            LocalDate transactionYearToDate = transaction.getDate();
            boolean isAfterStartOfTheYear = transactionYearToDate.isAfter(firstDateOfTheYear) || transactionYearToDate.isEqual(firstDateOfTheYear);
            boolean isBeforeTodayOrToday = transactionYearToDate.isBefore(today) || transactionYearToDate.isEqual(today);
            if (isAfterStartOfTheYear && isBeforeTodayOrToday){
                System.out.printf("%s | %s | %s | %s | %.2f \n", transaction.getDate(), transaction.getCurrentTime(), transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
            }
        }
    }
    public static void showPreviousYear() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int lastYear = currentYear - 1;
        System.out.println(" Transaction for" + lastYear);
       for(Transaction transaction : transactionList) {
           int transactionYear = transaction.getDate().getYear();
           if(transactionYear == lastYear) {
               System.out.printf("%s | %s | %s | %s | %.2f", transaction.getDate(), transaction.getCurrentTime(), transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
           }
       }
   }
   public static void searchByVendorName() {
       System.out.println("Enter the vendor's name: ");
       String input = myScanner.nextLine();
       for (Transaction transaction : transactionList){
           String desiredVendor = transaction.getVendor();
           if (input.equalsIgnoreCase(desiredVendor)){
               System.out.printf("%s | %s | %s | %s | %.2f", transaction.getDate(), transaction.getCurrentTime(), transaction.getVendor(), transaction.getDescription(), transaction.getAmount());
           }
       }
   }
}
