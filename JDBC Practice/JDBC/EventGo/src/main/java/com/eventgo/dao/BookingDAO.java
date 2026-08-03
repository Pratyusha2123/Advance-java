package com.eventgo.dao;

import com.eventgo.connection.DBConnection;
import java.sql.*;

public class BookingDAO {

    public void bookPass(int userId, int eventId, int quantity) {
        String checkPassesQuery = "SELECT available_passes, ticket_price FROM events WHERE event_id = ?";
        String updatePassesQuery = "UPDATE events SET available_passes = available_passes - ? WHERE event_id = ?";
        String insertBookingQuery = "INSERT INTO bookings (user_id, event_id, quantity, total_amount, booking_date) VALUES (?, ?, ?, ?, CURDATE())";

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            if (con == null) return;

            // 1. Transaction Start
            con.setAutoCommit(false);

            // Check available passes and price
            PreparedStatement pstCheck = con.prepareStatement(checkPassesQuery);
            pstCheck.setInt(1, eventId);
            ResultSet rs = pstCheck.executeQuery();

            if (rs.next()) {
                int availablePasses = rs.getInt("available_passes");
                double ticketPrice = rs.getDouble("ticket_price");

                if (availablePasses < quantity) {
                    System.out.println(" Booking Failed: Not enough passes available!");
                    con.rollback();
                    return;
                }

                double totalAmount = ticketPrice * quantity;

                // 2. Set Savepoint before multi-step update
                Savepoint sp = con.setSavepoint("BeforeBookingSavepoint");

                try {
                    // Update available passes in events table
                    PreparedStatement pstUpdate = con.prepareStatement(updatePassesQuery);
                    pstUpdate.setInt(1, quantity);
                    pstUpdate.setInt(2, eventId);
                    pstUpdate.executeUpdate();

                    // Insert booking record
                    PreparedStatement pstInsert = con.prepareStatement(insertBookingQuery);
                    pstInsert.setInt(1, userId);
                    pstInsert.setInt(2, eventId);
                    pstInsert.setInt(3, quantity);
                    pstInsert.setDouble(4, totalAmount);
                    pstInsert.executeUpdate();

                    // 3. Commit Transaction if everything is successful
                    con.commit();
                    System.out.println(" Pass Booked Successfully! Total Amount: " + totalAmount);

                } catch (Exception e) {
                    // Rollback to savepoint if inner execution fails
                    con.rollback(sp);
                    System.out.println(" Error during booking process, rolled back to savepoint!");
                    e.printStackTrace();
                }

            } else {
                System.out.println("Event not found!");
            }

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Complete rollback on major error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Reset auto-commit
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void viewMyBookings(int userId) {
        String query = "SELECT b.booking_id, e.event_name, e.venue, b.quantity, b.total_amount, b.booking_date " +
                "FROM bookings b JOIN events e ON b.event_id = e.event_id WHERE b.user_id = ?";

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return;

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== YOUR BOOKINGS HISTORY ==========");
            boolean hasBookings = false;

            while (rs.next()) {
                hasBookings = true;
                System.out.println("Booking ID     : " + rs.getInt("booking_id"));
                System.out.println("Event Name     : " + rs.getString("event_name"));
                System.out.println("Venue          : " + rs.getString("venue"));
                System.out.println("Passes Booked  : " + rs.getInt("quantity"));
                System.out.println("Total Amount   : " + rs.getDouble("total_amount"));
                System.out.println("Booking Date   : " + rs.getDate("booking_date"));
                System.out.println("-------------------------------------------");
            }

            if (!hasBookings) {
                System.out.println("You have not booked any passes yet!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cancelBooking(int bookingId, int userId) {
        String getBookingQuery = "SELECT event_id, quantity FROM bookings WHERE booking_id = ? AND user_id = ?";
        String deleteBookingQuery = "UPDATE events SET available_passes = available_passes + ? WHERE event_id = ?";
        String removeBookingQuery = "DELETE FROM bookings WHERE booking_id = ?";

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            if (con == null) return;

            con.setAutoCommit(false);

            PreparedStatement pstGet = con.prepareStatement(getBookingQuery);
            pstGet.setInt(1, bookingId);
            pstGet.setInt(2, userId);
            ResultSet rs = pstGet.executeQuery();

            if (rs.next()) {
                int eventId = rs.getInt("event_id");
                int quantity = rs.getInt("quantity");

                // Restore passes to event
                PreparedStatement pstUpdate = con.prepareStatement(deleteBookingQuery);
                pstUpdate.setInt(1, quantity);
                pstUpdate.setInt(2, eventId);
                pstUpdate.executeUpdate();

                // Delete booking record
                PreparedStatement pstDelete = con.prepareStatement(removeBookingQuery);
                pstDelete.setInt(1, bookingId);
                pstDelete.executeUpdate();

                con.commit();
                System.out.println(" Booking Cancelled Successfully & Passes Restored!");

            } else {
                System.out.println(" Booking Not Found or Unauthorized!");
                con.rollback();
            }

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}