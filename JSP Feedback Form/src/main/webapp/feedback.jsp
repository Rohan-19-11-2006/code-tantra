<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback Form</title>
</head>
<body>

<h2>User Feedback Form</h2>

<form method="post">
    Name:
    <input type="text" name="name" required><br><br>

    Feedback:
    <textarea name="feedback" required></textarea><br><br>

    <input type="submit" value="Submit">
</form>

<%
    String name = request.getParameter("name");
    String feedback = request.getParameter("feedback");

    if (name != null && feedback != null) {
%>
        <h3>Thank you, <%= name %>, for your feedback!</h3>
<%
    }
%>

</body>
</html>
