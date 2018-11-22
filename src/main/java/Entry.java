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
            output += "| ";
            int bound = 9 - time.get(i).day.length();
            String day = time.get(i).day;
            output += day.substring(0, 1).toUpperCase() + day.substring(1);
            for(int j = 0; j < bound; j++) {
                output += " ";
            }
            output += " | ";
            String hour = "";
            if (time.get(i).time < 10)
                hour = "0" + Integer.toString(time.get(i).time) + ".00";
            else
                hour = Integer.toString(time.get(i).time) + ".00";
            output += hour + " | " + Integer.toString(classroom.number) + " " + course.name + " " + teacher.name + "\n";
        }
        return output;
    }
}
