package com.employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    public static void getEmployeeSalary(int empId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT name, salary FROM employees WHERE emp_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Employee Name  : " + rs.getString("name"));
                System.out.println("Employee Salary: " + rs.getDouble("salary"));
            } else {
                System.out.println("❌ Record not found!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

