package main.java;
import java.util.List;

public class Course {
    public String name;
    public int credit;
    public List<String> requirement;

    public Course(String name, int credit, List<String> requirement){
        this.name = name;
        this.credit = credit;
        this.requirement = requirement;
    }
}
