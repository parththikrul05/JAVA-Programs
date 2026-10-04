import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeRecordViewer {
    // Database credentials (using MySQL as an example)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/company_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Pass";

    public static void main(String[] args) {
        String sqlQuery = "SELECT emp_id, emp_name, department, salary FROM employees";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            System.out.println("=========================================");
            System.out.println("       EMPLOYEE RECORDS DATABASE         ");
            System.out.println("=========================================\n");

            int count = 1;

            // Iterate through the ResultSet one record at a time
            while (resultSet.next()) {
                int id = resultSet.getInt("emp_id");
                String name = resultSet.getString("emp_name");
                String department = resultSet.getString("department");
                double salary = resultSet.getDouble("salary");

                // Displaying the retrieved employee details
                System.out.printf("Record %d:\n", count);
                System.out.printf("  Employee ID : %d\n", id);
                System.out.printf("  Name        : %s\n", name);
                System.out.printf("  Department  : %s\n", department);
                System.out.printf("  Salary      : $%.2f\n", salary);
                System.out.println("-----------------------------------------");

                count++;
            }

            if (count == 1) {
                System.out.println("No records found in the database.");
            }

        } catch (Exception e) {
            System.err.println("Database connection or query execution failed!");
            e.printStackTrace();
        }
    }
}