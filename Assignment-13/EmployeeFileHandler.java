import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeFileHandler {
    public static void main(String[] args) {
        String fileName = "employee_details.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many employees do you want to enter? ");
        int numberOfEmployees = scanner.nextInt();
        scanner.nextLine(); 

        // 1. Writing to the file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            System.out.println("\nEnter Details for " + numberOfEmployees + "Employees");
            
            // Loop runs for the exact number the user requested
            for (int i = 1; i <= numberOfEmployees; i++) {
                System.out.println("\nEmployee " + i + ":");
                
                System.out.print("Enter Employee ID: ");
                String empId = scanner.nextLine();
                
                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter Employee Role: ");
                String role = scanner.nextLine();

                // Write the formatted input to the file
                writer.write("Emp ID: " + empId + ", Name: " + name + ", Role: " + role + "\n");
            }
            
            System.out.println("\nAll employee details successfully written to: " + fileName);
            
        } catch (IOException e) {
            System.out.println("An error occurred while writing: " + e.getMessage());
        }

        System.out.println("\n--- Reading Employee Details ---");

        // 2. Reading from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading: " + e.getMessage());
        }
        
        scanner.close();
    }
}