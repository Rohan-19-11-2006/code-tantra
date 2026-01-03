package com.AdditionCalculator;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Direct GET requests go here
        showError(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");

        if (n1 == null || n2 == null || n1.isEmpty() || n2.isEmpty()) {
            showError(response);
            return;
        }

        int a = Integer.parseInt(n1);
        int b = Integer.parseInt(n2);

        out.println("<h2>Sum is: " + (a + b) + "</h2>");
        out.println("<br><a href='add.html'>Go Back</a>");
    }

    private void showError(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Error: Please enter both numbers using the form.</h3>");
        out.println("<a href='add.html'>Go Back</a>");
    }


}
