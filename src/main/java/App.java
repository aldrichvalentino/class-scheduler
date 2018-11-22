import scheduler.grammar.*;
import org.antlr.v4.runtime.*;
import java.io.InputStream;
import java.io.IOException;

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

        // parser.addParseListener(new ClassroomListener());
        parser.addParseListener(new LecturerListener());
        parser.addParseListener(new TeachesListener());
        // LecturerListener lecturers = new LecturerListener();
        // parser.addParseListener(lecturers);
        // System.out.println(parser.classrooms());
        // System.out.println(parser.courses());
        System.out.println(parser.lecturers());
        System.out.println(parser.teaches());
        // System.out.println(parser.courses().getText());
        // System.out.println(new App().getGreeting());
    }
}
