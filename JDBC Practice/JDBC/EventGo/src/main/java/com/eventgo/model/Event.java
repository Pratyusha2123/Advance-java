package com.eventgo.model;

import java.sql.Date;
import java.sql.Time;

public class Event {

    private int eventId;
    private String eventName;
    private String venue;
    private Date eventDate;
    private Time eventTime;
    private int availablePasses;
    private double ticketPrice;

    public Event() {
    }

    public Event(int eventId, String eventName, String venue, Date eventDate,
                 Time eventTime, int availablePasses, double ticketPrice) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.venue = venue;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.availablePasses = availablePasses;
        this.ticketPrice = ticketPrice;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public int getAvailablePasses() {
        return availablePasses;
    }

    public void setAvailablePasses(int availablePasses) {
        this.availablePasses = availablePasses;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
