package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.machine.module.Function;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.HashMap;
import java.util.Stack;

public class Machine {
    private int next;
    private HashMap<String, Register> registers;
    private Stack<Value> memoryStack;

    public Machine() {
        this.reset();
    }

    public Machine reset() {
        this.next = 0;
        this.registers = new HashMap<String, Register>();
        this.memoryStack = new Stack<Value>();
        return this;
    }

    public Machine setNext(int next) {
        this.next = next;
        return this;
    }

    public int getNext() {
        return this.next;
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

    public Machine assignRegister(String ref, Value value) throws UndefinedException {
        this.getRegister(ref).assignValue(value);
        return this;
    }

    public Machine defineRegister(String ref, Value value) throws OverdefinedException {
        if (this.registers.get(ref) == null) {
            this.registers.put(ref, new Register(value));
        } else {
            throw new OverdefinedException();
        }
        return this;
    }

    public Machine pushValue(Value value) {
        this.memoryStack.push(value);
        return this;
    }

    public Machine popValue() {
        this.memoryStack.pop();
        return this;
    }

    public Value peek() throws UndefinedException {
        if (this.memoryStack.size() == 0) {
            throw new UndefinedException();
        }
        return this.memoryStack.peek();
    }

    public Machine execute(Instruction[] instructions) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        this.next = 0;
        while (this.next < instructions.length) {
            Instruction i = instructions[this.next];
            i.execute(this);
            this.next++;
        }
        return this;
    }

    public Machine execute(Function function) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        return this.execute(function.getInstructions());
    }

    public Machine execute(Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        // TODO: add non main module method
        return this.execute(module.getFunction("main"));
    }
}
