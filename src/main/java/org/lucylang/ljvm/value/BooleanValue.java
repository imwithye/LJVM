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
    public Value add(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public Value sub(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public Value mul(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public Value div(Value value) throws ValueUnavailableException, TypeUnmatchedException {
        if (!this.isSameType(value)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        throw new ValueUnavailableException(new BooleanValue());
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
    public Value equ(Value value) throws TypeUnmatchedException {
        if (!(value instanceof BooleanValue)) {
            throw new TypeUnmatchedException(this.getType(), value.getType());
        }
        BooleanValue booleanValue = (BooleanValue) value;
        return new BooleanValue(this.booleanValue() == booleanValue.booleanValue());
    }

    @Override
    public Value les(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public Value gre(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public Value leq(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public Value geq(Value value) throws ValueUnavailableException {
        throw new ValueUnavailableException(new BooleanValue());
    }

    @Override
    public NumberValue toNumberValue() {
        if (this.booleanValue()) {
            return new NumberValue(this.intValue());
        } else {
            return new NumberValue(this.intValue());
        }
    }

    @Override
    public StringValue toStringValue() {
        if (this.booleanValue()) {
            return new StringValue(this.stringValue());
        } else {
            return new StringValue(this.stringValue());
        }
    }

    @Override
    public BooleanValue toBooleanValue() {
        return this;
    }
}
