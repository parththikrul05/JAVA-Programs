import java.util.Scanner;

// Abstract class
abstract class Payment {
    protected double amount;
    public Payment(double amount) {
        this.amount = amount;
    }
    // Abstract method with no body
    abstract void processPayment();
}

// Subclass 1: Credit Card
class CreditCardPayment extends Payment {
    private String cardNumber;
    public CreditCardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }
    // Implementation without @Override annotation
    void processPayment() {
        System.out.println("\n-Transaction Details-");
        System.out.println("Processing Credit Card Payment..");
        System.out.println("Amount: $" + amount + " charged to Card: " + cardNumber);
    }
}

// Subclass 2: UPI
class UPIPayment extends Payment {
    private String upiId;

    public UPIPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    void processPayment() {
        System.out.println("\n-Transaction Details-");
        System.out.println("Processing UPI Payment...");
        System.out.println("Amount: $" + amount + " sent to UPI ID: " + upiId);
    }
}

// Main class to run the Payment program
public class PaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Payment payment = null;

        System.out.println(" Payment Gateway ");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        System.out.print("Choose payment method (1 or 2): ");
        int choice = scanner.nextInt();

        System.out.print("Enter payment amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        if (choice == 1) {
            System.out.print("Enter 16-digit Credit Card Number: ");
            String cardNumber = scanner.nextLine();
            payment = new CreditCardPayment(amount, cardNumber);
        } else if (choice == 2) {
            System.out.print("Enter UPI ID (user@bank): ");
            String upiId = scanner.nextLine();
            payment = new UPIPayment(amount, upiId);
        } else {
            System.out.println("Invalid choice!");
        }

        // Process the payment if a valid choice was made
        if (payment != null) {
            payment.processPayment();
        }
        scanner.close();
    }
}
