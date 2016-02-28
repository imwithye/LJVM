package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.NotExecutableException;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.NoneValue;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.Stack;

public class Machine {
    protected int pc;
    protected Scope<String, Register> currentScope;
    protected Scope<String, Module> imports;
    protected Stack<Value> memoryStack;

    public Machine() {
        this.reset();
    }

    public Machine reset() {
        this.pc = 0;
        this.currentScope = null;
        this.imports = new Scope<String, Module>();
        this.memoryStack = new Stack<Value>();
        return this;
    }

    public Machine setProgramCounter(int pc) {
        this.pc = pc;
        return this;
    }

    public Register getRegister(String ref) {
        assert ref != null;
        try {
            Register r = this.currentScope.safeGet(ref);
            return r;
        } catch (UndefinedException udf) {
            Register r = new Register(new NoneValue());
            this.currentScope.set(ref, r);
            return r;
        }
    }

    public Value getValue(String ref) {
        assert ref != null;
        return this.getRegister(ref).getValue();
    }

    public Machine pushValue(Value value) {
        assert value != null;
        this.memoryStack.push(value.copy());
        return this;
    }

    public Value popValue() {
        return this.memoryStack.pop();
    }

    public Value peekValue() {
        return this.memoryStack.peek();
    }

    private Machine execute(Instruction[] instructions, int pos, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        assert instructions != null;
        assert module != null;
        if (instructions == null) {
            return this;
        }
        boolean hasReturn = false;
        this.pc = pos;
        while (this.pc < instructions.length) {
            Instruction i = instructions[this.pc];
            if (!i.execute(this, module)) {
                this.pc++;
            } else {
                hasReturn = true;
                break;
            }
        }
        if (!hasReturn) {
            this.pushValue(new NoneValue());
        }
        return this;
    }

    public Machine call(Module module, String routineName) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        assert module != null;
        assert routineName != null;
        Routine routine = module.getRoutine(routineName);
        Scope<String, Register> currentScope = this.currentScope;
        int next = this.pc;
        this.currentScope = new Scope<String, Register>(routineName);
        this.execute(routine.getInstructions(), 0, module);
        this.pc = next;
        this.currentScope = currentScope;
        return this;
    }

    public Value execute(Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException, NotExecutableException {
        assert module != null;
        if(!module.isMain()) {
            throw new NotExecutableException(module);
        }
        this.call(module, "main");
        return this.popValue();
    }
}
