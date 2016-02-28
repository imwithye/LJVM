package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class NumberValue extends Value {
    private NumberType type;
    private Double value;

    public NumberValue() {
        this(0);
    }

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
        if (this.value.equals(new Double(this.value.intValue()))) {
            return new Integer(this.value.intValue()).toString();
        } else {
            return this.value.toString();
        }
    }

    @Override
    public Boolean booleanValue() throws ValueUnavailableException {
        throw new ValueUnavailableException("boolean", this);
    }

    @Override
    public Value add(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        return new NumberValue(this.value + ((NumberValue) value).floatValue());
    }

    @Override
    public Value sub(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        return new NumberValue(this.value - ((NumberValue) value).floatValue());
    }

    @Override
    public Value mul(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        return new NumberValue(this.value * ((NumberValue) value).floatValue());
    }

    @Override
    public Value div(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        return new NumberValue(this.value / ((NumberValue) value).floatValue());
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
        if (!(value instanceof NumberValue)) {
            return new BooleanValue(false);
        }
        NumberValue numberValue = (NumberValue) value;
        return new BooleanValue(this.intValue().equals(numberValue.intValue()) || this.floatValue().equals(numberValue.floatValue()));
    }

    @Override
    public BooleanValue les(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        NumberValue numberValue = (NumberValue) value;
        return new BooleanValue(this.intValue().compareTo(numberValue.intValue()) < 0 || this.floatValue().compareTo(numberValue.floatValue()) < 0);
    }

    @Override
    public BooleanValue gre(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        NumberValue numberValue = (NumberValue) value;
        return new BooleanValue(this.intValue().compareTo(numberValue.intValue()) > 0 || this.floatValue().compareTo(numberValue.floatValue()) > 0);
    }

    @Override
    public BooleanValue leq(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        NumberValue numberValue = (NumberValue) value;
        return new BooleanValue(this.intValue().compareTo(numberValue.intValue()) <= 0 || this.floatValue().compareTo(numberValue.floatValue()) <= 0);
    }

    @Override
    public BooleanValue geq(Value value) throws TypeUnmatchedException {
        if (!(value instanceof NumberValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        NumberValue numberValue = (NumberValue) value;
        return new BooleanValue(this.intValue().compareTo(numberValue.intValue()) >= 0 || this.floatValue().compareTo(numberValue.floatValue()) >= 0);
    }

    @Override
    public Value valueAt(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (value instanceof NumberValue) {
            throw new ValueUnavailableException("unable to perform [] over " + this + " value");
        }
        throw new TypeUnmatchedException(new NumberType(), value.getType());
    }

    @Override
    public void set(Value index, Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException("unable to perform set[] over " + this + " value");
    }

    @Override
    public NumberValue length() throws ValueUnavailableException {
        throw new ValueUnavailableException("length", this);
    }
}
