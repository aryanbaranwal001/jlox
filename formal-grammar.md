<!-- whole program is made of individual statements -->
<!-- following is called program rule/top level rule/start rule that describes a complete program -->

program      → declaration* EOF ;

declaration  → exprStmt
             | printStmt ;

statement    → exprStmt
             | printStmt ;

exprStmt     → expression ";" ;

printStmt    → "print" expression ";" ;

---

varDecl      → "var" IDENTIFIER ( "=" expression )? ";" ;

expression   → equality ;

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
