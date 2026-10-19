//```java
import java.util.HashMap;
import java.util.Map;

public class User {

    private String username;
    private double balance;
    private Map<String, Integer> portfolio;

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void addMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Money added successfully.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public boolean buyStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return false;
        }

        double totalCost = stock.getPrice() * quantity;

        if (balance < totalCost) {
            System.out.println("Insufficient balance.");
            return false;
        }

        balance -= totalCost;
        portfolio.put(
                stock.getSymbol(),
                portfolio.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        System.out.println("Stock purchased successfully!");
        return true;
    }

    public boolean sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);

        if (quantity <= 0 || quantity > owned) {
            System.out.println("Invalid quantity or insufficient shares.");
            return false;
        }

        balance += stock.getPrice() * quantity;

        int remaining = owned - quantity;

        if (remaining == 0) {
            portfolio.remove(stock.getSymbol());
        } else {
            portfolio.put(stock.getSymbol(), remaining);
        }

        System.out.println("Stock sold successfully!");
        return true;
    }

    public void displayPortfolio() {
        System.out.println("\nUsername: " + username);
        System.out.println("Balance: Rs. " + balance);
        System.out.println("Portfolio:");

        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
                System.out.println(
                        entry.getKey() + " : " + entry.getValue() + " shares"
                );
            }
        }
    }

    public static void main(String[] args) {
        User user = new User("Vishal", 10000);

        Stock stock = new Stock(
                "TCS",
                "Tata Consultancy Services",
                3500.0
        );

        user.buyStock(stock, 2);
        user.displayPortfolio();
    }
}

