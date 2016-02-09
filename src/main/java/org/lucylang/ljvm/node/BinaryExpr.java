package org.lucylang.ljvm.node;

public class BinaryExpr implements IValue {
    private IValue lhs;
    private IValue rhs;

    public BinaryExpr(IValue lhs, IValue rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue getLhs() {
        return this.lhs;
    }

    public IValue getRhs() {
        return this.rhs;
    }
}
