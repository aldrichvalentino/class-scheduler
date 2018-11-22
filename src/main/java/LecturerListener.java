import scheduler.grammar.*;
import java.util.ArrayList;
import main.java.Lecturer;
import main.java.Time;

class LecturerListener extends ClassSchedulerBaseListener {
  ArrayList<Lecturer> lecturers;

  LecturerListener() {
    super();
    this.lecturers = new ArrayList<Lecturer>();
  }

  @Override
  public void exitLecturers(ClassSchedulerParser.LecturersContext ctx) {
    for (int idx = 0; idx < ctx.lecturer().size(); idx++) {
      String lecturerName = ctx.lecturer(idx).WORD().getText();
      ArrayList<Time> preference = new ArrayList<Time>();
      for (int jdx = 0; jdx < ctx.lecturer(idx).schedules().schedule().size(); jdx++) {
        String day = ctx.lecturer(idx).schedules().schedule(jdx).WORD().getText();
        for (int kdx = 0; kdx < ctx.lecturer(idx).schedules().schedule(jdx).NUMERIC().size(); kdx++) {
          Time time = new Time(day, Integer.parseInt(ctx.lecturer(idx).schedules().schedule(jdx).NUMERIC(kdx).getText()));
          preference.add(time);
        }
      }
      this.lecturers.add(new Lecturer(lecturerName, preference));
    }
  }
}