import scheduler.grammar.*;
import org.antlr.v4.runtime.*;
import java.io.InputStream;
import java.io.IOException;

import main.listeners.*;

public class App {
    public String getGreeting() {
        return "Hello world.";
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
        System.out.println(teachesListener.entries.get(0).course);
        
        System.out.println(new App().getGreeting());
    }
}
