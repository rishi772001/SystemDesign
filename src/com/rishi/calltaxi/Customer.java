package com.rishi.calltaxi;

import java.util.*;

public class Customer {
    int custId;
    public static Scanner inp = new Scanner(System.in);
    
    Customer(int id)
    {
        this.custId = id;
    }

    private Booking setBooking(int pickup, int drop, int time)
    {
        Booking book = new Booking();
        book.setCustomerId(this.custId);
        book.setFrom(pickup);
        book.setTo(drop);
        book.setPickupTime(time);
        int distance = pickup + drop;
        int dropTime = time + distance;
        int earnings = (((distance * 15) - 5) * 10) + 100;

        book.setDropTime(dropTime);
        book.setEarnings(earnings);
        return book;
    }

    private Taxi allocateTaxi(Map<Integer, List<Taxi>> positions, int pickup, int drop, int time, int i, int j)
    {
        List<Taxi> available = new ArrayList<>();
        if(i != -1 && positions.get(i - 1).size() > 0)
            available.addAll(positions.get(i - 1));
        else if(j != -1 && positions.get(j + 1).size() > 0)
            available.addAll(positions.get(j + 1));
        else
            return null;

        Taxi taxi = null;

        available.removeIf(t -> !t.getFree());
        int mini = Integer.MAX_VALUE;

        if(available.size() > 0){
            for(Taxi t : available)
            {
                if(t.getTotalEarnings() < mini)
                {
                    mini = t.getTotalEarnings();
                    taxi = t;
                    break;
                }
            }
        }
        if(taxi != null)
        {
            taxi = setTaxi(taxi, pickup, drop, time);
        }
        return taxi;
    }
    private Taxi setTaxi(Taxi taxi, int pickup, int drop, int time)
    {
        taxi.setFree(false);
        int distance = pickup + drop;
        int earnings = (((distance * 15) - 5) * 10) + 100;
        taxi.setTotalEarnings(earnings);
        Booking book = setBooking(pickup, drop, time);
        taxi.setBookings(book);
        return taxi;
    }
    public Taxi book() {
        int time;
        int pickup, drop;
        // booking taxi
        Taxi taxi = null;
        
        System.out.println("Enter Pickup Point");
        pickup = (int) (inp.next().charAt(0)) - 97;
        System.out.println("Enter Drop Point(not same as pickup)");
        do {
            drop = (int) (inp.next().charAt(0)) - 97;
        }while (pickup == drop);

        System.out.println("Enter Pickup Time");
        time = inp.nextInt();

        Map<Integer, List<Taxi>> positions = Reserve.getPositions();

        if(positions.get(pickup).size() > 0){
            List<Taxi> curr = positions.get(pickup);

            for(Taxi t : curr)
            {
                if(t.getFree())
                {
                    taxi = setTaxi(t, pickup, drop, time);
                    return taxi;
                }
            }
        }
        // no taxi in current pos
        else {
            int i = pickup;
            int j = pickup;

            while ( i - 1 >= 0 && j + 1 <= 5)
            {
                taxi = allocateTaxi(positions, pickup, drop, time, i, j);
                if(taxi != null)
                    return taxi;
                i--;
                j++;
            }

            while ( i - 1 >= 0 )
            {
                taxi = allocateTaxi(positions, pickup, drop, time, i, -1);
                if(taxi != null)
                    return taxi;
                i--;
            }
            while ( j + 1 <= 5 )
            {
                taxi = allocateTaxi(positions, pickup, drop, time, -1, j);
                if(taxi != null)
                    return taxi;
                j++;
            }
        }
        return taxi;
    }
}
