import java.util.InputMismatchException;
import java.util.Scanner;

// 1. Creating the user-defined exception
class UnderageApplicantException extends Exception {
    public UnderageApplicantException(String message) {
        super(message);
    }
}

public class DrivingLicenseSystem {

    public static void checkLicenseEligibility(int age) throws UnderageApplicantException {
        if (age < 18) {
            // Throwing the exception if age is less than 18
            throw new UnderageApplicantException("Age is below 18. Applicants must be at least 18 years old to apply.");
        }
        // This will only print if no exception is thrown
        System.out.println("Success: You are eligible for a driving license.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- RTO Driving License Portal ---");
        System.out.print("Enter applicant's age: ");

        try {
            int age = scanner.nextInt();
            
            // Check eligibility based on age
            checkLicenseEligibility(age);
            
            // If eligible, we can prompt for the next steps here
            if (age >= 18) {
                System.out.println("Please proceed to counter 3 for your written test.");
            }

        } catch (UnderageApplicantException e) {
            // Catching and displaying our custom exception message
            System.out.println("Application Rejected: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("System Error: Age must be a numerical value.");
        } finally {
            scanner.close();
            System.out.println("Thank you for using the RTO portal.");
        }
    }
}