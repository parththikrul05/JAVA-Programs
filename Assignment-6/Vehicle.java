// Interface for the anonymous class to implement
interface VehicleAction {
    void performAction();
}

public class Vehicle {
    private String brand;
    private String type;

    public Vehicle(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    // Inner Class: Handles displaying details
    class VehicleDetails {
        public void displayDetails() {
            System.out.println("--- Vehicle Information ---");
            System.out.println("Brand: " + brand);
            System.out.println("Type: " + type);
        }
    }

    public static void main(String[] args) {
        // Instantiate the outer class
        Vehicle myVehicle = new Vehicle("Tesla", "Electric Sedan");

        // Instantiate the inner class using the outer class instance
        Vehicle.VehicleDetails details = myVehicle.new VehicleDetails();
        details.displayDetails();

        // Anonymous Class: Performs a specific action
        VehicleAction action = new VehicleAction() {
            // Overriding the interface method without the @Override annotation
            public void performAction() {
                System.out.println("Action: The vehicle is now accelerating.");
            }
        };

        // Trigger the anonymous class method
        action.performAction();
    }
}