package org.lucylang.ljvm.node;

public class BooleanLiteral extends Node implements IValue {
    private Boolean value;

    public BooleanLiteral(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }
}
