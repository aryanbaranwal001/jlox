<!-- whole program is made of individual statements -->
<!-- following is called program rule/top level rule/start rule that describes a complete program -->

program      → declaration* EOF ;

declaration  → varDecl
             | statement ;

statement    → exprStmt
             | ifStmt
             | printStmt
             | whileStmt
             | forStmt
             | block ;

exprStmt     → expression ";" ;

ifStmt       → "if" "(" expression ")" statement ("else" statement)? ;

printStmt    → "print" expression ";" ;

whileStmt    → "while" "(" expression ")" statment ;

forstmt      → "for" "(" ( varDecl | exprStmt | ";" ) expression? ";" expression? ")" statement ;

block        → "{" declaration* "}" ;

---

varDecl      → "var" IDENTIFIER ( "=" expression )? ";" ;

expression   → equality ;

assignment   → IDENTIFIER "=" assignment | logic_or ;

logic_or     → logic_and ( "or" logic_and )*;

logic_and    → equality ( "and" equality )* ;

equality     → comparison ( ( "!=" | "==" ) comparison )* ;

comparison   → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;

term         → factor ( ( "-" | "+" ) factor )* ;

factor       → unary ( ( "/" | "*" ) unary )* ;

unary        → ( "!" | "-" ) unary
             | primary ;

primary      → NUMBER
             | STRING
             | "true"
             | "false"
             | "nil"
             | "(" expression ")" ;

<!-- Info -->
<!-- 1. ; at the end means "this grammar rule ends here" -->
