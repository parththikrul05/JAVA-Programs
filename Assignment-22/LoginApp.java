import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginApp {
    private static final String URL = "jdbc:mysql://localhost:3306/auth_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass"; // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authenticate(username, password)) {
            System.out.println("\nLogin Successful! Welcome, " + username + ".");
        } else {
            System.out.println("\nInvalid username or password. Access Denied.");
        }

        scanner.close();
    }

    private static boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Bind input parameters safely to prevent SQL Injection
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Returns true if a matching user record is found
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            return false;
        }
    }
}