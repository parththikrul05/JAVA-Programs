import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double accountBalance = 5000.00;

        System.out.println("--- Welcome to the ATM ---");
        System.out.println("Current Balance: ₹" + accountBalance);
        System.out.print("Enter the amount to withdraw: ₹");

        try {
            // Taking input from the user
            double withdrawalAmount = scanner.nextDouble();

            // Checking for invalid amounts
            if (withdrawalAmount <= 0) {
                throw new IllegalArgumentException("Invalid amount. You must enter a value greater than zero.");
            }
            if (withdrawalAmount > accountBalance) {
                throw new IllegalArgumentException("Insufficient balance for this transaction.");
            }

            // Processing valid withdrawal
            accountBalance -= withdrawalAmount;
            System.out.println("Please collect your cash: ₹" + withdrawalAmount);
            System.out.println("Remaining Balance: ₹" + accountBalance);

        } catch (IllegalArgumentException e) {
            // Catches the logical errors we threw above
            System.out.println("Transaction Failed: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Catches cases where the user types letters or symbols instead of a number
            System.out.println("Transaction Failed: Please enter a valid numerical amount.");
        } finally {
            scanner.close();
            System.out.println("Thank you for using our ATM.");
        }
    }
}