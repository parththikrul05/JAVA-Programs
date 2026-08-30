import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

public class AtmPinVerification {

    // The 'throws' keyword warns the compiler that this method can throw an exception
    public static void verifyPin(int enteredPin) throws InvalidPinException {
        int correctPin = 1234; // Example correct PIN

        if (enteredPin != correctPin) {
            // The 'throw' keyword actually creates and throws the error
            throw new InvalidPinException("The PIN you entered is invalid.");
        }
        
        System.out.println("PIN verified successfully. You may now access your account.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- ATM Authentication ---");
        System.out.print("Please enter your 4-digit PIN: ");

        try {
            int pin = scanner.nextInt();
            
            // Calling the method that might throw an exception
            verifyPin(pin);
            
        } catch (InvalidPinException e) {
            // Catching our custom PIN error
            System.out.println("Authentication Failed: " + e.getMessage());
        } catch (InputMismatchException e) {
            // Catching if the user types letters instead of numbers
            System.out.println("Authentication Failed: PIN must contain numbers only.");
        } finally {
            // This meets your requirement to display a specific completion message
            scanner.close();
            System.out.println("The verification process has completed.");
        }
    }
}