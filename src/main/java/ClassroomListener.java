import scheduler.grammar.*;
import java.util.ArrayList;
import java.util.List;

import main.java.Classroom;

class ClassroomListener extends ClassSchedulerBaseListener {
  List<Classroom> list;

  public ClassroomListener() {
    list = new ArrayList<>();
  }

  @Override
  public void exitClassrooms(ClassSchedulerParser.ClassroomsContext ctx) {
    for (int j = 0; j < ctx.classroom().size(); j++) {
      int name = Integer.parseInt(ctx.classroom(j).NUMERIC(0).getText());
      int capacity = Integer.parseInt(ctx.classroom(j).NUMERIC(1).getText());
      List<String> facilities = new ArrayList<>();
      for (int i = 0; i < ctx.classroom(j).WORD().size(); i++) {
        facilities.add(ctx.classroom(j).WORD(i).getText());
      }
      Classroom classroom = new Classroom(name, capacity, facilities);
      list.add(classroom);
    }
  }
}