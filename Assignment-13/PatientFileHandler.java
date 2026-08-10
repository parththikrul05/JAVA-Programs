import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class PatientFileHandler {
    public static void main(String[] args) {
        String fileName = "patient_details.txt";
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the number of patients (n)
        System.out.print("How many patients do you want to enter? ");
        int numberOfPatients = scanner.nextInt();
        
        // Consume the leftover newline character
        scanner.nextLine(); 

        // 1. Writing Patient Details
        // Using append mode (true) so previous runs aren't overwritten
        try (FileWriter writer = new FileWriter(fileName, true)) {
            System.out.println("\n--- Enter Details for " + numberOfPatients + " Patients ---");
            
            for (int i = 1; i <= numberOfPatients; i++) {
                System.out.println("\nPatient " + i + ":");
                
                System.out.print("Enter Patient ID: ");
                String patientId = scanner.nextLine();
                
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter Age: ");
                String age = scanner.nextLine();
                
                System.out.print("Enter Diagnosis: ");
                String diagnosis = scanner.nextLine();

                // Write the collected details to the file with line breaks
                writer.write("Patient ID: " + patientId + "\n");
                writer.write("Name: " + name + "\n");
                writer.write("Age: " + age + "\n");
                writer.write("Diagnosis: " + diagnosis + "\n");
                writer.write("-------------------------\n"); 
            }
            
            System.out.println("\nPatient details successfully saved to: " + fileName);
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("\n--- Reading Patient Details ---");

        // 2. Reading Patient Details
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Loop through and print each line
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        
        scanner.close();
    }
}