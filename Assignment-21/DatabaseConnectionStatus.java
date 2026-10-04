import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionStatus {

    // Database credentials and URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Pass"; // Replace with your MySQL password

    public static void main(String[] args) {
        System.out.println("Attempting to connect to the database...");

        // Establishing connection using try-with-resources to ensure auto-closure
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            
            // Checking status
            if (connection != null && !connection.isClosed()) {
                System.out.println("Status: Database Connected Successfully!");
                System.out.println("Database Product Name: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("Driver Version: " + connection.getMetaData().getDriverVersion());
            } else {
                System.out.println("Status: Connection Failed!");
            }

        } catch (SQLException e) {
            System.err.println("Status: Connection Error!");
            System.err.println("Error Message: " + e.getMessage());
        }
    }
}