package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SimpleInterestServlet")
public class SimpleInterestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Simple Interest Calculator</h2>");

        try {
            // Read parameters safely
            String pStr = request.getParameter("principal");
            String rStr = request.getParameter("rate");
            String tStr = request.getParameter("time");

            // Check for null or empty values
            if (pStr == null || pStr.isEmpty() ||
                rStr == null || rStr.isEmpty() ||
                tStr == null || tStr.isEmpty()) {

                out.println("<p style='color:red;'>Error: All fields are required!</p>");
                out.println("<p><a href='simpleInterest.html'>Go Back</a></p>");
                return;
            }

            // Parse input values
            double principal = Double.parseDouble(pStr);
            double rate = Double.parseDouble(rStr);
            double time = Double.parseDouble(tStr);

            // Simple Interest calculation
            double si = (principal * rate * time) / 100;

            // Display result
            out.println("<p>Principal (P): " + principal + "</p>");
            out.println("<p>Rate (R): " + rate + "%</p>");
            out.println("<p>Time (T): " + time + " years</p>");
            out.println("<p><strong>Simple Interest = " + si + "</strong></p>");

        } catch (NumberFormatException e) {
            out.println("<p style='color:red;'>Error: Please enter valid numeric values!</p>");
            out.println("<p><a href='simpleInterest.html'>Go Back</a></p>");
        }

        out.println("</body></html>");
    }
}
