<!DOCTYPE html>
<html>
<head>
    <title>Student Information</title>
</head>
<body>

<h2>Student Information</h2>

<form method="post">

    Name:
    <input type="text" name="name">
    <br><br>

    Roll No:
    <input type="text" name="roll">
    <br><br>

    Course:
    <input type="text" name="course">
    <br><br>

    <input type="submit" value="Submit">

</form>

<%
    String name = request.getParameter("name");
    String roll = request.getParameter("roll");
    String course = request.getParameter("course");

    if (name != null && roll != null && course != null) {
%>

<h3>Student Details</h3>

<p>Name: <%= name %></p>
<p>Roll No: <%= roll %></p>
<p>Course: <%= course %></p>

<%
    }
%>

</body>
</html>