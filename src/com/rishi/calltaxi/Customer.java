package com.rishi.calltaxi;

import java.util.*;

public class Customer {
    int custId;
    public static Scanner inp = new Scanner(System.in);
    
    Customer(int id)
    {
        this.custId = id;
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
            Booking book = new Booking();
            book.setBooking(pickup, drop, time, custId);
            taxi = Taxi.setTaxi(taxi, pickup, drop, time, book);
        }
        return taxi;
    }

    public Taxi book() {
        int time;
        int pickup, drop;
        // booking taxi
        Taxi taxi = null;
        
        System.out.println("Enter Pickup Point");
        pickup = (int) (inp.next().charAt(0)) - 97;

        do {
            System.out.println("Enter Drop Point(not same as pickup)");
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
                    Booking book = new Booking();
                    book.setBooking(pickup, drop, time, custId);
                    taxi = Taxi.setTaxi(t, pickup, drop, time, book);
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
