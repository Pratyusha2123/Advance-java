package com.demoStudent;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentDAO {

    public void addStudentBatch() {

        try {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Student(name,course,age,marks) VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "Rahul");
            ps.setString(2, "Java");
            ps.setInt(3, 21);
            ps.setDouble(4, 85);
            ps.addBatch();

            ps.setString(1, "Priya");
            ps.setString(2, "Python");
            ps.setInt(3, 22);
            ps.setDouble(4, 90);
            ps.addBatch();

            ps.setString(1, "Amit");
            ps.setString(2, "Full Stack");
            ps.setInt(3, 23);
            ps.setDouble(4, 88);
            ps.addBatch();

            ps.setString(1, "Sneha");
            ps.setString(2, "Data Science");
            ps.setInt(3, 21);
            ps.setDouble(4, 92);
            ps.addBatch();

            ps.setString(1, "Rohan");
            ps.setString(2, "Cyber Security");
            ps.setInt(3, 22);
            ps.setDouble(4, 86);
            ps.addBatch();

            ps.executeBatch();
            System.out.println("5 Students Added Successfully");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}