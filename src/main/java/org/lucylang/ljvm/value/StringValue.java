package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.StringType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class StringValue extends Value {
    private StringType type;
    private String value;

    public StringValue() {
        this("");
    }

    public StringValue(String value) {
        this.type = new StringType();
        this.value = value;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public Integer intValue() throws ValueUnavailableException {
        try {
            return Integer.parseInt(this.value);
        } catch (NumberFormatException e) {
            throw new ValueUnavailableException("string " + this.stringValue() + " is not an integer");
        }
    }

    @Override
    public Double floatValue() throws ValueUnavailableException {
        try {
            return Double.parseDouble(this.value);
        } catch (NumberFormatException e) {
            throw new ValueUnavailableException("string " + this.stringValue() + " is not a float");
        }
    }

    @Override
    public String stringValue() {
        return this.value;
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
        return new StringValue(this.value + ((StringValue) value).stringValue());
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
        if (!(value instanceof StringValue)) {
            return new BooleanValue(false);
        }
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(this.stringValue().compareTo(stringValue.stringValue()) == 0);
    }

    @Override
    public BooleanValue les(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(this.stringValue().compareTo(stringValue.stringValue()) < 0);
    }

    @Override
    public BooleanValue gre(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(this.stringValue().compareTo(stringValue.stringValue()) > 0);
    }

    @Override
    public BooleanValue leq(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(this.stringValue().compareTo(stringValue.stringValue()) <= 0);
    }

    @Override
    public BooleanValue geq(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        StringValue stringValue = (StringValue) value;
        return new BooleanValue(this.stringValue().compareTo(stringValue.stringValue()) >= 0);
    }

    @Override
    public Value valueAt(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (value instanceof NumberValue) {
            return new StringValue("" + this.value.charAt(((NumberValue) value).intValue()));
        }
        throw new TypeUnmatchedException(new NumberType(), value.getType());
    }

    @Override
    public NumberValue length() {
        return new NumberValue(this.value.length());
    }
}
