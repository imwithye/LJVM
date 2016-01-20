package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Machine {
    private HashMap<String, Register> registers;
    private Stack<Value> memoryStack;

    public Machine() {
        this.reset();
    }

    public Machine reset() {
        registers = new HashMap<String, Register>();
        memoryStack = new Stack<Value>();
        return this;
    }

    public Register getRegister(String ref) throws UndefinedException {
        Register r = this.registers.get(ref);
        if (r == null) {
            throw new UndefinedException();
        }
        return r;
    }

    public Value getValue(String ref) throws UndefinedException {
        return this.getRegister(ref).getValue();
    }

    public Machine defineRegister(String ref, Value value) throws OverdefinedException {
        if (this.registers.get(ref) == null) {
            this.registers.put(ref, new Register(value));
        } else {
            throw new OverdefinedException();
        }
        return this;
    }

    public Machine assignRegister(String ref, Value value) throws UndefinedException {
        this.getRegister(ref).assignValue(value);
        return this;
    }
}
