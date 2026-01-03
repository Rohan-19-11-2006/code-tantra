package com.book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {

    private Connection conn;

    // Step 3a: Initialize DB connection
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_db", "root", "root"
            );
        } catch (Exception e) {
            throw new ServletException("DB Connection error: " + e.getMessage());
        }
    }

    // Step 3b: Handle both POST (insert) and GET (view)
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (request.getMethod().equalsIgnoreCase("POST")) {
            // Insert book
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String priceStr = request.getParameter("price");

            if(title != null && author != null && priceStr != null){
                try {
                    double price = Double.parseDouble(priceStr);
                    String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, title);
                    ps.setString(2, author);
                    ps.setDouble(3, price);
                    ps.executeUpdate();
                    out.println("<h3>Book added successfully!</h3>");
                } catch (SQLException e) {
                    out.println("<h3>Error: " + e.getMessage() + "</h3>");
                }
            }
            out.println("<a href='index.html'>Go Back</a>");
        } else {
            // View books
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM books");
                out.println("<h2>Books in Library</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("title") + "</td>");
                    out.println("<td>" + rs.getString("author") + "</td>");
                    out.println("<td>" + rs.getDouble("price") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<br><a href='index.html'>Go Back</a>");
            } catch (SQLException e) {
                out.println("<h3>Error: " + e.getMessage() + "</h3>");
            }
        }
    }

    // Step 3c: Close connection
    public void destroy() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
