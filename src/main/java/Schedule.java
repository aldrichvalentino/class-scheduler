package main.java;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<Entry>[] content;

    public Schedule(){
        content = new List[5];
    }

    public void insertEntry(Lecturer teacher, Classroom classroom, Course course, String day, List<Integer> time) {
        boolean valid = true;
        boolean localValid = true;
        ArrayList<Time> teachesTime = new ArrayList<>();
        for(int i = 0; i < time.size();i++){
            teachesTime.add(new Time(day, time.get(i)));
        }

        localValid = validate(teacher.preference, teachesTime, false);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Lecturer time preferences not match with teach time");
        }

        localValid = validate(course.requirement, classroom.facility, false);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Course requirement not match with classroom constraint");
        }

        localValid = checkCredit(course.credit, teachesTime);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Credit not match with teach time");
        }

        localValid = validate(classroom.timeTaken, teachesTime, true);
        valid = localValid && valid;
        if(!localValid){
            System.out.println("Classroom already taken at specified teach time");
        }

        if(valid){
            Entry newEntry = new Entry(teacher,classroom,course,teachesTime);
            teacher.assign(teachesTime);
            classroom.insertTimeTaken(teachesTime);
            switch (day){
                case "Senin":
                    content[0].add(newEntry);
                    break;
                case "Selasa":
                    content[1].add(newEntry);
                    break;
                case "Rabu":
                    content[2].add(newEntry);
                    break;
                case "Kamis":
                    content[3].add(newEntry);
                    break;
                case "Jumat":
                    content[4].add(newEntry);
                    break;
                default:
                    System.out.println("Day " + day + " not recognized");
            }
        }
    }

    public String toString(){
        String output = "";

    }

    private boolean validate(List<?> obj1, List<?> obj2, boolean inverse){
        boolean valid = inverse;
        for(int i = 0; i < obj2.size(); i++){
            valid = inverse;
            for(int j = 0; j < obj1.size();j++){
                if(obj2.get(i).equals(obj1.get(j))){
                    valid = !inverse;
                    break;
                }
            }
            if(!valid)
                break;
        }
        return valid;
    }

    private boolean checkCredit(int credit, List<Time> time){
        return (credit == time.size());
    }
}
