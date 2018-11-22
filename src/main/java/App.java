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

        for(int i = 0;i < teachesListener.teaches.size();i++){
            boolean valid = true;
            Classroom classroom = (Classroom)App.getItem(teachesListener.teaches.get(i).classroom, classroomListener.classrooms, Classroom.class);
            Course course = (Course)App.getItem(teachesListener.teaches.get(i).course, courseListener.courses, Course.class);
            Lecturer lecturer = (Lecturer)App.getItem(teachesListener.teaches.get(i).teacher, lecturerListener.lecturers, Lecturer.class);

            if(classroom == null){
                System.out.println("Classroom " + teachesListener.teaches.get(i).classroom + " not found");
                valid = false;
            }

            if(course == null){
                System.out.println("Course " + teachesListener.teaches.get(i).course + " not found");
                valid = false;
            }

            if(lecturer == null){
                System.out.println("Lecturer " + teachesListener.teaches.get(i).teacher + " not found");
                valid = false;
            }

            int capacity = teachesListener.teaches.get(i).capacity;
            if(valid)
                schedule.insertEntry(lecturer, classroom, course, capacity, teachesListener.teaches.get(i).time);
        }

        System.out.println(schedule.toString());
        System.out.println(new App().getGreeting());
    }
}
