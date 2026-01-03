package com.ProductPriceUpdater.app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductPriceUpdater {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/shopdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int productId = sc.nextInt();

        System.out.print("Enter New Price: ");
        double newPrice = sc.nextDouble();

        String sql = "UPDATE products SET price = ? WHERE id = ?";

        try (
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(sql)
        ) {
            pst.setDouble(1, newPrice);
            pst.setInt(2, productId);

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("✅ Product price updated successfully!");
            } else {
                System.out.println("❌ Product ID not found.");
            }

        } catch (SQLException e) {
            System.out.println("⚠️ Database error occurred.");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
