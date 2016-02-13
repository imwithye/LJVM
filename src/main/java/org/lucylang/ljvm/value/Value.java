package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

import java.io.Serializable;

public abstract class Value implements Serializable {
    public abstract Type getType();

    public boolean isSameType(Value value) {
        return this.getType().equals(value.getType());
    }

    public abstract Integer intValue() throws ValueUnavailableException;

    public abstract Double floatValue() throws ValueUnavailableException;

    public abstract String stringValue() throws ValueUnavailableException;

    public abstract Boolean booleanValue() throws ValueUnavailableException;

    public abstract Value add(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value sub(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value mul(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value div(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value and(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value or(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value not() throws ValueUnavailableException;

    public abstract Value equ(Value value);

    public abstract Value les(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value gre(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value leq(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value geq(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public NumberValue toNumberValue() throws ValueUnavailableException {
        return new NumberValue(this.floatValue());
    }

    public StringValue toStringValue() throws ValueUnavailableException {
        return new StringValue(this.stringValue());
    }

    public BooleanValue toBooleanValue() throws ValueUnavailableException {
        return new BooleanValue(this.booleanValue());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
