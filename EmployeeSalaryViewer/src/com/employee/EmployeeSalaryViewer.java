package com.employee;
import java.util.Scanner;

public class EmployeeSalaryViewer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        EmployeeDAO.getEmployeeSalary(empId);

        sc.close();
    }
}
