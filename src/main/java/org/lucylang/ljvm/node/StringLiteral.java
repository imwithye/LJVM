package org.lucylang.ljvm.node;

public class StringLiteral implements IValue {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
