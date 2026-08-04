package com.bloodcamp.dao;

import com.bloodcamp.connection.DBConnection;
import com.bloodcamp.model.Slot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

public class SlotDAO {

//adding  (Transaction or Savepoint)
    public void bookSlot(Slot slot) {

        String sql = "INSERT INTO slot_booking(donor_id, donation_date, donation_time) VALUES(?,?,?)";

        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);
            Savepoint sp = con.setSavepoint();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, slot.getDonorId());
            ps.setString(2, slot.getDonationDate());
            ps.setString(3, slot.getDonationTime());

            int row = ps.executeUpdate();

            if (row > 0) {

                con.commit();
                System.out.println("Slot Booked Successfully.");

            } else {
                con.rollback(sp);
                System.out.println("Booking Failed.");
            }
            ps.close();
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void cancelSlot(int bookingId) {

        String sql = "DELETE FROM slot_booking WHERE booking_id=?";

        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bookingId);
            int row = ps.executeUpdate();
            if (row > 0) {
                con.commit();
                System.out.println("Slot Cancelled Successfully.");

            } else {
                con.rollback();
                System.out.println("Booking ID Not Found.")
            }
            ps.close();
            con.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
