package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public abstract class Value {
    public abstract Type getType();

    public boolean isSameType(Value value) {
        return this.getType().equals(value.getType());
    }

    public abstract Integer intValue() throws ValueUnavailableException;

    public abstract Double floatValue() throws ValueUnavailableException;

    public abstract String stringValue();

    public abstract Boolean booleanValue() throws ValueUnavailableException;

    public abstract Value add(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value sub(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value mul(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value div(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value and(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value or(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value not() throws ValueUnavailableException;

    public abstract Value equ(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value les(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value gre(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value leq(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract Value geq(Value value) throws ValueUnavailableException, TypeUnmatchedException;

    public abstract NumberValue toNumberValue() throws ValueUnavailableException;

    public abstract StringValue toStringValue() throws ValueUnavailableException;

    public abstract BooleanValue toBooleanValue() throws ValueUnavailableException;
}
