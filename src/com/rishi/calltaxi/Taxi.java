package com.rishi.calltaxi;

import java.util.ArrayList;
import java.util.List;

public class Taxi extends Thread{
    private int id;
    private int pos;
    private int totalEarnings;
    private Boolean isFree;

    private int currpickup = -1, currdrop = -1;


    private List<Booking> bookings = new ArrayList<>();

    Taxi(int id){
        this.id = id;
        this.pos = 0;
        this.totalEarnings = 0;
        this.isFree = true;
    }

    public int getTaxiId() {
        return id;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
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

    public static Taxi setTaxi(Taxi taxi, int pickup, int drop, int time, Booking book)
    {
        taxi.setFree(false);
        taxi.currpickup = pickup;
        taxi.currdrop = drop;
        int distance = pickup + drop;
        int earnings = (((distance * 15) - 5) * 10) + 100;
        taxi.setTotalEarnings(earnings);
        taxi.setBookings(book);
        Thread th = new Thread();
        th.start();
        return taxi;
    }

    @Override
    public void run()
    {
        while(pos > currpickup)
        {
            try {
                Taxi.sleep(1000);
                Reserve.updatePositions(pos, --pos, this);
            } catch (Exception e) {
                System.out.println(e);;
            }
        }
        while(pos < currpickup)
        {
            try {
                Taxi.sleep(1000);
                Reserve.updatePositions(pos, ++pos, this);
            } catch (Exception e) {
                System.out.println(e);;
            }
        }
        while(pos < currdrop)
        {
            try {
                Taxi.sleep(1000);
                Reserve.updatePositions(pos, ++pos, this);
            } catch (Exception e) {
                System.out.println(e);;
            }
        }
        while(pos > currdrop)
        {
            try {
                Taxi.sleep(1000);
                Reserve.updatePositions(pos, --pos, this);
            } catch (Exception e) {
                System.out.println(e);;
            }
        }
        this.setFree(true);

    }

}
