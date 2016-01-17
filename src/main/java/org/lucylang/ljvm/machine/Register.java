package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.value.Value;

public class Register {
    private Value value;

    public Register(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return this.value;
    }

    public Value assignValue(Value value) {
        this.value = value;
        return this.value;
    }
}
