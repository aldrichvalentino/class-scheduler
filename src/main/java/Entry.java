package main.java;

import java.util.List;

public class Entry {
    public Lecturer teacher;
    public Classroom classroom;
    public Course course;
    public List<Time> time;

    public Entry(Lecturer teacher, Classroom classroom, Course course, List<Time> time){
        this.teacher = teacher;
        this.classroom = classroom;
        this.course = course;
        this.time = time;
    }
}
