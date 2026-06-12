package visitor;

abstract class Pastry {
    abstract void accept(PastryVisitor visitor);
}

class Beignet extends Pastry {
    void accept(PastryVisitor visitor) {
        visitor.visitBeignet(this);
    }
}

class Cruller extends Pastry {
    void accept(PastryVisitor visitor) {
        visitor.visitCruller(this);
    }

}

interface PastryVisitor {
    void visitBeignet(Beignet beignet);

    void visitCruller(Cruller cruller);
}

class PrintVisitor implements PastryVisitor {
    public void visitBeignet(Beignet beignet) {
        System.out.println("Visiting a beignet");
    }

    public void visitCruller(Cruller cruller) {
        System.out.println("Visiting a cruller");
    }
}

public class Visit {
    public static void main() {
        // Beignet pastry1 = new Beignet();
        // Cruller pastry2 = new Cruller();

        Pastry pastry1 = new Beignet();
        Pastry pastry2 = new Cruller();

        PastryVisitor visitor = new PrintVisitor();

        pastry1.accept(visitor);
        pastry2.accept(visitor);
    }
}
