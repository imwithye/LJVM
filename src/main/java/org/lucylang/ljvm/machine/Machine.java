package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.machine.instruction.DefInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.Stack;

public class Machine {
    private int next;
    private Scope<String, Register> registers;
    private Stack<Instruction[]> instructionStack;
    private Stack<Integer> nextStack;
    private Stack<Value> memoryStack;

    public Machine() {
        this.reset();
    }

    public Machine reset() {
        this.next = 0;
        this.registers = new Scope<String, Register>();
        this.instructionStack = new Stack<Instruction[]>();
        this.nextStack = new Stack<Integer>();
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
        if (!this.registers.isDefined(ref)) {
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

    public Machine execute(Instruction[] instructions, int pos, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        if (instructions == null) {
            return this;
        }
        this.next = pos;
        while (this.next < instructions.length) {
            Instruction i = instructions[this.next];
            if(!i.execute(this, module)) {
                this.next++;
            } else {
                break;
            }
        }
        return this;
    }

    public Machine call(Module module, String routineName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Routine routine = module.getRoutine(routineName);
        Scope<String, Register> routineScope = new Scope<String, Register>(this.registers);
        Scope<String, Register> currentScope = this.registers;
        int next = this.next;
        this.registers = routineScope;
        this.execute(routine.getInstructions(), 0, module);
        this.next = next;
        this.registers = routineScope;
        return this;
    }

    public Machine execute(Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        DefInstruction[] vars = module.getVars();
        this.execute(vars, 0, module);
        this.call(module, "main");
        return this;
    }
}
