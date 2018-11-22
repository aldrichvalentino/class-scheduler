import scheduler.grammar.*;
import java.util.ArrayList;

class TeachesListener extends ClassSchedulerBaseListener {
  ArrayList<Entry> entries;

  TeachesListener() {
    super();
    this.entries = new ArrayList<Entry>();
  }

  @Override
  public void exitTeaches(ClassSchedulerParser.TeachesContext ctx) {
    for (int idx = 0; idx < ctx.teach().size(); idx++) {
      String lecturerName = ctx.teach(idx).WORD().getText();
      String className = ctx.teach(idx).NUMERIC().getText();
      String courseName = ctx.teach(idx).ALPHANUMERIC().getText();
      ArrayList<Schedule> schedules = new ArrayList<Schedule>();
      for (int jdx = 0; jdx < ctx.teach(idx).schedules().schedule().size(); jdx++) {
        String scheduleNames = ctx.teach(idx).schedules().schedule(jdx).WORD().getText();
        ArrayList<Integer> times = new ArrayList<Integer>();
        for (int kdx = 0; kdx < ctx.teach(idx).schedules().schedule(jdx).NUMERIC().size(); kdx++) {
          times.add(Integer.parseInt(ctx.teach(idx).schedules().schedule(jdx).NUMERIC(kdx).getText()));
        }
        schedules.add(new Schedule(scheduleNames, times));
      }
      this.entries.add(new Entry(lecturerName, className, courseName, schedules));
    }
    // for (int idx = 0; idx < this.entries.size(); idx++) {
    //   System.out.println(this.entries.get(idx).lecturerName);
    // }
  }
}