
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final List<Stock> stocks = new ArrayList<>();
    private static final List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        User user = new User("Vishal", 10000);

        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELI", "Reliance Industries", 2500));
        stocks.add(new Stock("WIPRO", "Wipro", 600));

        int choice = 0;

        do {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Available Stocks");
            System.out.println("2. View Balance");
            System.out.println("3. Buy Stock");
            System.out.println("4. Sell Stock");
            System.out.println("5. View Portfolio");
            System.out.println("6. View Transaction History");
            System.out.println("7. Add Money");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewStocks();
                    break;

                case 2:
                    System.out.println(
                            "Available Balance: Rs. " + user.getBalance()
                    );
                    break;

                case 3:
                    buyStock(user);
                    break;

                case 4:
                    sellStock(user);
                    break;

                case 5:
                    user.displayPortfolio();
                    break;

                case 6:
                    viewTransactions();
                    break;

                case 7:
                    addMoney(user);
                    break;

                case 8:
                    System.out.println("Thank you for using Stock Trading Platform!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 8);

        sc.close();
    }

    private static void viewStocks() {
        System.out.println("\n--- AVAILABLE STOCKS ---");

        for (Stock stock : stocks) {
            stock.displayStock();
        }
    }

    private static Stock findStock(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }

    private static void buyStock(User user) {
        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid quantity.");
            sc.nextLine();
            return;
        }

        int quantity = sc.nextInt();

        if (user.buyStock(stock, quantity)) {
            transactions.add(new Transaction(
                    user.getUsername(),
                    stock.getSymbol(),
                    "BUY",
                    quantity,
                    stock.getPrice()
            ));
        }
    }

    private static void sellStock(User user) {
        System.out.print("Enter stock symbol: ");
        String symbol = sc.next();

        Stock stock = findStock(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid quantity.");
            sc.nextLine();
            return;
        }

        int quantity = sc.nextInt();

        if (user.sellStock(stock, quantity)) {
            transactions.add(new Transaction(
                    user.getUsername(),
                    stock.getSymbol(),
                    "SELL",
                    quantity,
                    stock.getPrice()
            ));
        }
    }

    private static void viewTransactions() {
        System.out.println("\n--- TRANSACTION HISTORY ---");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    private static void addMoney(User user) {
        System.out.print("Enter amount to add: ");

        if (!sc.hasNextDouble()) {
            System.out.println("Invalid amount.");
            sc.nextLine();
            return;
        }

        double amount = sc.nextDouble();
        user.addMoney(amount);
    }
}

