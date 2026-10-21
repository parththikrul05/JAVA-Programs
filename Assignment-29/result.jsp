<!DOCTYPE html>
<html>
<head>
    <title>Registration Result</title>
</head>
<body>

<h2>Registration Details</h2>

<%
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String password = request.getParameter("password");
%>

<p>Name: <%= name %></p>
<p>Email: <%= email %></p>
<p>Phone Number: <%= phone %></p>
<p>Password: <%= password %></p>

</body>
</html>