package com.order;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/customerdb",
                    "root",
                    "root"); // change password
        }
        catch (Exception e) {
            System.out.println("DB Error: " + e);
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            String name = req.getParameter("name");
            String item = req.getParameter("item");
            int qty = Integer.parseInt(req.getParameter("quantity"));

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO orders(customer_name,item_name,quantity) VALUES(?,?,?)");

            ps.setString(1, name);
            ps.setString(2, item);
            ps.setInt(3, qty);

            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<h2>Order Placed Successfully!</h2>");
                out.println("<h3>Order Summary</h3>");
                out.println("Customer: " + name + "<br>");
                out.println("Item: " + item + "<br>");
                out.println("Quantity: " + qty + "<br>");
            } else {
                out.println("<h2>Order Failed!</h2>");
            }
        }
        catch (Exception e) {
            out.println("Error: " + e);
        }
    }

    public void destroy() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}