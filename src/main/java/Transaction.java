import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    //private fields for encapsulation
    private LocalDate date;
    private LocalTime currentTime;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime currentTime, String description, String vendor, double amount) {
        this.date = date;
        this.currentTime = currentTime;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

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
    public String toString () {
        return date + "|" + currentTime + "|" + description + "|" + vendor +"|" + amount ;
    }
}

