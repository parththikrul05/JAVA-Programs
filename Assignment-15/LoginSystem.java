import java.util.Scanner;

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

public class LoginSystem {

    // The 'throws' keyword indicates this method might throw an InvalidPasswordException
    public static void authenticateUser(String username, String password) throws InvalidPasswordException {
        String correctUsername = "admin";
        String correctPassword = "Pass123";

        // Let's assume the username is correct for this demonstration
        if (username.equals(correctUsername)) {
            if (!password.equals(correctPassword)) {
                // The 'throw' keyword is used to manually trigger the exception
                throw new InvalidPasswordException("Incorrect password! Access Denied.");
            }
            System.out.println("Login successful. Welcome, " + username + "!");
        } else {
            System.out.println("Username not found in the system.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- System Login ---");
        
        System.out.print("Enter Username: ");
        String user = scanner.nextLine();
        
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        try {
            // Attempting to log in
            authenticateUser(user, pass);
        } catch (InvalidPasswordException e) {
            // Catching the custom exception thrown above
            System.out.println("Error: " + e.getMessage());
        } finally {
            // The 'finally' block always executes, even if an exception occurs
            scanner.close();
            System.out.println("Closing the login portal...");
        }
    }
}