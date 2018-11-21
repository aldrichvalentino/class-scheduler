package main.java;

import java.util.ArrayList;
import java.util.List;

public class Lecturer {
    public String name;
    public List<Time> preference;
    public List<String> constraint;

    public Lecturer(String name, int day, int[] time, String[] constraint){
        this.name = name;
        this.preference = new ArrayList<>();
        this.constraint = new ArrayList<>();
        for(int i = 0; i < time.length;i++){
            this.preference.add(new Time(day, time[i]));
        }
        for(int i = 0; i < constraint.length;i++){
            this.constraint.add(constraint[i]);
        }
    }

    public Lecturer(String name, int day, List<Integer> time, List<String> constraint){
        this.name = name;
        this.constraint = constraint;
        this.preference = new ArrayList<>();
        for(int i = 0; i < time.size();i++){
            this.preference.add(new Time(day, time.get(i)));
        }
    }

    public boolean assign(List<Time> time){
        //Return false if failed to assign time
        boolean canAssign = true;
        int[] assignIdx = new int[time.size()];
        for(int i = 0; i < time.size();i++){
            for(int j = 0; j < preference.size();j++){
                if (time.get(i).day == preference.get(j).day && time.get(i).time == preference.get(j).time){
                    assignIdx[i] = j;
                } else {
                    canAssign = false;
                    break;
                }
            }
        }
        if(canAssign){
            for(int i=0; i < assignIdx.length; i++){
                preference.remove(assignIdx[i]);
            }
        }
        return canAssign;
    }
}
