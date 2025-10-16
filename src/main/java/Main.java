import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    //An array list to hold all the transactions
    private static ArrayList<Transaction> transactionList;
    // A scanner to prompt the user to input their information
    static Scanner myScanner = new Scanner(System.in);
    // The path of the csv file declared as a field to grant access to every method
    static String path = "src/main/resources/transactions.csv";
    // The needed print structure declared in a class level to be called from any needed method
    public static void printTransactions() {

        for (Transaction transaction : transactionList) {

            System.out.printf("%s %s %s %s %.2f \n", transaction.getDate(), transaction.getCurrentTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
        }
    }



    public static void main(String[] args) {

        transactionList = readTransactionFile(path);
        mainMenu();



    }

    public static void mainMenu() {
        boolean working = true;
        //while loop to iterate the main menu until it's exited
        while(working){
            String prompt = """
                Enter a choice:
                D) Deposit
                P) Payment
                L) Ledger menu
                X)
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

    public static void makeTransactions(boolean isPositive) {
        System.out.println("Enter date:");
        String userInput = myScanner.nextLine();
        LocalDate date = LocalDate.parse(userInput);
        System.out.println("Enter time:");
        userInput = myScanner.nextLine();
        LocalTime time = LocalTime.parse(userInput);
        System.out.println("Enter description");
        String description = myScanner.nextLine();
        System.out.println("Enter vendor");
        String vendor = myScanner.nextLine();
        System.out.println("Enter amount");
        userInput = myScanner.nextLine();
        double amount = Double.parseDouble(userInput);
        if (!isPositive){
            amount = amount * -1;
        }

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactionList.add(newTransaction);



        //writeTransactionFile();
        try(FileWriter writer = new FileWriter(path, true)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String tranString = String.valueOf(date) + "|" + String.valueOf(time) + "|" + description + "|" + vendor + "|" + amount;
            bufferedWriter.write(tranString);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void ledgerMenuPrompter(Scanner myScanner) {


        boolean running = true;
        while (running) {
            System.out.println("A) ALL\nD) Deposits\nP) Payments \nR) Reports\nH) Home ");
            String choice = myScanner.nextLine();
            if (choice.equalsIgnoreCase("A")) {
                printTransactions();
            } else if (choice.equalsIgnoreCase("D")) {
                System.out.println("showDeposits");
            } else if (choice.equalsIgnoreCase("P")) {
                System.out.println("showPayments");
            } else if (choice.equalsIgnoreCase("R")) {
//                showReports();
            } else if (choice.equalsIgnoreCase("H")) {
                running = false;

            } else {
                System.err.println("Invalid selection or empty entry");
            }


        }
    }


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
            System.err.println("Error2: " + e);
        }
        return transactionList;

    }


}
