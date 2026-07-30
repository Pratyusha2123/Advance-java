package com.demoAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

public class AccountDAO {

    public void moneyTransfer() {

        try {

            Connection con = DBConnection.getConnection();

            // Auto Commit OFF
            con.setAutoCommit(false);

            // Deduct money from Rahul
            PreparedStatement ps1 = con.prepareStatement(
                    "UPDATE Account SET balance = balance - ? WHERE id=?");

            ps1.setDouble(1, 2000);
            ps1.setInt(2, 101);
            ps1.executeUpdate();

            // Savepoint
            Savepoint sp = con.setSavepoint("AfterDebit");

            // Add money to Priya
            PreparedStatement ps2 = con.prepareStatement(
                    "UPDATE Account SET balance = balance + ? WHERE id=?");

            ps2.setDouble(1, 2000);
            ps2.setInt(2, 102);
            ps2.executeUpdate();

            // Rollback to Savepoint
            con.rollback(sp);

            // Commit
            con.commit();

            System.out.println("Transaction Completed");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
