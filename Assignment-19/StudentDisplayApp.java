import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDisplayApp {

    // Update database configuration as needed
    private static final String DB_URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Pass";

    public static void main(String[] args) {
        String selectQuery = "SELECT student_id, name, department, gpa FROM students";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            System.out.println("=====================================================================");
            System.out.printf("%-12s | %-20s | %-22s | %-5s%n", "Student ID", "Name", "Department", "GPA");
            System.out.println("=====================================================================");

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double gpa = rs.getDouble("gpa");

                System.out.printf("%-12d | %-20s | %-22s | %-5.2f%n", id, name, dept, gpa);
            }
            System.out.println("=====================================================================");

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}