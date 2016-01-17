package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.Type;

public abstract class Value {
    public abstract Type getType();

    public boolean isSameType(Value value) {
        return this.getType().equals(value.getType());
    }

    public abstract Integer intValue() throws ValueUnavailableException;

    public abstract Double floatValue() throws ValueUnavailableException;

    public abstract String stringValue() throws ValueUnavailableException;

    public abstract Boolean booleanValue() throws ValueUnavailableException;
}
