package com.rishi.railway;

import com.rishi.calltaxi.Customer;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        System.out.println("Enter choice");
        System.out.println("1. Book");
        System.out.println("2. Cancel");
        System.out.println("3. Print booked tickets");
        System.out.println("4. print available tickets");
        choice = sc.nextInt();

        Train train = new Train();


        switch (choice)
        {
            case 1:
                String name, gender;
                int age, preference;
                sc.nextLine();
                System.out.println("Enter Name");
                name = sc.nextLine();
                System.out.println("Enter Gender(M/F)");
                gender = sc.next();
                System.out.println("Enter Age");
                age = sc.nextInt();
                System.out.println("Enter berth preference");
                String temp = sc.next().toLowerCase();
                switch (temp)
                {
                    case "u":
                        preference = 2;
                        break;
                    case "l":
                        preference = 0;
                        break;
                    default:
                        preference = 1;
                        break;
                }
                Passenger passenger = new Passenger(name, age, gender, preference);
                train.book(passenger);
                break;
            case 2:
                // cancel
                break;
            case 3:
                // print booked
                break;
            case 4:
                // print available
                break;
        }
    }
}
