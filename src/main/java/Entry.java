package main.java;

import java.util.List;
import java.util.ArrayList;

public class Entry {
    public Lecturer teacher;
    public Classroom classroom;
    public Course course;
    public List<Time> time;

    public Entry(Lecturer teacher, Classroom classroom, Course course, ArrayList<Time> time) {
        super();
        this.teacher = teacher;
        this.classroom = classroom;
        this.course = course;
        this.time = new ArrayList<Time>();
        this.time.addAll(time);
    }

    public String toString() {
        String output = "";
        for(int i=0; i < time.size(); i++) {
            output += "| " + time.get(i).day + " | " + Integer.toString(time.get(i).time) + " | ";
            output += Integer.toString(classroom.number) + " " + course.name + " " + teacher.name + "\n";
        }
        return output;
    }
}
