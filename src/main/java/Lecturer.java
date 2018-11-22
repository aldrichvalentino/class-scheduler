package main.java;

import java.util.ArrayList;
import java.util.List;

public class Lecturer {
    public String name;
    public List<Time> preference;

    public Lecturer(String name, ArrayList<Time> preference) {
        super();
        this.name = name;
        this.preference = new ArrayList<Time>();
        this.preference.addAll(preference);
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
