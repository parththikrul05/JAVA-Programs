<%@ page import="java.sql.*" %>
<%@ page import="com.parth.DatabaseConnection" %>

<!DOCTYPE html>
<html>
<head>
    <title>Employee CRUD</title>
</head>
<body>

<h2>Employee Management</h2>

<form method="post">

    Employee ID:
    <input type="number" name="emp_id" required>
    <br><br>

    Name:
    <input type="text" name="name">
    <br><br>

    Department:
    <input type="text" name="department">
    <br><br>

    Salary:
    <input type="number" name="salary">
    <br><br>

    <input type="submit" name="action" value="Add">
    <input type="submit" name="action" value="Update">
    <input type="submit" name="action" value="Delete">
    <input type="submit" name="action" value="View">

</form>

<%
    String action = request.getParameter("action");

    if (action != null) {
        try {
            Connection con = DatabaseConnection.getConnection();

            int id = Integer.parseInt(request.getParameter("emp_id"));

            if (action.equals("Add")) {

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO employee VALUES (?, ?, ?, ?)"
                );

                ps.setInt(1, id);
                ps.setString(2, request.getParameter("name"));
                ps.setString(3, request.getParameter("department"));
                ps.setDouble(4, Double.parseDouble(request.getParameter("salary")));

                ps.executeUpdate();

                out.println("<p>Employee added successfully.</p>");

            } else if (action.equals("Update")) {

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE employee SET name=?, department=?, salary=? WHERE emp_id=?"
                );

                ps.setString(1, request.getParameter("name"));
                ps.setString(2, request.getParameter("department"));
                ps.setDouble(3, Double.parseDouble(request.getParameter("salary")));
                ps.setInt(4, id);

                ps.executeUpdate();

                out.println("<p>Employee updated successfully.</p>");

            } else if (action.equals("Delete")) {

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM employee WHERE emp_id=?"
                );

                ps.setInt(1, id);

                ps.executeUpdate();

                out.println("<p>Employee deleted successfully.</p>");

            } else if (action.equals("View")) {

                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM employee"
                );

                out.println("<h3>Employee Records</h3>");

                while (rs.next()) {
                    out.println(
                        rs.getInt("emp_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("department") + " | " +
                        rs.getDouble("salary") + "<br>"
                    );
                }
            }

            con.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
%>

</body>
</html>