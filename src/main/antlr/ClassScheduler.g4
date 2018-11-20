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
 * */
courses : (course ENTER)*;
course  : COURSE courseName credits classes;
classes : OPENBRACKET WORD+ CLOSEBRACKET; // TODO: change the class grammar

/*
 * Classroom Definition
 * */
classrooms : (classroom ENTER)*;
classroom  : CLASSROOM name capacity facilities;
name			 : NUMERIC;
capacity	 : NUMERIC;
facilities : object (',' object)*;
object		 : WORD;

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
 * */
lecturers 				: (lecture ENTER)*;
lecture  					: LECTURE lecturerName schedules constraints teachesLecturer;
schedules					: OPENBRACKET (schedule ENTER)+ CLOSEBRACKET;
constraints				: OPENBRACKET (constraint ENTER)* CLOSEBRACKET;
teachesLecturer		: OPENBRACKET teach* CLOSEBRACKET;
schedule					: OPENTUPLE NUMERIC (',' NUMERIC)* CLOSETUPLE;
constraint        : facilities;

/*
 * Parser Rules
 */
courseName					: ALPHANUMERIC;
credits							: NUMERIC;
COURSE							: 'course';
CLASSROOM						: 'classroom';
LECTURE							: 'lecture';
TEACH								: 'teach';

/*
 * Lexer Rules
 */
LOWERCASE       : [a-z];
UPPERCASE       : [A-Z];
DIGIT		        : [0-9];
WORD            : (LOWERCASE | UPPERCASE | '_')+;
ALPHANUMERIC    : (LOWERCASE | UPPERCASE | DIGIT | '_')+;
NUMERIC			    : [0-9]+;
WHITESPACE      : (' ' | '\t');
ENTER           : ('\r'? '\n' | '\r')+;
OPENBRACKET		  : '{'(ENTER | WHITESPACE);
CLOSEBRACKET	  : (ENTER | WHITESPACE)'}';
OPENTUPLE		    : '['(ENTER | WHITESPACE);
CLOSETUPLE		  : (ENTER | WHITESPACE)']';
WS              : WHITESPACE+ -> skip;