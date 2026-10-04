import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentDatabaseConnection {

    // Target database URL for the Student database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db"; 
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Pass"; // Replace with your MySQL password

    public static void main(String[] args) {
        System.out.println("Initiating database connection...");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Check connection status
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connection Status: ACTIVE");
                System.out.println("Student database is connected successfully.");
            } else {
                System.out.println("Connection Status: INACTIVE");
            }

        } catch (SQLException e) {
            System.err.println("Connection Status: FAILED");
            System.err.println("Could not connect to the Student database.");
            System.err.println("Reason: " + e.getMessage());
        }
    }
}