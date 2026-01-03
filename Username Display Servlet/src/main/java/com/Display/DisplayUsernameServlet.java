package com.Display;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DisplayUsernameServlet")
public class DisplayUsernameServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Read username parameter
        String username = request.getParameter("username");

        // Start HTML
        out.println("<html>");
        out.println("<head><title>Username Display</title></head>");
        out.println("<body>");
        out.println("<h2>Enter Your Username</h2>");

        // If username is empty or null, show the form
        if(username == null || username.trim().isEmpty()) {
            out.println("<form action='DisplayUsernameServlet' method='get'>");
            out.println("Username: <input type='text' name='username' required>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
        } else {
            // Show greeting
            out.println("<h3>Welcome, " + username + "!</h3>");
            // Optionally, show form again to enter new username
            out.println("<br><form action='DisplayUsernameServlet' method='get'>");
            out.println("Enter another username: <input type='text' name='username' required>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
