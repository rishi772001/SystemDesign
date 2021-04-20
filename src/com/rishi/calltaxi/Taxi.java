package com.rishi.calltaxi;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    private int id;
    private String pos;
    private int totalEarnings;
    private Boolean isFree;
    private List<Booking> bookings = new ArrayList<>();

    Taxi(int id){
        this.id = id;
        this.pos = "a";
        this.totalEarnings = 0;
        this.isFree = true;
    }

    public int getId() {
        return id;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(int totalEarnings) {
        this.totalEarnings += totalEarnings;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Booking book) {
        this.bookings.add(book);
    }
}
