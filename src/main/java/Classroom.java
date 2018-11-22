package main.java;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    public int number;
    public int capacity;
    public List<String> facility;
    public List<Time> timeTaken;

    public Classroom(int number, int capacity, String[] facility){
        this.number = number;
        this.capacity = capacity;
        this.facility = new ArrayList<>();
        for(int i = 0;i < facility.length;i++){
            this.facility.add(facility[i]);
        }
    }

    public Classroom(int number, int capacity, List<String> facility){
        this.number = number;
        this.capacity = capacity;
        this.facility = facility;
    }

    public void insertTimeTaken(List<Time> timeTaken){
        //Implementation
    }
}