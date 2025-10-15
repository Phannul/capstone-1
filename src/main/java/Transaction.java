import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {
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
    // A method to combine the date and time fields together so that it could be easy to compare both of them
    public LocalDateTime getDateTime(){
        return LocalDateTime.of(date, currentTime);
    }
}

