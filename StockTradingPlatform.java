import java.util.HashMap;
import java.util.Scanner;

class Stock {
    String symbol;
    String name;
    double price;

    public Stock(String symbol, String name, double price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    HashMap<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buyStock(String symbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("❌ Error: Insufficient balance!");
            return;
        }
        balance -= cost;
        holdings.put(symbol, holdings.getOrDefault(symbol, 0) + quantity);
        System.out.println("✅ Successfully bought " + quantity + " shares of " + symbol);
    }

    public void sellStock(String symbol, int quantity, double price) {
        if (!holdings.containsKey(symbol) || holdings.get(symbol) < quantity) {
            System.out.println("❌ Error: Not enough shares to sell!");
            return;
        }
        double revenue = quantity * price;
        balance += revenue;
        holdings.put(symbol, holdings.get(symbol) - quantity);
        if (holdings.get(symbol) == 0) {
            holdings.remove(symbol);
        }
        System.out.println("✅ Successfully sold " + quantity + " shares of " + symbol);
    }

    public void displayPortfolio(HashMap<String, Stock> market) {
        System.out.println("\n--- Your Portfolio ---");
        System.out.printf("Available Balance: $%.2f\n", balance);
        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
            return;
        }
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            double currentPrice = market.get(symbol).price;
            System.out.printf("Stock: %-5s | Shares: %-3d | Current Value: $%.2f\n", symbol, qty, (qty * currentPrice));
        }
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Stock> market = new HashMap<>();
        
        market.put("AAPL", new Stock("AAPL", "Apple Inc.", 175.50));
        market.put("TSLA", new Stock("TSLA", "Tesla Inc.", 240.20));
        market.put("GOOG", new Stock("GOOG", "Alphabet Inc.", 135.80));

        Portfolio myPortfolio = new Portfolio(10000.00);
        System.out.println("=== Welcome to Stock Trading Platform ===");

        while (true) {
            System.out.println("\n1. View Market Prices\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 5) {
                System.out.println("Thank you for trading!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- Live Market ---");
                    for (Stock s : market.values()) {
                        System.out.printf("%-5s (%-15s) : $%.2f\n", s.symbol, s.name, s.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter Stock Symbol (AAPL, TSLA, GOOG): ");
                    String buySym = scanner.nextLine().toUpperCase();
                    if (!market.containsKey(buySym)) {
                        System.out.println("Invalid stock symbol!");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int buyQty = scanner.nextInt();
                    myPortfolio.buyStock(buySym, buyQty, market.get(buySym).price);
                    break;
                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSym = scanner.nextLine().toUpperCase();
                    if (!market.containsKey(sellSym)) {
                        System.out.println("Invalid stock symbol!");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int sellQty = scanner.nextInt();
                    myPortfolio.sellStock(sellSym, sellQty, market.get(sellSym).price);
                    break;
                case 4:
                    myPortfolio.displayPortfolio(market);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }
}