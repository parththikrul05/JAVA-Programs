import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalStaffAuthApp {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass"; // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Hospital Staff Portal ===");
        System.out.print("Enter Login ID: ");
        String loginId = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        authenticateAndGrantAccess(loginId, password);

        scanner.close();
    }

    private static void authenticateAndGrantAccess(String loginId, String password) {
        String sql = "SELECT full_name, role FROM staff WHERE login_id = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, loginId);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("full_name");
                    String role = rs.getString("role");

                    System.out.println("\n=============================================");
                    System.out.println("AUTHENTICATION SUCCESSFUL");
                    System.out.println("Welcome, " + name + " (" + role + ")");
                    System.out.println("=============================================");
                    
                    displayAccessPortal(role);
                } else {
                    System.out.println("\n[ERROR] Authentication Failed: Invalid Login ID or Password.");
                }
            }

        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Could not connect or execute query: " + e.getMessage());
        }
    }

    private static void displayAccessPortal(String role) {
        switch (role) {
            case "Doctor":
                System.out.println("[ACCESS GRANTED] Doctor Portal");
                System.out.println("- Access patient medical history");
                System.out.println("- Write and approve medical prescriptions");
                System.out.println("- Request critical allocation & lab procedures");
                break;

            case "Nurse":
                System.out.println("[ACCESS GRANTED] Nursing Unit Portal");
                System.out.println("- View assigned ward bed statuses");
                System.out.println("- Record patient vital metrics");
                System.out.println("- Manage medication dosage logs");
                break;

            default:
                System.out.println("[WARNING] Role recognized, but no default panel is assigned.");
                break;
        }
    }
}