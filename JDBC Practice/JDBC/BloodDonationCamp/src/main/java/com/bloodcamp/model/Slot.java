package com.bloodcamp.model;

public class Slot {

    private int bookingId;
    private int donorId;
    private String donationDate;
    private String donationTime;

    public Slot() {
    }

    public Slot(int donorId, String donationDate, String donationTime) {
        this.donorId = donorId;
        this.donationDate = donationDate;
        this.donationTime = donationTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(String donationTime) {
        this.donationTime = donationTime;
    }
}
