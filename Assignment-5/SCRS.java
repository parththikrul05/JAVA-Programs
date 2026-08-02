import java.util.Scanner;
import java.util.ArrayList;

public class SCRS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // ArrayList to dynamically store registered courses
        ArrayList<String> courses = new ArrayList<>();
        boolean running = true;

        System.out.println("=== Student Course Registration System ===");

        while (running) {
            // Displaying the Menu
            System.out.println("\nMenu Options:");
            System.out.println("1. Add a Course");
            System.out.println("2. Remove a Course");
            System.out.println("3. View Registered Courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = sc.nextInt();
            
            // Consume the leftover newline character to prevent skipping inputs
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    // ADD COURSE
                    System.out.print("Enter the name of the course to add: ");
                    String courseToAdd = sc.nextLine();
                    courses.add(courseToAdd);
                    System.out.println("Success: '" + courseToAdd + "' has been added to your schedule.");
                    break;
                    
                case 2:
                    // REMOVE COURSE
                    if (courses.isEmpty()) {
                        System.out.println("Warning: Your course list is already empty.");
                    } else {
                        System.out.print("Enter the exact name of the course to remove: ");
                        String courseToRemove = sc.nextLine();
                        
                        // ArrayList.remove() returns true if the item existed and was removed
                        if (courses.remove(courseToRemove)) {
                            System.out.println("Success: '" + courseToRemove + "' has been removed.");
                        } else {
                            System.out.println("Error: Course '" + courseToRemove + "' not found in your list.");
                        }
                    }
                    break;
                    
                case 3:
                    // VIEW COURSES (Using StringBuffer as required)
                    StringBuffer displayBuffer = new StringBuffer();
                    displayBuffer.append("\n--- Your Registered Courses ---\n");
                    
                    if (courses.isEmpty()) {
                        displayBuffer.append("You have not registered for any courses yet.\n");
                    } else {
                        for (int i = 0; i < courses.size(); i++) {
                            // Chaining append methods for efficient string building
                            displayBuffer.append(i + 1).append(". ").append(courses.get(i)).append("\n");
                        }
                    }
                    
                    // Display the final generated string
                    System.out.print(displayBuffer);
                    break;
                    
                case 4:
                    // EXIT
                    System.out.println("Exiting the Course Registration System. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
        
        sc.close();
    }
}