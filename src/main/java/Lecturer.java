import java.util.ArrayList;

class Lecturer {
  String name;
  ArrayList<String> constraints;
  ArrayList<Schedule> schedules;
  
  Lecturer(String name, ArrayList<String> constraints, ArrayList<Schedule> schedules) {
    super();
    this.name = name;
    this.constraints = new ArrayList<String>();
    this.schedules = new ArrayList<Schedule>();
    this.constraints.addAll(constraints);
    this.schedules.addAll(schedules);
  }
}