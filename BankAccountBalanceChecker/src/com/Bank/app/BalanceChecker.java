package com.Bank.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BalanceChecker {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        try {
            // 1. Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create Connection
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb", "root", "root");

            // 3. Prepare SQL Query
            String query = "SELECT holder_name, balance FROM accounts WHERE acc_no = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, accNo);

            // 4. Execute Query
            ResultSet rs = ps.executeQuery();

            // 5. Process Result
            if (rs.next()) {
                System.out.println("Account Holder: " + rs.getString("holder_name"));
                System.out.println("Account Balance: ₹" + rs.getDouble("balance"));
            } else {
                System.out.println("Account not found!");
            }

            // 6. Close Connection
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
