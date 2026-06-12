package visitor;

abstract class Expr {
    abstract void apply(ExprOperation operation);
}

class NumberExpr extends Expr {
    void apply(ExprOperation operation) {
        operation.onNumber(this);
    }
}

class AddExpr extends Expr {
    void apply(ExprOperation operation) {
        operation.onAdd(this);
    }
}

interface ExprOperation {
    void onNumber(NumberExpr expr);

    void onAdd(AddExpr expr);
}

class PrintOperation implements ExprOperation {
    public void onNumber(NumberExpr expr) {
        System.out.println("number");
    }

    public void onAdd(AddExpr expr) {
        System.out.println("add");
    }
}

public class Tox {
    public static void main() {
        Expr expr = new AddExpr();

        ExprOperation op = new PrintOperation();

        expr.apply(op);
    }
}
