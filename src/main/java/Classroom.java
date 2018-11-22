package main.java;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    public int number;
    public int capacity;
    public List<String> facility;
    public List<Time> timeTaken;

    public Classroom(int number){
        this.number = number;
        this.capacity = 0;
        this.facility = new ArrayList<>();
        this.timeTaken = new ArrayList<>();
    }

    public Classroom(int number, int capacity, String[] facility){
        this.number = number;
        this.capacity = capacity;
        this.facility = new ArrayList<>();
        for(int i = 0;i < facility.length;i++){
            this.facility.add(facility[i]);
        }
        this.timeTaken = new ArrayList<>();
    }

    public Classroom(int number, int capacity, List<String> facility){
        this.number = number;
        this.capacity = capacity;
        this.facility = facility;
        this.timeTaken = new ArrayList<>();
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setFacility(List<String> facility){
        this.facility.addAll(facility);
    }

    public void insertTimeTaken(List<Time> timeTaken){
        this.timeTaken.addAll(timeTaken);
    }

    public boolean equals(String number){
        return (Integer.parseInt(number) == this.number);
    }
}
