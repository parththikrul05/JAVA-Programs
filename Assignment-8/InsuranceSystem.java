import java.util.Scanner;

// Parent Class
class Vehicle {
    protected String registrationNumber;
    protected String model;
    protected double baseValue;

    // Parent constructor
    public Vehicle(String registrationNumber, String model, double baseValue) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.baseValue = baseValue;
    }

    // Parent method
    public void showVehicleInfo() {
        System.out.println("Registration No: " + registrationNumber);
        System.out.println("Vehicle Model: " + model);
        System.out.println("Vehicle Value: $" + baseValue);
    }
}

// Child Class
class ComprehensiveInsurance extends Vehicle {
    String policyNumber;
    double premiumRate;

    // Child constructor
    public ComprehensiveInsurance(String registrationNumber, String model, double baseValue, String policyNumber, double premiumRate) {
        // 1. Accessing parent constructor
        super(registrationNumber, model, baseValue);
        this.policyNumber = policyNumber;
        this.premiumRate = premiumRate;
    }

    public double calculatePremium() {
        // 2. Accessing parent field (baseValue) directly using super
        return super.baseValue * (premiumRate / 100);
    }
    public void showInsuranceDetails() {
        System.out.println("--- Insurance Policy Details ---");
        System.out.println("Policy Number: " + policyNumber);
        
        // 3. Accessing parent method
        super.showVehicleInfo();
        System.out.println("Premium Rate: " + premiumRate + "%");
        System.out.println("Annual Premium: $" + calculatePremium());
    }
}

// Main class
public class InsuranceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking inputs from the user
        System.out.print("Enter Vehicle Registration Number: ");
        String regNumber = scanner.nextLine();
        
        System.out.print("Enter Vehicle Model: ");
        String model = scanner.nextLine();
        
        System.out.print("Enter Vehicle Base Value: ");
        double baseValue = scanner.nextDouble();
        
        // Consume the newline character left by nextDouble()
        scanner.nextLine(); 
        
        System.out.print("Enter Insurance Policy Number: ");
        String policyNumber = scanner.nextLine();
        
        System.out.print("Enter Premium Rate (in %): ");
        double premiumRate = scanner.nextDouble();
        ComprehensiveInsurance myInsurance = new ComprehensiveInsurance(
            regNumber, 
            model, 
            baseValue, 
            policyNumber, 
            premiumRate
        );
        
        System.out.println(); 
        myInsurance.showInsuranceDetails();
        
        scanner.close(); 
    }
}