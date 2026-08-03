package com.eventgo.model;

import java.sql.Timestamp;

public class Booking {

    private int bookingId;
    private int userId;
    private int eventId;
    private int numberOfPasses;
    private double totalPrice;
    private Timestamp bookingDate;

    public Booking() {
    }

    public Booking(int bookingId, int userId, int eventId, int numberOfPasses, double totalPrice, Timestamp bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.numberOfPasses = numberOfPasses;
        this.totalPrice = totalPrice;
        this.bookingDate = bookingDate;
    }
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getNumberOfPasses() {
        return numberOfPasses;
    }

    public void setNumberOfPasses(int numberOfPasses) {
        this.numberOfPasses = numberOfPasses;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }
}
