import java.sql.*;
import java.util.Scanner;

public class StudentCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/academic_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to MySQL Database successfully!");

            while (true) {
                System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Student Details (Create)");
                System.out.println("2. View All Student Details (Read)");
                System.out.println("3. Update Student Details (Update)");
                System.out.println("4. Delete Student Details (Delete)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> createStudent(conn, scanner);
                    case 2 -> readStudents(conn);
                    case 3 -> updateStudent(conn, scanner);
                    case 4 -> deleteStudent(conn, scanner);
                    case 5 -> {
                        System.out.println("Exiting Application. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createStudent(Connection conn, Scanner scanner) throws SQLException {
        String sql = "INSERT INTO students (roll_no, name, course, marks) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Enter Roll Number: ");
            int rollNo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Course: ");
            String course = scanner.nextLine();

            System.out.print("Enter Marks: ");
            double marks = scanner.nextDouble();

            pstmt.setInt(1, rollNo);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setDouble(4, marks);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student details added successfully!");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: A student with this Roll Number already exists.");
        }
    }

    private static void readStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Student Records ---");
            System.out.printf("%-10s %-20s %-15s %-8s%n", "Roll No", "Name", "Course", "Marks");
            System.out.println("----------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-20s %-15s %-8.2f%n",
                        rs.getInt("roll_no"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getDouble("marks"));
            }

            if (!found) {
                System.out.println("No student records found.");
            }
        }
    }

    private static void updateStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number of Student to update: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE students SET name = ?, course = ?, marks = ? WHERE roll_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Course: ");
            String course = scanner.nextLine();
            System.out.print("Enter New Marks: ");
            double marks = scanner.nextDouble();

            pstmt.setString(1, name);
            pstmt.setString(2, course);
            pstmt.setDouble(3, marks);
            pstmt.setInt(4, rollNo);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student record updated successfully!");
            } else {
                System.out.println("Student with Roll Number " + rollNo + " not found.");
            }
        }
    }

    private static void deleteStudent(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Roll Number of Student to delete: ");
        int rollNo = scanner.nextInt();

        String sql = "DELETE FROM students WHERE roll_no = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rollNo);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student record deleted successfully!");
            } else {
                System.out.println("Student with Roll Number " + rollNo + " not found.");
            }
        }
    }
}