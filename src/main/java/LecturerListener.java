import scheduler.grammar.*;
import java.util.ArrayList;

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
      ArrayList<String> constraints = new ArrayList<String>();
      ArrayList<Schedule> schedules = new ArrayList<Schedule>();
      for (int jdx = 0; jdx < ctx.lecturer(idx).constraints().WORD().size(); jdx++) {
        constraints.add(ctx.lecturer(idx).constraints().WORD(jdx).getText());
      }
      for (int jdx = 0; jdx < ctx.lecturer(idx).schedules().schedule().size(); jdx++) {
        String scheduleNames = ctx.lecturer(idx).schedules().schedule(jdx).WORD().getText();
        ArrayList<Integer> times = new ArrayList<Integer>();
        for (int kdx = 0; kdx < ctx.lecturer(idx).schedules().schedule(jdx).NUMERIC().size(); kdx++) {
          times.add(Integer.parseInt(ctx.lecturer(idx).schedules().schedule(jdx).NUMERIC(kdx).getText()));
        }
        schedules.add(new Schedule(scheduleNames, times));
      }
      this.lecturers.add(new Lecturer(lecturerName, constraints, schedules));
    }
    // for (int idx = 0; idx < this.lecturers.size(); idx++) {
    //   System.out.println(this.lecturers.get(idx).constraints);
    // }
  }
}