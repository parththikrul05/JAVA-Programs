import java.util.Scanner;

// 1. Final Class: This class cannot be extended by any other class
final class BankAcc {
    
    // 2. Final Variable: The account number cannot be changed once assigned
    private final String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor to initialize the final variable
    public BankAcc(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // 3. Final Method: This method could never be overridden
    public final void displayAccountDetails() {
        System.out.println("\n--- Bank Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + balance);
        System.out.println("----------------------------\n");
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input from the user
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String accHolder = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();

        // Creating object with user input
        BankAcc myAccount = new BankAcc(accNum, accHolder, balance);
        
        // Display details
        myAccount.displayAccountDetails();

        scanner.close();
    }
}

