<%@ page import="java.sql.*" %>
<%@ page import="com.parth.DatabaseConnection" %>

<!DOCTYPE html>
<html>
<head>
    <title>Patient CRUD</title>
</head>
<body>

<h2>Patient Management</h2>

<form method="post">

    Patient ID:
    <input type="number" name="patient_id" required>
    <br><br>

    Name:
    <input type="text" name="name">
    <br><br>

    Age:
    <input type="number" name="age">
    <br><br>

    Disease:
    <input type="text" name="disease">
    <br><br>

    Treatment Status:
    <input type="text" name="treatment_status">
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

            int id = Integer.parseInt(request.getParameter("patient_id"));

            if (action.equals("Add")) {

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO patient VALUES (?, ?, ?, ?, ?)"
                );

                ps.setInt(1, id);
                ps.setString(2, request.getParameter("name"));
                ps.setInt(3, Integer.parseInt(request.getParameter("age")));
                ps.setString(4, request.getParameter("disease"));
                ps.setString(5, request.getParameter("treatment_status"));

                ps.executeUpdate();

                out.println("<p>Patient added successfully.</p>");

            } else if (action.equals("Update")) {

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE patient SET name=?, age=?, disease=?, treatment_status=? WHERE patient_id=?"
                );

                ps.setString(1, request.getParameter("name"));
                ps.setInt(2, Integer.parseInt(request.getParameter("age")));
                ps.setString(3, request.getParameter("disease"));
                ps.setString(4, request.getParameter("treatment_status"));
                ps.setInt(5, id);

                ps.executeUpdate();

                out.println("<p>Patient updated successfully.</p>");

            } else if (action.equals("Delete")) {

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM patient WHERE patient_id=?"
                );

                ps.setInt(1, id);

                ps.executeUpdate();

                out.println("<p>Patient deleted successfully.</p>");

            } else if (action.equals("View")) {

                Statement stmt = con.createStatement();

                ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM patient"
                );

                out.println("<h3>Patient Records</h3>");

                while (rs.next()) {
                    out.println(
                        rs.getInt("patient_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | " +
                        rs.getString("disease") + " | " +
                        rs.getString("treatment_status") +
                        "<br>"
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