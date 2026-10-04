import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDisplayApp {

    // Update database configuration as needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Parth@0510";

    public static void main(String[] args) {
        String selectQuery = "SELECT product_id, product_name, quantity, price FROM products";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            System.out.println("==========================================================");
            System.out.printf("%-12s | %-20s | %-8s | %-8s%n", "Product ID", "Product Name", "Quantity", "Price ($)");
            System.out.println("==========================================================");

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                System.out.printf("%-12d | %-20s | %-8d | %-8.2f%n", id, name, quantity, price);
            }
            System.out.println("==========================================================");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}