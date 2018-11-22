import main.java.Classroom;
import main.java.Course;
import main.java.Lecturer;
import main.java.Schedule;
import scheduler.grammar.*;
import org.antlr.v4.runtime.*;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;

import main.listeners.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static Object getItem(String name, List<?> objects, Class type){
        Class<?> theClass = type;
        for(int i = 0; i < objects.size();i++){
            if(objects.get(i) instanceof Classroom){
                if(((Classroom) objects.get(i)).equals(name))
                    return objects.get(i);
            } else if (objects.get(i) instanceof Course){
                if(((Course) objects.get(i)).equals(name))
                    return objects.get(i);
            } else if (objects.get(i) instanceof Lecturer){
                if(((Lecturer) objects.get(i)).equals(name))
                    return objects.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        //Reading the DSL script
        InputStream is = ClassLoader.getSystemResourceAsStream("input.txt");
        
        //Loading the DSL script into the ANTLR stream.
        CharStream cs = new ANTLRInputStream(is);

        //Passing the input to the lexer to create tokens
        ClassSchedulerLexer lexer = new ClassSchedulerLexer(cs);
        
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        //Passing the tokens to the parser to create the parse trea. 
        ClassSchedulerParser parser = new ClassSchedulerParser(tokens);
        ClassroomListener classroomListener = new ClassroomListener();
        CourseListener courseListener = new CourseListener();
        LecturerListener lecturerListener = new LecturerListener();
        TeachesListener teachesListener = new TeachesListener();
        Schedule schedule = new Schedule(5);
        parser.addParseListener(classroomListener);
        parser.addParseListener(courseListener);
        parser.addParseListener(lecturerListener);
        parser.addParseListener(teachesListener);
        
        parser.classrooms();
        parser.courses();
        parser.lecturers();
        parser.teaches();
        
        System.out.println(classroomListener.classrooms.get(1).facility);
        System.out.println(courseListener.courses.get(0).requirement);
        System.out.println(lecturerListener.lecturers.get(0).name);
        System.out.println(teachesListener.teaches.get(0).course);

        for(int i = 0;i < teachesListener.teaches.size();i++){
            Classroom classroom = (Classroom)App.getItem(teachesListener.teaches.get(i).classroom, classroomListener.classrooms, Classroom.class);
            Course course = (Course)App.getItem(teachesListener.teaches.get(i).course, courseListener.courses, Course.class);
            Lecturer lecturer = (Lecturer)App.getItem(teachesListener.teaches.get(i).teacher, lecturerListener.lecturers, Lecturer.class);
            System.out.println(classroom.number);
            schedule.insertEntry(lecturer, classroom, course, teachesListener.teaches.get(i).time);
        }

        //
        System.out.println(schedule.toString());
        System.out.println(new App().getGreeting());
    }
}
