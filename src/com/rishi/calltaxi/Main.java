package com.rishi.calltaxi;

import java.util.*;

public class Main {

    public static int n = 4; // no of taxies
    public static int counter = 0;
    public static Taxi[] taxies = new Taxi[n];
    public static Scanner inp = new Scanner(System.in);
    public static String[] points = {"a", "b", "c", "d", "e", "f"};
    public static Map<String, List<Taxi>> positions = new HashMap<>();

    Main(){

        for(int i = 0; i < n; i++)
        {
            taxies[i] = new Taxi(i);
            positions.put(points[i], new ArrayList<Taxi>());
        }
    }

    public static void main(String[] args) {
        // Initialize variables
        int choice; // choice of 4
        int i = 0; // iterator

        // Show options
        System.out.println("-----CALL TAXI BOOKING-----");
        System.out.println("1. Booking");
        System.out.println("2. Booking Details");
        System.out.println("3. Taxi Details");
        System.out.println("4. Exit");
        // Get choice
        System.out.print("Enter Your choice");
        while(true)
        {
            counter++;
            choice = inp.nextInt();
            switch (choice){
                case 1:
                    book();
                    break;
                case 2:
                    showBookings();
                    break;
                case 3:
                    showTaxies();
                    break;
                case 4:
                    System.out.println("Thank you!!!");
                    System.exit(1);
                    break;
            }
        }
    }

    private static void showTaxies() {
        for(int i = 0; i < n; i++)
        {
            System.out.print("----------------------------------");
            Taxi taxi = taxies[i];
            System.out.print("Taxi Number:   " + taxi.getId() + "\t");
            System.out.print("Current Position:   " + taxi.getPos() + "\t");
            System.out.println("Total Earnings:   " + taxi.getTotalEarnings());

            System.out.println("BookingId \t CustomerId \t From \t To \t PickupTime \t DropTime \t Earnings");

            for(Booking b: taxi.getBookings())
            {
                System.out.println(b.getBookingId() + "\t" + b.getCustomerId() + "\t" + b.getFrom() + "\t" + b.getTo() + "\t" + b.getPickupTime() + "\t" + b.getDropTime() +"\t"+ b.getEarnings());
            }

        }
    }

    private static void showBookings() {
        System.out.print("----------------------------------");
        System.out.println("BookingId \t CustomerId \t From \t To \t PickupTime \t DropTime \t Earnings");

        for(int i = 0; i < n; i++)
        {
            Taxi taxi = taxies[i];
            for(Booking b: taxi.getBookings())
            {
                System.out.println(b.getBookingId() + "\t" + b.getCustomerId() + "\t" + b.getFrom() + "\t" + b.getTo() + "\t" + b.getPickupTime() + "\t" + b.getDropTime() +"\t"+ b.getEarnings());
            }

        }
    }

    private static void book() {
        int custId, time;
        String pickup, drop;
        System.out.println("Enter Customer Id");
        custId = inp.nextInt();
        System.out.println("Enter Pickup Point");
        pickup = inp.next();
        System.out.println("Enter Drop Point");
        drop = inp.next();
        System.out.println("Enter Pickup Time");
        time = inp.nextInt();
        if(positions.get(pickup).size() > 0){
            List<Taxi> curr = positions.get(pickup);
            Taxi taxi;
            for(Taxi t : curr)
            {
                if(t.getFree())
                {
                    t.setFree(false);
                    Booking book = new Booking();
                    book.setBookingId(counter);
                    book.setCustomerId(custId);
                    book.setFrom(pickup);
                    book.setTo(drop);
                    book.setPickupTime(time);

                    int distance = Integer.parseInt(pickup) + Integer.parseInt(drop);
                    int dropTime = time + distance;
                    int earnings = (((distance * 15) - 5) * 10) + 100;

                    book.setDropTime(dropTime);
                    book.setEarnings(earnings);
                    t.setBookings(book);
                }
            }
        }
        // no taxi in current pos
        else {
            Boolean flag = false;
            String i = pickup;

        }
    }
}
