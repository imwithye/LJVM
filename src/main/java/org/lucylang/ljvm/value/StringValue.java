package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.StringType;
import org.lucylang.ljvm.type.Type;

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
}
