package com.demoemployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDAO {
    Connection con;

    public EmployeeDAO() throws Exception {
        con = DBConnection.getConnection(); // Assuming DBConnection class handles connection
    }

    public void registerEmployee(String name, String department, double salary) throws Exception {
        String query = "INSERT INTO Employee (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, department);
        pstmt.setDouble(3, salary);

        int rows = pstmt.executeUpdate();
        System.out.println(rows + " Row(s) Inserted Successfully!");
    }

    public void getEmployeeById(int id) throws Exception {
        String query = "SELECT * FROM Employee WHERE id = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("ID : " + rs.getInt("id"));
            System.out.println("Name : " + rs.getString("name"));
            System.out.println("Department : " + rs.getString("department"));
            System.out.println("Salary : " + rs.getDouble("salary"));
        } else {
            System.out.println("No employee found with ID: " + id);
        }
    }

    public void getAllEmployees() throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Employee");

        System.out.println("-----------------------------------");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    " | Name: " + rs.getString("name") +
                    " | Dept: " + rs.getString("department") +
                    " | Salary: " + rs.getDouble("salary"));
        }
        System.out.println("-----------------------------------");
    }
}
