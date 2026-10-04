import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SequentialRecordViewer {
    // Database credentials and URL (adjust to match your setup)
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass";

    public static void main(String[] args) {
        String query = "SELECT * FROM products";

        // Try-with-resources automatically closes connections and statements
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int recordCount = 1;

            // rs.next() moves the cursor to the next record and returns true if a record exists
            while (rs.next()) {
                System.out.println("--- Record #" + recordCount + " ---");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Category: " + rs.getString("category"));
                recordCount++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}