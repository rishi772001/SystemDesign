package com.rishi.calltaxi;

public class Booking {
    private int customerId;
    private int from;
    private int to;
    private int pickupTime;
    private int dropTime;
    private int earnings;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getDropTime() {
        return dropTime;
    }

    public void setDropTime(int dropTime) {
        this.dropTime = dropTime;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public void setBooking(int pickup, int drop, int time, int custId)
    {
        setCustomerId(custId);
        setFrom(pickup);
        setTo(drop);
        setPickupTime(time);
        int distance = pickup + drop;
        int dropTime = time + distance;
        int earnings = (((distance * 15) - 5) * 10) + 100;

        setDropTime(dropTime);
        setEarnings(earnings);
    }
}
