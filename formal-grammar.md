<!-- whole program is made of individual statements -->
program    → statement* EOF ;

statement  → exprStmt
           | printStmt ;

exprStmt   → expression ";" ;

printStmt  → "print" expression ";" ;

---

expression → equality ;

equality   → comparison ( ( "!=" | "==" ) comparison )* ;

comparison → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;

term       → factor ( ( "-" | "+" ) factor )* ;

factor     → unary ( ( "/" | "*" ) unary )* ;

unary      → ( "!" | "-" ) unary
           | primary ;

primary    → NUMBER
           | STRING
           | "true"
           | "false"
           | "nil"
           | "(" expression ")" ;

<!-- Info -->
<!-- 1. ; at the end means "this grammar rule ends here" -->
