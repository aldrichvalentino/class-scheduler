import scheduler.grammar.*;
import java.util.ArrayList;

class ClassroomListener extends ClassSchedulerBaseListener {
  ArrayList<String> list;

  @Override
  public void exitClassrooms(ClassSchedulerParser.ClassroomsContext ctx) {
    for (int j = 0; j < ctx.classroom().size(); j++) {
      
      System.out.println(ctx.classroom(j).NUMERIC(0)); // name
      System.out.println(ctx.classroom(j).NUMERIC(1)); // capacity
      for (int i = 0; i < ctx.classroom(j).WORD().size(); i++) {
        System.out.println(ctx.classroom(j).WORD(i));
      }
    }
    // TODO: masukin ke suatu class
  }

  // @Override 
  // public void exitCourses(ClassSchedulerParser.CoursesContext ctx) { 
  //   for (int j = 0; j < ctx.course().size(); j++) {
  //     System.out.println(ctx.course(j).ALPHANUMERIC());
  //     System.out.println(ctx.course(j).NUMERIC());
  //   }
  // }
}