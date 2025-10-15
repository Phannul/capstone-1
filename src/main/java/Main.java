import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static ArrayList<Transaction> transactionList;
    static Scanner myScanner = new Scanner(System.in);
    static String path = "src/main/resources/transactions.csv";

    public static void main(String[] args) {
        transactionList = readTransactionFile(path);
        printTransactions();

    }

    public static void mainMenu() {
        String prompt = """
                Enter a choice:
                D) Deposit
                P) Payment
                L) Ledger menu
                X)
                """;
        System.out.println(prompt);
        String userInput = myScanner.nextLine();

        switch (userInput) {
            case "D":
                enterDeposit();
                break;
            case "X":
                break;
            default:
                System.out.println("invalid entry");
        }
    }

    public static void enterDeposit() {
        System.out.println("Enter date:");
        String userInput = myScanner.nextLine();
        LocalDate date = LocalDate.parse(userInput);
        System.out.println("Enter time:");
        userInput = myScanner.nextLine();
        LocalTime time = LocalTime.parse(userInput);
        System.out.println("Enter description");
        userInput = myScanner.nextLine();
        System.out.println("Enter vendor");
        userInput = myScanner.nextLine();
        System.out.println("Enter amount");
        userInput = myScanner.nextLine();
        double amount = Double.parseDouble(userInput);





        //blah
        writeTransactionFile();
    }

    public static void writeTransactionFile(String path) {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path)));
    }





    public static ArrayList<Transaction> readTransactionFile(String path) {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();
            String input;
            while((input = reader.readLine()) != null) {
                String[] section = input.split("\\|");
                LocalDate date = LocalDate.parse( section[0]);
                LocalTime time = LocalTime.parse(section[1]);
                String description = section[2];
                String vendor = section[3];
                double amount = Double.parseDouble (section[4]);
                Transaction transactions = new Transaction(date, time, description, vendor, amount);
                transactionList.add(transactions);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        return transactionList;
    }

    public static void printTransactions(){

        for(Transaction transaction : transactionList) {

            System.out.printf("%s %s %s %s %.2f \n", transaction.getDate(), transaction.getCurrentTime(), transaction.getDescription(),transaction.getVendor(),transaction.getAmount() );
        }
    }
}
