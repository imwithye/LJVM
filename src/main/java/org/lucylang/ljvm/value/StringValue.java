package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.StringType;
import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class StringValue extends Value {
    private StringType type;
    private String value;

    public StringValue(String value) {
        this.type = new StringType();
        this.value = value;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public Integer intValue() {
        return Integer.parseInt(this.value);
    }

    @Override
    public Double floatValue() {
        return Double.parseDouble(this.value);
    }

    @Override
    public String stringValue() {
        return this.value;
    }

    @Override
    public Boolean booleanValue() throws ValueUnavailableException {
        throw new ValueUnavailableException();
    }

    @Override
    public Value add(Value value) throws TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException();
        }
        return new StringValue(this.value + ((StringValue) value).stringValue());
    }

    @Override
    public Value sub(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value mul(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value div(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value and(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value or(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException();
        }
        throw new ValueUnavailableException();
    }

    @Override
    public Value not() throws ValueUnavailableException {
        throw new ValueUnavailableException();
    }

    @Override
    public Value equ(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException();
        }
        return new BooleanValue(this.stringValue().compareTo(value.stringValue()) == 0);
    }

    @Override
    public Value les(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException();
        }
        return new BooleanValue(this.stringValue().compareTo(value.stringValue()) < 0);
    }

    @Override
    public Value gre(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException();
        }
        return new BooleanValue(this.stringValue().compareTo(value.stringValue()) > 0);
    }

    @Override
    public Value leq(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException();
        }
        return new BooleanValue(this.stringValue().compareTo(value.stringValue()) <= 0);
    }

    @Override
    public Value geq(Value value) throws TypeUnmatchedException {
        if (!(value instanceof StringValue)) {
            throw new TypeUnmatchedException();
        }
        return new BooleanValue(this.stringValue().compareTo(value.stringValue()) >= 0);
    }

    @Override
    public NumberValue toNumberValue() {
        return new NumberValue(this.floatValue());
    }

    @Override
    public StringValue toStringValue() {
        return this;
    }

    @Override
    public BooleanValue toBooleanValue() throws ValueUnavailableException {
        return new BooleanValue(this.booleanValue());
    }
}
