package com.rishi.railway;

public class Passenger {
    public int age, preference;
    public String name, gender;
    Passenger(String name, int age, String gender, int preference)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
        if(age >= 60)
            this.preference = 0;
        else
            this.preference = preference;
    }
}
