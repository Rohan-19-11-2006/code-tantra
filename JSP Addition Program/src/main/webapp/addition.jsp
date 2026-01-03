<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Addition Program</title>
</head>
<body>

<h2>JSP Addition Program</h2>

<form method="post">
    Number 1: <input type="text" name="num1" required><br><br>
    Number 2: <input type="text" name="num2" required><br><br>
    <input type="submit" value="Add">
</form>

<hr>

<%
    // Read parameters
    String n1 = request.getParameter("num1");
    String n2 = request.getParameter("num2");

    // Check if form is submitted
    if (n1 != null && n2 != null) {
        int num1 = Integer.parseInt(n1);
        int num2 = Integer.parseInt(n2);

        int sum = num1 + num2;
%>
        <h3>Result:</h3>
        <p>Sum = <b><%= sum %></b></p>
<%
    }
%>

</body>
</html>
