<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>

<html>
<head>
    <title>Shopping Cart</title>
</head>
<body>

<h2>Your Cart</h2>

<%
    ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

    if (cart == null || cart.isEmpty()) {
%>
        <p>Your cart is empty.</p>
<%
    } else {
%>
        <ul>
        <%
            for (String item : cart) {
        %>
                <li><%= item %></li>
        <%
            }
        %>
        </ul>
<%
    }
%>

<br>
<a href="products.jsp">Back to Products</a>

</body>
</html>
