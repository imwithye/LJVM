package org.lucylang.ljvm.node;

public class NotExpr extends Node {
    private IValue value;

    public NotExpr(IValue value) {
        assert value != null;
        this.value = value;
    }

    public IValue getValue() {
        return this.value;
    }
}
