package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.NoneType;
import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class NoneValue extends Value {
    private NoneType noneType;

    public NoneValue() {
        this.noneType = new NoneType();
    }

    @Override
    public Type getType() {
        return this.noneType;
    }

    @Override
    public Integer intValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("int", this);
    }

    @Override
    public Double floatValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("float", this);
    }

    @Override
    public String stringValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("string", this);
    }

    @Override
    public Boolean booleanValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("boolean", this);
    }

    @Override
    public Value add(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform add over " + this + " value");
    }

    @Override
    public Value sub(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform sub over " + this + " value");
    }

    @Override
    public Value mul(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform mul over " + this + " value");
    }

    @Override
    public Value div(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform div over " + this + " value");
    }

    @Override
    public Value and(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform and over " + this + " value");
    }

    @Override
    public Value or(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform or over " + this + " value");
    }

    @Override
    public Value not() throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform not over " + this + " value");
    }

    @Override
    public BooleanValue equ(Value value) {
        if (!this.isSameType(value)) {
            return new BooleanValue(false);
        }
        return new BooleanValue(true);
    }

    @Override
    public BooleanValue les(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform < over " + this + " value");
    }

    @Override
    public BooleanValue gre(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform > over " + this + " value");
    }

    @Override
    public BooleanValue leq(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform <= over " + this + " value");
    }

    @Override
    public BooleanValue geq(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform >= over " + this + " value");
    }

    @Override
    public Value valueAt(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (value instanceof NumberValue) {
            throw new ValueUnavailableException("unable to perform [] over " + this + " value");
        }
        throw new TypeUnmatchedException(new NumberType(), value.getType());
    }

    @Override
    public NumberValue length() throws ValueUnavailableException {
        throw new ValueUnavailableException("length", this);
    }
}
