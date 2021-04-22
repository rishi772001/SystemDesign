package com.rishi.calltaxi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Reserve {
    public static int n = 4;
    public static int len = 6;

    public static Taxi[] taxies = new Taxi[4];
    private static HashMap<Integer, Character> points = new HashMap<>();
    private static Map<Integer, List<Taxi>> positions = new HashMap<>();

    Reserve()
    {

        for(int i = 0; i < 6; i++) {
            positions.put(i, new ArrayList<Taxi>());
            points.put(i, (char)(97 + i));
        }
        for(int i = 0; i < n; i++) {
            taxies[i] = new Taxi(i);
            setPositions(0, taxies[i]);
        }
    }

    public static void setPositions(int i, Taxi t)
    {
        positions.get(i).add(t);
    }

    public static void updatePositions(int remove, int add, Taxi t)
    {
        positions.get(remove).remove(t);
        positions.get(add).add(t);
    }

    public static Map<Integer, List<Taxi>> getPositions()
    {
        return positions;
    }

    public static HashMap<Integer, Character> getPoints()
    {
        return points;
    }

    public void showTaxies() {
        for(int i = 0; i < n; i++)
        {
            System.out.println("----------------------------------");
            Taxi taxi = taxies[i];
            System.out.print("Taxi Number:   " + taxi.getTaxiId() + "\t");
            System.out.print("Current Position:   " + (char)(taxi.getPos() + 97) + "\t");
            System.out.println("Total Earnings:   " + taxi.getTotalEarnings());

            System.out.println("BookingId \t CustomerId \t From \t To \t PickupTime \t DropTime \t Earnings");

            for(Booking b: taxi.getBookings())
            {
                System.out.println( b.getCustomerId() + "\t" + b.getFrom() + "\t" + b.getTo() + "\t" + b.getPickupTime() + "\t" + b.getDropTime() +"\t"+ b.getEarnings());
            }

        }
    }

    public void showBookings() {
        System.out.println("----------------------------------");
        System.out.println("BookingId \t CustomerId \t From \t To \t PickupTime \t DropTime \t Earnings");

        for(int i = 0; i < n; i++)
        {
            Taxi taxi = taxies[i];
            for(Booking b: taxi.getBookings())
            {
                System.out.println( b.getCustomerId() + "\t" + b.getFrom() + "\t" + b.getTo() + "\t" + b.getPickupTime() + "\t" + b.getDropTime() +"\t"+ b.getEarnings());
            }

        }
    }
}
