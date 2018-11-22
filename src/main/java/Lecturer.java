package main.java;

import java.util.ArrayList;
import java.util.List;

public class Lecturer {
    public String name;
    public List<Time> preference;

    public Lecturer(String name){
        this.name = name;
        preference = new ArrayList<>();
    }

    public Lecturer(String name, ArrayList<Time> preference) {
        super();
        this.name = name;
        this.preference = new ArrayList<Time>();
        this.preference.addAll(preference);
    }

    public void setPreference(List<Time> time){
        preference.addAll(time);
    }

    public void assign(List<Time> time){
        preference.removeAll(time);
    }
}
