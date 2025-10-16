import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Comparable<Transaction> {
    //private fields for encapsulation
    private LocalDate date;
    private LocalTime currentTime;
    private String description;
    private String vendor;
    private double amount;
    // constructor to build the objects

    public Transaction(LocalDate date, LocalTime currentTime, String description, String vendor, double amount) {
        this.date = date;
        this.currentTime = currentTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    // getters to allow the user access the private fields in an encapsulated manner

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
    // Compiles the entries into the required transaction format
    public String toString () {
        return date + "|" + currentTime + "|" + description + "|" + vendor +"|" + amount ;
    }
    @Override
    // Method to sort the transactions in descending order
    public int compareTo(Transaction otherTransaction) {
        int comparedByDate = this.date.compareTo(otherTransaction.date);
        /* A conditional to make it try to sort them by dates first and if the transactions have
        the same date value it will move forward and compare them by the time they were
        posted
         */
        if (comparedByDate != 0){
            return comparedByDate * -1;
        }else {
            return this.currentTime.compareTo(otherTransaction.currentTime) * -1;
        }
    }

    // A method to combine the date and time fields together so that it could be easy to compare both of them
//    public LocalDateTime getDateTime(){
//       return LocalDateTime.of(returnDate(), returnTime());
//    }

}

