import scheduler.grammar.*;
import java.util.ArrayList;
import java.util.List;

import main.java.Course;

class CourseListener extends ClassSchedulerBaseListener {
  List<Course> list;

  public CourseListener() {
    list = new ArrayList<>();
  }

  @Override 
  public void exitCourses(ClassSchedulerParser.CoursesContext ctx) { 
    for (int j = 0; j < ctx.course().size(); j++) {
      String name = ctx.course(j).ALPHANUMERIC().getText();
      int credits = Integer.parseInt(ctx.course(j).NUMERIC().getText());
      List<String> facilities = new ArrayList<>();
      for (int i = 0; i < ctx.course(j).WORD().size(); i++) {
        facilities.add(ctx.course(j).WORD(i).getText());
      }
      
      Course course = new Course(name, credits, facilities);
      list.add(course);
    }
  }
}