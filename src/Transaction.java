
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String username;
    private String stockSymbol;
    private String type;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;

    public Transaction(String username, String stockSymbol,
                       String type, int quantity, double price) {

        this.username = username;
        this.stockSymbol = stockSymbol;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    public void displayTransaction() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Username: " + username);
        System.out.println("Stock: " + stockSymbol);
        System.out.println("Type: " + type);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price per share: Rs. " + price);
        System.out.println("Total amount: Rs. " + (quantity * price));
        System.out.println(
                "Date and Time: " + dateTime.format(formatter)
        );
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {

        Transaction transaction = new Transaction(
                "Vishal",
                "TCS",
                "BUY",
                2,
                3500.0
        );

        transaction.displayTransaction();
    }
}

