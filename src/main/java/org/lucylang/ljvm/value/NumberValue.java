package org.lucylang.ljvm.value;

public class NumberValue extends Value {
    private Double value;

    public NumberValue(Integer value) {
        this.value = value.doubleValue();
    }

    public NumberValue(Double value) {
        this.value = value;
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
