package com.emp;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee_db",
                    "root",
                    "root");
            System.out.println("DB Connected...");
        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");

        try {
            if (action.equals("register")) {

                String name = req.getParameter("name");
                String uname = req.getParameter("username");
                String pass = req.getParameter("password");

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO employee(name, username, password) VALUES(?,?,?)");
                ps.setString(1, name);
                ps.setString(2, uname);
                ps.setString(3, pass);

                int i = ps.executeUpdate();

                if (i > 0)
                    out.println("<h2>Employee Registered Successfully</h2>");
                else
                    out.println("<h2>Registration Failed</h2>");

            } else if (action.equals("login")) {

                String uname = req.getParameter("username");
                String pass = req.getParameter("password");

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM employee WHERE username=? AND password=?");
                ps.setString(1, uname);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next())
                    out.println("<h2>Login Successful — Attendance Marked</h2>");
                else
                    out.println("<h2>Invalid Username or Password</h2>");
            }

        } catch (Exception e) {
            out.println("Error: " + e);
        }
    }

    public void destroy() {
        try {
            con.close();
            System.out.println("Connection Closed...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
