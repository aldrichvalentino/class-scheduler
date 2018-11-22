package main.java;

import java.util.ArrayList;
import java.util.List;

public class Teach {
    public String teacher;
    public String classroom;
    public String course;
    public int capacity;
    public List<Time> time;

    public Teach(String teacher, String classroom, String course, int capacity, ArrayList<Time> time) {
        super();
        this.teacher = teacher;
        this.classroom = classroom;
        this.course = course;
        this.capacity = capacity;
        this.time = new ArrayList<Time>();
        this.time.addAll(time);
    }
}
