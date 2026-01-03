<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Products</title>
</head>
<body>

<h2>Available Products</h2>

<form action="CartServlet" method="post">
    <input type="hidden" name="product" value="Laptop">
    <input type="submit" value="Add Laptop to Cart">
</form>

<form action="CartServlet" method="post">
    <input type="hidden" name="product" value="Mobile">
    <input type="submit" value="Add Mobile to Cart">
</form>

<form action="CartServlet" method="post">
    <input type="hidden" name="product" value="Headphones">
    <input type="submit" value="Add Headphones to Cart">
</form>

<br><br>
<a href="cart.jsp">View Cart</a>

</body>
</html>
