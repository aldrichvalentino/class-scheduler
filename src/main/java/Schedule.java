package main.java;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<Entry>[] content;

    public Schedule(int numOfDay){
        content = new List[numOfDay];
        for(int i = 0; i < numOfDay;i++){
            content[i] = new ArrayList<>();
        }
    }

    public void insertEntry(Lecturer teacher, Classroom classroom, Course course, int capacity, List<Time> teachesTime) {
        boolean valid = true;
        boolean localValid = true;

        ArrayList<Time>[] timeByDay = splitTimeByUniqueDay(teachesTime);

        localValid = validate(teacher.preference, teachesTime, false);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Lecturer " + teacher.name + " time preferences not match with teach time");
        }

        localValid = validate(course.requirement, classroom.facility, false);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Course " + course.name + " requirement not match with classroom "+ classroom.number + " constraint");
        }
        
        localValid = checkCapacity(classroom.capacity, capacity);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Course " + course.name + " capacity doesn't match with classroom "+ classroom.number + " capacity");
        }

        localValid = checkCredit(course.credit, teachesTime);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Course " + course.name + " credit not match with teach time");
        }

        localValid = validate(classroom.timeTaken, teachesTime, true);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Classroom " + classroom.number + " already taken at specified teach time for " + course.name + " course");
        }

        if(valid){
            teacher.assign(teachesTime);
            classroom.insertTimeTaken(teachesTime);
            for(int i = 0; i < timeByDay.length;i++){
                Entry newEntry = new Entry(teacher,classroom,course,timeByDay[i]);
                switch (timeByDay[i].get(0).day){
                    case "monday":
                        content[0].add(newEntry);
                        break;
                    case "tuesday":
                        content[1].add(newEntry);
                        break;
                    case "wednesday":
                        content[2].add(newEntry);
                        break;
                    case "thursday":
                        content[3].add(newEntry);
                        break;
                    case "friday":
                        content[4].add(newEntry);
                        break;
                    default:
                        System.out.println("Day " + timeByDay[i].get(0).day + " not recognized");
                }
            }
        }
    }

    public String toString(){
        String output = "";
        for(int i = 0; i < content.length; i++) {
            for (int j = 0; j < content[i].size(); j++){
                Entry e = content[i].get(j);
                output += e.toString();
            }
        }
//        System.out.println(String.format("%5s %5s %13s %5s %13s %5s %13s %5s %13s %5s %13s", "Time", "|", "Monday", "|", "Tuesday", "|", "Wednesday", "|", "Thursday", "|", "Friday"));
//        System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------"));
        return output;
    }

    private boolean validate(List<?> obj1, List<?> obj2, boolean inverse){
        boolean valid = inverse;
        //System.out.println(obj2.size());
        for(int i = 0; i < obj2.size(); i++){
            valid = inverse;
            for(int j = 0; j < obj1.size();j++){
                if(obj2.get(i) instanceof Time){
                    if(((Time) obj2.get(i)).equals((Time)obj1.get(j))){
                        valid = !inverse;
                        break;
                    }
                } else if (obj2.get(i) instanceof  String){
                    if(((String) obj2.get(i)).equals((String)obj1.get(j))){
                        valid = !inverse;
                        break;
                    }
                }else {
                    if(obj2.get(i).equals(obj1.get(j))) {
                        valid = !inverse;
                        break;
                    }
                }
            }
            if(!valid)
                break;
        }
        return valid;
    }

    private boolean checkCapacity(int roomCapacity, int requirementCapacity) {
        return requirementCapacity <= roomCapacity;
    }

    private boolean checkCredit(int credit, List<Time> time){
        return (credit == time.size());
    }

    public ArrayList<Time>[] splitTimeByUniqueDay(List<Time> time){
        List<String> days = new ArrayList<>();
        for(int i = 0; i < time.size();i++){
            if(!days.contains(time.get(i).day)){
                days.add(time.get(i).day);
            }
        }

        ArrayList<Time>[] newTime = new ArrayList[days.size()];
        for(int i = 0; i < newTime.length; i++){
            newTime[i] = new ArrayList<>();
        }

        for(int i = 0; i < time.size();i++){
            int idx = days.indexOf(time.get(i).day);
            newTime[idx].add(time.get(i));
        }
        return newTime;
    }
}
