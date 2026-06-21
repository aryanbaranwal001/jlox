package jlox;

// AI (pretty print), no need to understand for now
class AstPrinter implements Expr.Visitor<String> {

  String print(Expr expr) {
    return expr.accept(this);
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
