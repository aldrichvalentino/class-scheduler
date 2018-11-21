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
      Course course = new Course(ctx.course(j).ALPHANUMERIC().getText(), 
                                Integer.parseInt(ctx.course(j).NUMERIC().getText()));
      list.add(course);
    }
  }
}