import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to MySQL Database successfully!");

            while (true) {
                System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Employee (Create)");
                System.out.println("2. View All Employees (Read)");
                System.out.println("3. Update Employee (Update)");
                System.out.println("4. Delete Employee (Delete)");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> createEmployee(conn, scanner);
                    case 2 -> readEmployees(conn);
                    case 3 -> updateEmployee(conn, scanner);
                    case 4 -> deleteEmployee(conn, scanner);
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

    private static void createEmployee(Connection conn, Scanner scanner) throws SQLException {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            pstmt.setString(1, name);
            pstmt.setString(2, dept);
            pstmt.setDouble(3, salary);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee added successfully!");
            }
        }
    }

    private static void readEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Employee Records ---");
            System.out.printf("%-5s %-20s %-15s %-10s%n", "ID", "Name", "Department", "Salary");
            System.out.println("--------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-5d %-20s %-15s %-10.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
            }

            if (!found) {
                System.out.println("No employee records found.");
            }
        }
    }

    private static void updateEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter New Salary: ");
            double salary = scanner.nextDouble();

            pstmt.setString(1, name);
            pstmt.setString(2, dept);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee record updated successfully!");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }
    }

    private static void deleteEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }
    }
}
