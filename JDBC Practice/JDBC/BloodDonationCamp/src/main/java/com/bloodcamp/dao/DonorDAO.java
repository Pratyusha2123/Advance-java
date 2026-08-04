package com.bloodcamp.dao;

import com.bloodcamp.connection.DBConnection;
import com.bloodcamp.model.Donor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DonorDAO {

    public void registerDonor(Donor donor) {

        String sql = "INSERT INTO donor(name, age, blood_group, phone) VALUES(?,?,?,?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, donor.getName());
            ps.setInt(2, donor.getAge());
            ps.setString(3, donor.getBloodGroup());
            ps.setString(4, donor.getPhone());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Donor Registered Successfully.");
            } else {
                System.out.println("Registration Failed.");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDonors() {

        String sql = "SELECT * FROM donor";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            boolean found = false;

            System.out.println("\n========= DONOR LIST =========");

            while (rs.next()) {
                found = true;

                System.out.println("ID : " + rs.getInt("donor_id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Blood Group : " + rs.getString("blood_group"));
                System.out.println("Phone : " + rs.getString("phone"));
                System.out.println("----------------------------");
            }
            if (!found) {
                System.out.println("No Donors Found.");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDonor(Donor donor) {

        String sql = "UPDATE donor SET name=?, age=?, blood_group=?, phone=? WHERE donor_id=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, donor.getName());
            ps.setInt(2, donor.getAge());
            ps.setString(3, donor.getBloodGroup());
            ps.setString(4, donor.getPhone());
            ps.setInt(5, donor.getDonorId());

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Donor Updated Successfully.");
            } else {
                System.out.println("Donor Not Found.");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDonor(int donorId) {

        String sql = "DELETE FROM donor WHERE donor_id=?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donorId);

            int row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Donor Deleted Successfully.");
            } else {
                System.out.println("Donor Not Found.");
            }
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void batchRegisterDonors(Donor[] donors) {

        String sql = "INSERT INTO donor(name, age, blood_group, phone) VALUES(?,?,?,?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            for (Donor donor : donors) {

                ps.setString(1, donor.getName());
                ps.setInt(2, donor.getAge());
                ps.setString(3, donor.getBloodGroup());
                ps.setString(4, donor.getPhone());

                ps.addBatch();
            }
            ps.executeBatch();

            System.out.println("Batch Registration Successful.");
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

