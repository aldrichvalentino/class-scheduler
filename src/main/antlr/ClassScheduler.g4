/*
 * Grammar for class scheduling 
 * */
grammar ClassScheduler;

/*
 * Main statement 
 * Using top-down approach
 * */
statement : classrooms courses lecturers teaches;

/*
 * Classroom Definition
 * classroom 'class_name' 'capacity' ('facilities seperated with space')
 * ex: classroom 7603 45 (laptop projector)
 * */
classrooms : (classroom)*;
classroom  : CLASSROOM NUMERIC NUMERIC '(' WORD+ ')';

/*
 * Course Definition
 * course 'course_name' 'credits' 'facilities seperated by space'
 * ex: course IF3170 4 
 * */
courses : (course)*;
course  : COURSE ALPHANUMERIC NUMERIC '(' WORD+ ')';

/*
 * Lecturers Definition
 * Lecturer 'name' { day 1 2 } 
 * ex: lecturer x { Monday 7 8 }
 * */
lecturers 				: (lecturer)+;
lecturer  				: LECTURE WORD schedules;
schedules					: OPENBRACKET (schedule)+ CLOSEBRACKET;
schedule					: WORD NUMERIC+;

/*
 * Teach Deifinition
 * Teach 'lecturer_name' 'class_number' 'course_name' 'class_capacity' { day 1 2 }
 * ex: teach x 7603 if3109 { Monday 7 8 }
 * */
teaches			 			: (teach)+;
teach				 			: TEACH WORD NUMERIC ALPHANUMERIC NUMERIC schedules;

/*
 * Parser Rules
 */
courseName					: ALPHANUMERIC;
credits							: NUMERIC;
COURSE							: 'course';
CLASSROOM						: 'classroom';
LECTURE							: 'lecturer';
TEACH								: 'teach';

/*
 * Lexer Rules
 */
LOWERCASE       : [a-z];
UPPERCASE       : [A-Z];
NUMERIC			    : DIGIT+;
fragment DIGIT	: [0-9];
WORD            : (LOWERCASE | UPPERCASE | '_')+;
ALPHANUMERIC    : (LOWERCASE | UPPERCASE | DIGIT | '_')+;
WS              : [ \t\r\n]+ -> skip;
WHITESPACE      : (' ' | '\t');
ENTER           : ('\r'? '\n' | '\r')+;
OPENBRACKET		  : '{'(ENTER | WS);
CLOSEBRACKET	  : (ENTER | WS)'}';
OPENSET   		  : '('(ENTER | WS);
CLOSESET     	  : (ENTER | WS)')';
OPENTUPLE		    : '['(ENTER | WS);
CLOSETUPLE		  : (ENTER | WS)']';
