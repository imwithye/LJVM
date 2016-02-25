package org.lucylang.ljvm.node;

public class Return extends Node implements IStmt {
    private IValue value;

    public Return() {
        this.value = new NoneLiteral();
    }

    public Return(IValue value) {
        assert value != null;
        this.value = value;
    }

    public IValue getValue() {
        return this.value;
    }
}
