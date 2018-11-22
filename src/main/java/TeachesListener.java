import scheduler.grammar.*;
import java.util.ArrayList;
import main.java.Entry;
import main.java.Time;

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
      ArrayList<Time> preference = new ArrayList<Time>();
      for (int jdx = 0; jdx < ctx.teach(idx).schedules().schedule().size(); jdx++) {
        String day = ctx.teach(idx).schedules().schedule(jdx).WORD().getText();
        for (int kdx = 0; kdx < ctx.teach(idx).schedules().schedule(jdx).NUMERIC().size(); kdx++) {
          Time time = new Time(day, Integer.parseInt(ctx.teach(idx).schedules().schedule(jdx).NUMERIC(kdx).getText()));
          preference.add(time);
        }
      }
      this.entries.add(new Entry(lecturerName, className, courseName, preference));
    }
  }
}