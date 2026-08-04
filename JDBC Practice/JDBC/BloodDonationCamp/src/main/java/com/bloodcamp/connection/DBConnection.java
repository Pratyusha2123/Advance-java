package com.bloodcamp.connection;

import java.sql.Connection;//physical connection to the url
import java.sql.DriverManager;

public class DBConnection { // handle the database connectivty

    private static final String URL = "jdbc:mysql://localhost:3306/BloodDonationCamp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "SS2003";
    public static Connection getConnection() { // return the live connection object
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
