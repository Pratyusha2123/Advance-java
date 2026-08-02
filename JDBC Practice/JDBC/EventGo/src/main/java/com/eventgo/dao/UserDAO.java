package com.eventgo.dao;

import com.eventgo.connection.DBConnection;
import com.eventgo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public void registerUser(User user) {

        String query = "INSERT INTO users(name, email, phone) VALUES (?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("User Registered Successfully!");
            } else {
                System.out.println("Registration Failed!");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
