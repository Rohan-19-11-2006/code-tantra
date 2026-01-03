package studentRegistrationDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StudentRegistration {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // 1. Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/college",
                    "root",
                    "root" 
            );

            // Auto commit ON
            con.setAutoCommit(true);

            // 3. Get student details
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter Department: ");
            String dept = sc.nextLine();

            // 4. SQL Insert Query
            String query = "INSERT INTO students(name, roll, dept) VALUES (?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, roll);
            pst.setString(3, dept);

            // 5. Execute
            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("Student Registered Successfully!");
            }

            // 6. Close resources
            pst.close();
            con.close();
            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
