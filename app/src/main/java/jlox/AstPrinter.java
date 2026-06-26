package jlox;

import java.util.List;

// AI (pretty print), no need to understand for now
class AstPrinter implements Expr.Visitor<String>, Stmt.Visitor<String> {

  String print(Expr expr) {
    return expr.accept(this);
  }

  String print(Stmt stmt) {
    return stmt.accept(this);
  }

  String print(List<Stmt> stmts) {
    StringBuilder sb = new StringBuilder();
    for (Stmt stmt : stmts) {
      sb.append(stmt.accept(this)).append("\n");
    }
    return sb.toString().stripTrailing();
  }

  // --- Stmt visitors ---

  @Override
  public String visitBlockStmt(Stmt.Block stmt) {
    if (stmt.statements.isEmpty()) return "Block(empty)";
    StringBuilder sb = new StringBuilder("Block");
    for (int i = 0; i < stmt.statements.size(); i++) {
      boolean hasNext = i < stmt.statements.size() - 1;
      sb.append("\n").append(indent(stmt.statements.get(i).accept(this), hasNext));
    }
    return sb.toString();
  }

  @Override
  public String visitExpressionStmt(Stmt.Expression stmt) {
    return "ExprStmt\n" + indent(stmt.expression.accept(this), false);
  }

  @Override
  public String visitPrintStmt(Stmt.Print stmt) {
    return "PrintStmt\n" + indent(stmt.expression.accept(this), false);
  }

  @Override
  public String visitVarStmt(Stmt.Var stmt) {
    if (stmt.initializer == null) return "VarDecl(" + stmt.name.lexeme + ")";
    return "VarDecl(" + stmt.name.lexeme + ")\n" + indent(stmt.initializer.accept(this), false);
  }

  // --- Expr visitors ---

  @Override
  public String visitAssignExpr(Expr.Assign expr) {
    return "Assign(" + expr.name.lexeme + ")\n" + indent(expr.value.accept(this), false);
  }

  @Override
  public String visitBinaryExpr(Expr.Binary expr) {
    String left = indent(expr.left.accept(this), true);
    String right = indent(expr.right.accept(this), false);

    return "Binary(" + expr.operator.lexeme + ")\n" + left + "\n" + right;
  }

  @Override
  public String visitGroupingExpr(Expr.Grouping expr) {
    return "Grouping\n" + indent(expr.expression.accept(this), false);
  }

  @Override
  public String visitLiteralExpr(Expr.Literal expr) {
    if (expr.value == null) return "Literal(nil)";
    return "Literal(" + expr.value + ")";
  }

  @Override
  public String visitUnaryExpr(Expr.Unary expr) {
    return "Unary(" + expr.operator.lexeme + ")\n" + indent(expr.right.accept(this), false);
  }

  @Override
  public String visitVariableExpr(Expr.Variable expr) {
    return "Variable(" + expr.name.lexeme + ")";
  }

  private String indent(String text, boolean hasSibling) {
    String[] lines = text.split("\n");
    StringBuilder sb = new StringBuilder();

    sb.append(hasSibling ? "├── " : "└── ");
    sb.append(lines[0]);

    for (int i = 1; i < lines.length; i++) {
      sb.append("\n");
      sb.append(hasSibling ? "│   " : "    ");
      sb.append(lines[i]);
    }

    return sb.toString();
  }
}
