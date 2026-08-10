import java.util.Scanner;

// First Interface (Abstraction)
interface Switchable {
    void turnOn();
}

// Second Interface (Abstraction)
interface Adjustable {
    void adjustSetting();
}

// Light class implementing only ONE interface
class Light implements Switchable {
    public void turnOn() {
        System.out.println("Device Status: The Light is now turned ON.");
    }
}

//Multiple inheritance
class Fan implements Switchable, Adjustable {
    public void turnOn() {
        System.out.println("Device Status: The Fan is now turned ON.");
    }
    
    public void adjustSetting() {
        System.out.println("Device Status: The Fan speed has been adjusted to HIGH.");
    }
}

public class SwitchableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Which device would you like to interact with?");
        System.out.println("1. Light (Can only be turned on)");
        System.out.println("2. Smart Fan (Can be turned on AND adjusted)");
        System.out.print("Enter your choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        
        System.out.println();

        if (choice == 1) {
            // Interacting with Light through the Switchable interface
            Switchable light = new Light();
            light.turnOn();
            
        } else if (choice == 2) {
            Fan fan = new Fan();
            
            // Using a method from the Switchable interface
            fan.turnOn(); 
            
            // Using a method from the Adjustable interface
            fan.adjustSetting(); 
            
        } else {
            System.out.println("Invalid choice!");
        }
        
        scanner.close();
    }
}
