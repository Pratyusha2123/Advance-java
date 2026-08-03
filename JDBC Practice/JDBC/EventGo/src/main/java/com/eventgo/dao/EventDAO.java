package com.eventgo.dao;

import com.eventgo.connection.DBConnection;
import com.eventgo.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventDAO {

    public void addEvent(Event event) {

        String query = "INSERT INTO events(event_name, venue, event_date, event_time, available_passes, ticket_price) VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, event.getEventName());
            ps.setString(2, event.getVenue());
            ps.setDate(3, event.getEventDate());
            ps.setTime(4, event.getEventTime());
            ps.setInt(5, event.getAvailablePasses());
            ps.setDouble(6, event.getTicketPrice());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Event Added Successfully!");
            } else {
                System.out.println("Failed to Add Event!");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllEvents() {

        String query = "SELECT * FROM events";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== EVENT LIST ==========");

            while (rs.next()) {

                System.out.println("Event ID : " + rs.getInt("event_id"));
                System.out.println("Event Name : " + rs.getString("event_name"));
                System.out.println("Venue : " + rs.getString("venue"));
                System.out.println("Date : " + rs.getDate("event_date"));
                System.out.println("Time : " + rs.getTime("event_time"));
                System.out.println("Available Passes : " + rs.getInt("available_passes"));
                System.out.println("Ticket Price : " + rs.getDouble("ticket_price"));
                System.out.println("--------------------------------");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}