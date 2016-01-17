package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class NumberValue extends Value {
    private NumberType type;
    private Double value;

    public NumberValue(Integer value) {
        this.type = new NumberType();
        this.value = value.doubleValue();
    }

    public NumberValue(Double value) {
        this.type = new NumberType();
        this.value = value;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public Integer intValue() {
        return this.value.intValue();
    }

    @Override
    public Double floatValue() {
        return this.value;
    }

    @Override
    public String stringValue() {
        return this.value.toString();
    }

    @Override
    public Boolean booleanValue() throws ValueUnavailableException {
        throw new ValueUnavailableException();
    }

    @Override
    public Value add(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException();
        }
        return new NumberValue(this.value + ((NumberValue) value).floatValue());
    }

    @Override
    public Value sub(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException();
        }
        return new NumberValue(this.value - ((NumberValue) value).floatValue());
    }

    @Override
    public Value mul(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException();
        }
        return new NumberValue(this.value * ((NumberValue) value).floatValue());
    }

    @Override
    public Value div(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException();
        }
        return new NumberValue(this.value / ((NumberValue) value).floatValue());
    }

    @Override
    public Value and(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value or(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value not() throws ValueUnavailableException {
        throw new ValueUnavailableException();
    }
}
