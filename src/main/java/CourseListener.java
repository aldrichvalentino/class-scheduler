import scheduler.grammar.*;
import java.util.ArrayList;
import java.util.List;

class CourseListener extends ClassSchedulerBaseListener {
  List<String> list;

  public CourseListener() {
    list = new ArrayList<>();
  }

  @Override 
  public void exitCourses(ClassSchedulerParser.CoursesContext ctx) { 
    for (int j = 0; j < ctx.course().size(); j++) {
      list.add(ctx.course(j).ALPHANUMERIC().getText());
      System.out.println(ctx.course(j).NUMERIC());
    }
  }
}