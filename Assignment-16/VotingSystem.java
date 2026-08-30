import java.util.InputMismatchException;
import java.util.Scanner;

// 1. Creating the user-defined exception by extending Exception
class InvalidVoterAgeException extends Exception {
    public InvalidVoterAgeException(String message) {
        super(message);
    }
}

public class VotingSystem {

    // Method that declares it might throw our custom exception
    public static void checkVotingEligibility(int age) throws InvalidVoterAgeException {
        if (age < 18) {
            // Throwing the user-defined exception with a custom message
            throw new InvalidVoterAgeException("Age is below 18. You are not eligible to vote in this election.");
        }
        System.out.println("Age verified. You are eligible to vote. Please proceed to the booth.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Election Voting Registration ---");
        System.out.print("Please enter your age: ");

        try {
            int age = scanner.nextInt();
            
            // Checking the age
            checkVotingEligibility(age);
            
        } catch (InvalidVoterAgeException e) {
            // Handling our specific user-defined exception
            System.out.println("Registration Denied: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid whole number for age.");
        } finally {
            scanner.close();
            System.out.println("Voting system closed.");
        }
    }
}