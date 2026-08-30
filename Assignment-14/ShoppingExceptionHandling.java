import java.util.InputMismatchException;
import java.util.Scanner;

public class ShoppingExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Online Shopping Cart ---");

        try {
            // 1. Taking the product name as input
            System.out.print("Enter the name of the product: ");
            String productName = scanner.nextLine();

            // 2. Taking the product price as input
            System.out.print("Enter the price of '" + productName + "': ");
            double productPrice = scanner.nextDouble();
            
            // Optional: Handle negative pricing
            if (productPrice < 0) {
                throw new IllegalArgumentException("Product price cannot be negative.");
            }

            // 3. Taking the quantity as input
            System.out.print("Enter the quantity to add: ");
            int quantity = scanner.nextInt();

            // Checking for invalid quantity as per requirements
            if (quantity <= 0) {
                throw new IllegalArgumentException("Invalid product quantity. Quantity must be 1 or more.");
            }

            // Calculating the total price
            double totalPrice = productPrice * quantity;

            // Displaying the final receipt/summary
            System.out.println("\n--- Cart Summary ---");
            System.out.println("Product     : " + productName);
            System.out.println("Unit Price  : ₹" + productPrice);
            System.out.println("Quantity    : " + quantity);
            System.out.println("--------------------");
            System.out.println("Total Price : ₹" + totalPrice);
            System.out.println("Success! Item(s) added to your cart.");

        } catch (IllegalArgumentException e) {
            // Catches the zero or negative quantity/price error
            System.out.println("\nCart Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Catches cases where the user types letters instead of numbers for price/quantity
            System.out.println("\nCart Error: Please enter valid numerical values for price and quantity.");
        } finally {
            scanner.close();
            System.out.println("\nReturning to main menu...");
        }
    }
}