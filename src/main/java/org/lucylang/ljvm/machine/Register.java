package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.value.Value;

public class Register {
    protected Value value;

    public Register(Value value) {
        this.assignValue(value);
    }

    public Value getValue() {
        return this.value;
    }

    public Type getType() {
        return this.value.getType();
    }

    public Register assignValue(Value value) {
        assert value != null;
        this.value = value;
        return this;
    }
}
