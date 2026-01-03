package com.cart;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        addToCart(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        addToCart(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String product = request.getParameter("product");

        HttpSession session = request.getSession();
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        if (product != null) {
            cart.add(product);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}
