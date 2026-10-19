
public class Stock {

    private String symbol;
    private String name;
    private double price;

    // Constructor
    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Update stock price
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    // Display stock details
    public void displayStock() {
        System.out.println(
                symbol + " | " + name + " | ₹" + price
        );
    }

    // Main method
    public static void main(String[] args) {

        Stock stock = new Stock(
                "TCS",
                "Tata Consultancy Services",
                3500.0
        );

        System.out.println("Stock Details:");
        stock.displayStock();
    }
}

