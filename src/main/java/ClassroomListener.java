import scheduler.grammar.*;
import java.util.ArrayList;
import java.util.List;

class ClassroomListener extends ClassSchedulerBaseListener {
  List<String> list;

  public ClassroomListener() {
    list = new ArrayList<>();
  }

  @Override
  public void exitClassrooms(ClassSchedulerParser.ClassroomsContext ctx) {
    for (int j = 0; j < ctx.classroom().size(); j++) {
      
      list.add(ctx.classroom(j).NUMERIC(0).getText()); // name
      // System.out.println(ctx.classroom(j).NUMERIC(1)); // capacity
      for (int i = 0; i < ctx.classroom(j).WORD().size(); i++) {
        // System.out.println(ctx.classroom(j).WORD(i));
      }
    }
    // TODO: masukin ke suatu class
  }
}