package com.rishi.calltaxi;

import java.util.*;

public class Main {

    public static int counter = 0;
    public static Scanner inp = new Scanner(System.in);


    public static void main(String[] args) {
        // Initialize variables
        int choice; // choice of 4

        // Declare Objects
        Reserve reserve = new Reserve();


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
                    System.out.println("Enter Customer Id");
                    int custId = inp.nextInt();
                    Customer customer = new Customer(custId);
                    Taxi taxi = customer.book();
                    if(taxi == null)
                        System.out.println("Sorry no taxi found!!");
                    else
                        System.out.println("Your taxi is booked, taxi id = " + taxi.getTaxiId());
                    break;
                case 2:
                    reserve.showBookings();
                    break;
                case 3:
                    reserve.showTaxies();
                    break;
                case 4:
                    System.out.println("Thank you!!!");
                    System.exit(0);
                    break;
            }
        }
    }




}
