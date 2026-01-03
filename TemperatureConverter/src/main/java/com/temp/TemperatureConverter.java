// File: TemperatureConverter.java
package com.temp;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TemperatureConverter")
public class TemperatureConverter extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String celsiusParam = request.getParameter("celsius");

        out.println("<html><body>");
        out.println("<h2>Celsius to Fahrenheit Converter</h2>");

        // Check if parameter is missing or empty
        if (celsiusParam == null || celsiusParam.trim().isEmpty()) {
            out.println("<p>Error: Please enter a Celsius value!</p>");
        } else {
            try {
                // Convert input to double
                double celsius = Double.parseDouble(celsiusParam.trim());
                double fahrenheit = (celsius * 9 / 5) + 32;

                out.println("<p>" + celsius + "°C = " + String.format("%.2f", fahrenheit) + "°F</p>");
            } catch (NumberFormatException e) {
                out.println("<p>Error: Invalid number format! Please enter a valid number.</p>");
            }
        }

        out.println("<br><a href='temperature.html'>Convert Another</a>");
        out.println("</body></html>");
    }
}

