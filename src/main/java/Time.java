package main.java;

public class Time {
    public String day;
    public Integer time;

    public Time(String day, Integer time){
        this.day = day;
        this.time = time;
    }

    public boolean equals(Time other){
        return (day.equals(other.day) && time.equals(other.time));
    }
}
