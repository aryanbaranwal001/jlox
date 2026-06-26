<!-- whole program is made of individual statements -->
<!-- following is called program rule/top level rule/start rule that describes a complete program -->

program      → declaration* EOF ;

declaration  → exprStmt
             | printStmt ;

statement    → exprStmt
             | ifStmt
             | printStmt
             | block ;

ifStmt       → "if" "(" expression ")" statement ("else" statement)? ;

block        → "{" declaration* "}" ;

exprStmt     → expression ";" ;

printStmt    → "print" expression ";" ;

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
