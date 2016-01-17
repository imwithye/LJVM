package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.type.Type;
import org.lucylang.ljvm.value.Value;

public class Register {
    private Value value;

    public Register(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return this.value;
    }

    public Type getType() {
        return this.value.getType();
    }

    public Value assignValue(Value value) {
        this.value = value;
        return this.value;
    }
}
