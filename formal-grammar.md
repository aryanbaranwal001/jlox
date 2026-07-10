<!-- whole program is made of individual statements -->
<!-- following is called program rule/top level rule/start rule that describes a complete program -->

program      → declaration* EOF ;

declaration  → varDecl
             | classDecl
             | funDecl
             | statement ;

varDecl      → "var" IDENTIFIER ( "=" expression )? ";" ;

classDecl    → "class" IDENTIFIER "{" function* "}" ;

funDecl      → "fun" function ;
function     → IDENTIFIER "(" parameters? ")" block ;
parameters   → IDENTIFIER ( "," IDENTIFIER )* ;

statement    → exprStmt
             | ifStmt
             | printStmt
             | returnStmt
             | whileStmt
             | forStmt
             | block ;

exprStmt     → expression ";" ;
ifStmt       → "if" "(" expression ")" statement ("else" statement)? ;
printStmt    → "print" expression ";" ;
returnStmt   → "return" expression? ";" ;
whileStmt    → "while" "(" expression ")" statment ;
forstmt      → "for" "(" ( varDecl | exprStmt | ";" ) expression? ";" expression? ")" statement ;
block        → "{" declaration* "}" ;

expression   → equality ;

assignment   → ( call "." )? IDENTIFIER "=" assignment
             | logic_or ;

logic_or     → logic_and ( "or" logic_and )*;

logic_and    → equality ( "and" equality )* ;

equality     → comparison ( ( "!=" | "==" ) comparison )* ;

comparison   → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;

term         → factor ( ( "-" | "+" ) factor )* ;

factor       → unary ( ( "/" | "*" ) unary )* ;

unary        → ( "!" | "-" ) unary
             | call ;

call         → primary ( "(" arguments? ")" | "." IDENTIFIER )* ;

arguments    → expression ( "," expression )* ;

primary      → "true" | "false" | "nil"
             | NUMBER | STRING
             | IDENTIFIER
             | "(" expression ")" ;

<!-- Info -->
<!-- 1. ; at the end means "this grammar rule ends here" -->
