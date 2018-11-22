package main.java;
import java.util.ArrayList;
import java.util.List;

public class Course {
    public String name;
    public int credit;
    public List<String> requirement;

    public Course(String name){
        this.name = name;
        this.credit = 0;
        this.requirement = new ArrayList<>();
    }

    public Course(String name, int credit, List<String> requirement){
        this.name = name;
        this.credit = credit;
        this.requirement = requirement;
    }

    public void setCredit(int credit){
        this.credit = credit;
    }

    public void setRequirement(List<String> requirement){
        this.requirement.addAll(requirement);
    }
}
