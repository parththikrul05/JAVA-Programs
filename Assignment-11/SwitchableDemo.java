import java.util.Scanner;

interface Switchable {
    void turnOn();
}
class Light implements Switchable {
    public void turnOn() {
        System.out.println("Device Status: The Light is now turned ON.");
    }
}
class Fan implements Switchable {
    public void turnOn() {
        System.out.println("Device Status: The Fan is now turned ON.");
    }
}
public class SwitchableDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Which device would you like to turn on?");
        System.out.println("1. Light");
        System.out.println("2. Fan");
        System.out.print("Enter your choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        
        System.out.println(); // Print a blank line for readability

        if (choice == 1) {
            Switchable light = new Light();
            light.turnOn();
        } else if (choice == 2) {
            Switchable fan = new Fan();
            fan.turnOn();
        } else {
            System.out.println("Invalid choice!");
        }
        scanner.close();
    }
}