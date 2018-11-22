import java.util.ArrayList;

class Entry {
  String lecturerName;
  String classroomName;
  String courseName;
  ArrayList<Schedule> schedules;

  Entry(String lecturerName, String classroomName, String courseName, ArrayList<Schedule> schedules) {
    super();
    this.lecturerName = lecturerName;
    this.classroomName = classroomName;
    this.courseName = courseName;
    this.schedules = new ArrayList<Schedule>();
    this.schedules.addAll(schedules);
  }
}