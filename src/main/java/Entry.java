package main.java;

import java.util.List;
import java.util.ArrayList;

public class Entry {
    public String teacher;
    public String classroom;
    public String course;
    public List<Time> time;

    public Entry(String teacher, String classroom, String course, ArrayList<Time> time) {
        super();
        this.teacher = teacher;
        this.classroom = classroom;
        this.course = course;
        this.time = new ArrayList<Time>();
        this.time.addAll(time);
    }
}
