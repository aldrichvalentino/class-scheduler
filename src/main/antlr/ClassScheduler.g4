/*
 * Grammar for class scheduling 
 * */
grammar ClassScheduler;

/*
 * Main statement 
 * Using top-down approach
 * */
statement : classrooms ENTER courses ENTER lecturers ENTER teaches;

/*
 * Course Definition
 * course 'course_name' 'credits'
 * ex: course IF3170 4 
 * */
courses : (course)*;
course  : COURSE ALPHANUMERIC NUMERIC;
// classes : OPENBRACKET WORD NUMERIC+ CLOSEBRACKET; 

/*
 * Classroom Definition
 * classroom 'class_name' 'capacity' 'facilities seperated with space'
 * ex: classroom 7603 45 laptop projector
 * */
classrooms : (classroom)*;
classroom  : CLASSROOM NUMERIC NUMERIC WORD+;

/*
 * Teach Deifinition
 * */
teaches			 			: (teach ENTER)+;
teach				 			: TEACH lecturerName courseName teachingSchedules;
lecturerName 			: WORD;
teachingSchedules : OPENBRACKET (day schedule ENTER)+ CLOSEBRACKET;
day								: WORD;

/*
 * Lecturers Definition
 * name { day 1,2 } { laptop projector }
 * */
lecturers 				: (lecturer)*;
lecturer  				: LECTURE WORD schedules constraints;
schedules					: OPENBRACKET schedule+ CLOSEBRACKET;
schedule					: WORD NUMERIC+;
constraints				: OPENBRACKET WORD* CLOSEBRACKET;
teachesLecturer		: OPENBRACKET teach* CLOSEBRACKET;
constraint        : WORD+;

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
WS: [ \t\r\n]+ -> skip;
WHITESPACE      : (' ' | '\t');
ENTER           : ('\r'? '\n' | '\r')+;
OPENBRACKET		  : '{'(ENTER | WHITESPACE);
CLOSEBRACKET	  : (ENTER | WHITESPACE)'}';
OPENTUPLE		    : '['(ENTER | WHITESPACE);
CLOSETUPLE		  : (ENTER | WHITESPACE)']';