package org.lucylang.ljvm.node;

public class NumberLiteral extends Node implements IValue {
    private Double value;

    public NumberLiteral(Integer value) {
        this.value = new Double(value);
    }

    public NumberLiteral(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return this.value;
    }
}
