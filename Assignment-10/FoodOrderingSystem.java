import java.util.Scanner;

// Abstract class
abstract class FoodOrder {
    protected double baseAmount;
    public FoodOrder(double baseAmount) {
        this.baseAmount = baseAmount;
    }
    // Abstract method
    abstract void calculateBill();
}

// Subclass 1: Dine-In (Adds a 10% service charge)
class DineInOrder extends FoodOrder {
    public DineInOrder(double baseAmount) {
        super(baseAmount);
    }
    void calculateBill() {
        double serviceCharge = baseAmount * 0.10;
        double totalBill = baseAmount + serviceCharge;
        System.out.println("\n-Dine-In Order Receipt-");
        System.out.println("Base Amount: $" + baseAmount);
        System.out.println("Service Charge (10%): $" + serviceCharge);
        System.out.println("Total Bill: $" + totalBill);
    }
}

// Subclass 2: Take-Away (Adds a flat $5 packaging fee)
class TakeAwayOrder extends FoodOrder {
    private double packagingFee = 5.00;
    public TakeAwayOrder(double baseAmount) {
        super(baseAmount);
    }
    void calculateBill() {
        double totalBill = baseAmount + packagingFee;
        System.out.println("\n-Take-Away Order Receipt-");
        System.out.println("Base Amount: $" + baseAmount);
        System.out.println("Packaging Fee: $" + packagingFee);
        System.out.println("Total Bill: $" + totalBill);
    }
}

// Main class to run the Food Order program
public class FoodOrderingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FoodOrder order = null;

        System.out.println("Food Ordering System");
        System.out.println("1. Dine-In");
        System.out.println("2. Take-Away");
        System.out.print("Choose order type (1 or 2): ");
        int choice = scanner.nextInt();

        System.out.print("Enter the base amount of the food: $");
        double baseAmount = scanner.nextDouble();

        if (choice == 1) {
            order = new DineInOrder(baseAmount);
        } else if (choice == 2) {
            order = new TakeAwayOrder(baseAmount);
        } else {
            System.out.println("Invalid choice!");
        }

        // Calculate and display the bill if a valid choice was made
        if (order != null) {
            order.calculateBill();
        }
        scanner.close();
    }
}