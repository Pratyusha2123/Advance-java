package com.demoemployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDAO {

    Connection con;

    public EmployeeDAO() {
        con = DBConnection.getConnection();
    }

    public void registerEmployee(String name, String department, double salary) {

        try {

            String query = "INSERT INTO Employee(name, department, salary) VALUES(?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee Registered Successfully...");
            } else {
                System.out.println("Registration Failed...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEmployeeById(int id) {

        try {

            String query = "SELECT * FROM Employee WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("ID         : " + rs.getInt("id"));
                System.out.println("Name       : " + rs.getString("name"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Salary     : " + rs.getDouble("salary"));
                System.out.println("----------------------------");

            } else {

                System.out.println("Employee Not Found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllEmployees() {

        try {

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM Employee");

            System.out.println("---------------------------------------------");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("department") + " | " +
                                rs.getDouble("salary"));

            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int id, String name, String department, double salary) {

        try {

            String query = "UPDATE Employee SET name=?, department=?, salary=? WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee Updated Successfully.");

            } else {

                System.out.println("Employee ID Not Found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {

        try {

            String query = "DELETE FROM Employee WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee Deleted Successfully.");

            } else {

                System.out.println("Employee ID Not Found.");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}