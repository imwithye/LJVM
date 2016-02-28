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

    public abstract BooleanValue equ(Value value);

    public abstract BooleanValue les(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract BooleanValue gre(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract BooleanValue leq(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract BooleanValue geq(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value valueAt(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract void set(Value index, Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract NumberValue length() throws ValueUnavailableException;

    public abstract Value copy();

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
    public boolean equals(Object o) {
        if (o instanceof Value) {
            Value v = (Value) o;
            if (v.isSameType(this)) {
                return v.equ(this).booleanValue();
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
