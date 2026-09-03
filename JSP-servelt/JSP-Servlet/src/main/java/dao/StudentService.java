package dao;

import entity.Student;

import java.sql.*;

public class StudentService {
    Connection con;
    public StudentService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student",
                    "root",
                    "SS2003"
            );

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int register(Student student) {
        String sql = "INSERT INTO studentdata VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement prt = con.prepareStatement(sql);

            prt.setInt(1, student.getId());
            prt.setString(2, student.getName());
            prt.setString(3, student.getEmail());
            prt.setString(4, student.getRedgNo());
            prt.setString(5,student.getPassword());

            return prt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student validateStudent(String email, String password) {
        Student student = null;
        String sql = "SELECT * FROM studentdata WHERE email = ? AND password = ?";

        try {
            PreparedStatement prt = con.prepareStatement(sql);
            prt.setString(1, email);
            prt.setString(2, password);

            ResultSet rs = prt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setEmail(rs.getString(3));
                student.setRedgNo(rs.getString(4));
                student.setPassword(rs.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;
    }
}