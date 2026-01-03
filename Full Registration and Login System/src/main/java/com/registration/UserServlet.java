package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private Connection con;

    // 1️⃣ init() – runs once
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/userdb",
                "root",
                "root"
            );

            System.out.println("✅ Database connected");

        } catch (Exception e) {
            throw new ServletException("DB Connection error", e);
        }
    }

    // 2️⃣ service() – runs for every request
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            if ("register".equals(action)) {

                String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);

                ps.executeUpdate();

                out.println("<h3>Registration Successful</h3>");
                out.println("<a href='login.html'>Login Now</a>");

            } 
            else if ("login".equals(action)) {

                String sql = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("<h3>Login Successful</h3>");
                    out.println("<h4>Welcome " + username + "</h4>");
                } else {
                    out.println("<h3>Invalid Username or Password</h3>");
                    out.println("<a href='login.html'>Try Again</a>");
                }

            } 
            else {
                out.println("<h3>Invalid action!</h3>");
            }

        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }

    // 3️⃣ destroy() – runs when server stops
    @Override
    public void destroy() {
        try {
            if (con != null)
                con.close();
            System.out.println("❌ Database connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
