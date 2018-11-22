package main.listeners;

import main.java.Lecturer;
import main.java.Teach;
import scheduler.grammar.*;
import java.util.ArrayList;
import main.java.Entry;
import main.java.Time;

public class TeachesListener extends ClassSchedulerBaseListener {
  public ArrayList<Teach> teaches;

  public TeachesListener() {
    super();
    this.teaches = new ArrayList<Teach>();
  }

  @Override
  public void exitTeaches(ClassSchedulerParser.TeachesContext ctx) {
    for (int idx = 0; idx < ctx.teach().size(); idx++) {
      String lecturerName = ctx.teach(idx).WORD().getText();
      Lecturer lecturer = new Lecturer(lecturerName);
      String className = ctx.teach(idx).NUMERIC(0).getText();
      String courseName = ctx.teach(idx).ALPHANUMERIC().getText();
      int capacity = Integer.parseInt(ctx.teach(idx).NUMERIC(1).getText());
      // System.out.println(capacity);
      ArrayList<Time> preference = new ArrayList<Time>();
      for (int jdx = 0; jdx < ctx.teach(idx).schedules().schedule().size(); jdx++) {
        String day = ctx.teach(idx).schedules().schedule(jdx).WORD().getText();
        for (int kdx = 0; kdx < ctx.teach(idx).schedules().schedule(jdx).NUMERIC().size(); kdx++) {
          Time time = new Time(day, Integer.parseInt(ctx.teach(idx).schedules().schedule(jdx).NUMERIC(kdx).getText()));
          preference.add(time);
        }
      }
      this.teaches.add(new Teach(lecturerName, className, courseName, capacity, preference));
    }
  }
}