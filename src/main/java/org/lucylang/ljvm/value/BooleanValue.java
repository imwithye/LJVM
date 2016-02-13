package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.BooleanType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class BooleanValue extends Value {
    private BooleanType type;
    private Boolean value;

    public BooleanValue() {
        this(false);
    }

    public BooleanValue(Boolean value) {
        this.type = new BooleanType();
        this.value = value;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public Integer intValue() {
        if (this.value.booleanValue()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Double floatValue() {
        if (this.value.booleanValue()) {
            return new Double(1);
        } else {
            return new Double(0);
        }
    }

    @Override
    public String stringValue() {
        if (this.value.booleanValue()) {
            return "true";
        } else {
            return "false";
        }
    }

    @Override
    public Boolean booleanValue() {
        return this.value;
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
    public Value and(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        return new BooleanValue(this.value && ((BooleanValue) value).booleanValue());
    }

    @Override
    public Value or(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        return new BooleanValue(this.value || ((BooleanValue) value).booleanValue());
    }

    @Override
    public Value not() {
        return new BooleanValue(!this.value);
    }

    @Override
    public Value equ(Value value) {
        if (!(value instanceof BooleanValue)) {
            return new BooleanValue(false);
        }
        BooleanValue booleanValue = (BooleanValue) value;
        return new BooleanValue(this.booleanValue() == booleanValue.booleanValue());
    }

    @Override
    public Value les(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform < over " + this + " value");
    }

    @Override
    public Value gre(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform > over " + this + " value");
    }

    @Override
    public Value leq(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform <= over " + this + " value");
    }

    @Override
    public Value geq(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform >= over " + this + " value");
    }
}
