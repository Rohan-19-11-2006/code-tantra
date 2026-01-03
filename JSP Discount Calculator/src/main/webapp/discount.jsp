=<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Discount Calculator</title>
</head>
<body>

<h2>Discount Calculator</h2>

<form method="post">
    Price:
    <input type="text" name="price" required><br><br>

    Discount (%):
    <input type="text" name="discount" required><br><br>

    <input type="submit" value="Calculate">
</form>

<hr>

<%
    String priceStr = request.getParameter("price");
    String discountStr = request.getParameter("discount");

    if (priceStr != null && discountStr != null) {
        double price = Double.parseDouble(priceStr);
        double discount = Double.parseDouble(discountStr);

        double finalPrice = price - (price * discount / 100);
%>
        <h3>Result</h3>
        Original Price: <%= price %><br>
        Discount: <%= discount %> %<br>
        <strong>Final Payable Amount: <%= finalPrice %></strong>
<%
    }
%>

</body>
</html>
