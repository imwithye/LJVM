package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.Type;

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
}
