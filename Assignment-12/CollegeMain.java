import college.Student;
import college.Faculty;
import java.util.Scanner;

public class CollegeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- College Management System ---");
    
        System.out.print("\nEnter the number of students you want to add: ");
        int numStudents = scanner.nextInt();
        Student[] students = new Student[numStudents];
        
        for (int i = 0; i < numStudents; i++) {
            System.out.println("\n--- Enter Details for Student " + (i + 1) + " ---");
            
            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();
            
            System.out.print("Enter Course: ");
            String course = scanner.nextLine();
            
            students[i] = new Student(studentId, studentName, course);
        }
        
        System.out.print("\nEnter the number of faculty members you want to add: ");
        int numFaculty = scanner.nextInt();

        Faculty[] faculties = new Faculty[numFaculty];
        
        for (int i = 0; i < numFaculty; i++) {
            System.out.println("\n--- Enter Details for Faculty " + (i + 1) + " ---");
            
            System.out.print("Enter Faculty ID: ");
            int facultyId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            
            System.out.print("Enter Faculty Name: ");
            String facultyName = scanner.nextLine();
            
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
            
            // Store the new Faculty object in the array
            faculties[i] = new Faculty(facultyId, facultyName, department);
        }
        System.out.println("DISPLAYING INFORMATION");
        
        System.out.println("\n-Student Details-");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Student " + (i + 1) + ":");
            students[i].displayStudent();
        }
        
        System.out.println("\n-Faculty Details-");
        for (int i = 0; i < numFaculty; i++) {
            System.out.println("Faculty " + (i + 1) + ":");
            faculties[i].displayFaculty();
        }
        scanner.close();
    }
}