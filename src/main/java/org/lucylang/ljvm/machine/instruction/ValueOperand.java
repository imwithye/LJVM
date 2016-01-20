package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.value.BooleanValue;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.Value;

public class ValueOperand extends Operand<Value> {
    private final Value value;

    public ValueOperand(Value value) {
        this.value = value;
    }

    public ValueOperand(int value) {
        this.value = new NumberValue(value);
    }

    public ValueOperand(double value) {
        this.value = new NumberValue(value);
    }

    public ValueOperand(boolean value) {
        this.value = new BooleanValue(value);
    }

    public ValueOperand(String value) {
        this.value = new StringValue(value);
    }

    @Override
    public Value getValue() {
        return this.value;
    }
}
