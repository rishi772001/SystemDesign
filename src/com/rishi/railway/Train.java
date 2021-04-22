package com.rishi.railway;

import com.rishi.calltaxi.Booking;

import java.util.HashMap;
import java.util.Map;

public class Train {
/*
coach details
no  of births
seat map

book
cancel

There are a total of 63 berths for 63 confirmed tickets,
9 berths for 18 RAC tickets and 10 tickets in waiting-list.
If the waiting-list ticket count goes above 10,
print as ‘No tickets available’.

1...72
 */

    public Map<Integer, Integer> rac = new HashMap<>();
    public Map<Integer, Boolean> sideUpper = new HashMap<>();
    public Map<Integer, Boolean> upper = new HashMap<>();
    public Map<Integer, Boolean> middle = new HashMap<>();
    public Map<Integer, Boolean> lower = new HashMap<>();

    Train()
    {
        for(int i = 1; i < 73; i++)
        {
            if(i % 8 == 0){
                this.rac.put(i, 0);
            }
            if(i % 8 == 7){
                this.sideUpper.put(i, false);
            }
            if(i % 8 == 1 || i % 8 == 4){
                this.upper.put(i, false);
            }
            if(i % 8 == 2 || i % 8 == 5){
                this.middle.put(i, false);
            }
            if(i % 8 == 3 || i % 8 == 6){
                this.lower.put(i, false);
            }
        }
    }

    public int checkLower()
    {
        for (int seatNo: lower.keySet()) {
            Boolean state = lower.get(seatNo);

            if(!state)
            {
                lower.put(seatNo,true);
                return seatNo;
            }
        }
        return -1;
    }

    public int checkMiddle()
    {

        for (int seatNo: middle.keySet()) {
            Boolean state = middle.get(seatNo);

            if(!state)
            {
                middle.put(seatNo,true);
                return seatNo;
            }
        }
        return -1;
    }

    public int checkUpper()
    {
        for (int seatNo: upper.keySet()) {
            Boolean state = upper.get(seatNo);

            if(!state)
            {
                upper.put(seatNo,true);
                return seatNo;
            }
        }
        return -1;
    }


    public void book(Passenger p)
    {
        Boolean flag = false;
        int passengerSeatNo = -1;
        if(p.preference == 0)
        {

        }
        if(p.preference == 1)
        {
            passengerSeatNo = checkMiddle();
        }
        if(p.preference == 2)
        {
            passengerSeatNo = checkUpper();
        }
        if(!flag)
        {
            for (int seatNo: sideUpper.keySet()) {
                Boolean state = sideUpper.get(seatNo);

                if(!state)
                {
                    sideUpper.put(seatNo,true);
                    flag = true;
                }
            }
        }
        if(!flag)
        {
            for (int seatNo: rac.keySet()) {
                int state = rac.get(seatNo);

                if(state == 0)
                {
                    rac.put(seatNo,1);
                    flag = true;
                }
                if(state == 1)
                {
                    rac.put(seatNo,2);
                    flag = true;
                }
            }
        }
    }
    public void cancel()
    {

    }
}
