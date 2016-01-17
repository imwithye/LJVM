package org.lucylang.ljvm.value;

import org.lucylang.ljvm.type.BooleanType;
import org.lucylang.ljvm.type.Type;

public class BooleanValue extends Value {
    private BooleanType type;
    private Boolean value;

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
}
