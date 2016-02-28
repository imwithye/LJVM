package org.lucylang.ljvm.node;

public class ArrayLiteral extends Node implements IValue {
    private IValue[] values;

    public ArrayLiteral(int size) {
        this.values = new IValue[size];
    }

    public void setValue(int index, IValue value) {
        this.values[index] = value;
    }

    public IValue[] getValues() {
        return this.values;
    }
}
