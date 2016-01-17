package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.HashMap;
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

    public Machine execute(Instruction instruction) throws InvalidInstructionException, UndefinedException, OverdefinedException, ValueUnavailableException, TypeUnmatchedException {
        switch (instruction.getType()) {
            case NULL:
                break;
            case DEFINE: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                if (instruction.isRef(1)) {
                    Value value = this.getValue(instruction.getRef(1));
                    this.defineRegister(instruction.getRef(0), value);
                } else {
                    this.defineRegister(instruction.getRef(0), instruction.getValue(1));
                }
                break;
            }
            case MV: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                if (instruction.isRef(1)) {
                    Value value = this.getValue(instruction.getRef(1));
                    this.assignRegister(instruction.getRef(0), value);
                } else {
                    this.assignRegister(instruction.getRef(0), instruction.getValue(1));
                }
                break;
            }
            case ADD: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1, op2;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                if (instruction.isRef(2)) {
                    op2 = this.getValue(instruction.getRef(2));
                } else {
                    op2 = instruction.getValue(2);
                }
                Value result = op1.add(op2);
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case SUB: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1, op2;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                if (instruction.isRef(2)) {
                    op2 = this.getValue(instruction.getRef(2));
                } else {
                    op2 = instruction.getValue(2);
                }
                Value result = op1.sub(op2);
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case MUL: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1, op2;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                if (instruction.isRef(2)) {
                    op2 = this.getValue(instruction.getRef(2));
                } else {
                    op2 = instruction.getValue(2);
                }
                Value result = op1.mul(op2);
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case DIV: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1, op2;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                if (instruction.isRef(2)) {
                    op2 = this.getValue(instruction.getRef(2));
                } else {
                    op2 = instruction.getValue(2);
                }
                Value result = op1.div(op2);
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case AND: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1, op2;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                if (instruction.isRef(2)) {
                    op2 = this.getValue(instruction.getRef(2));
                } else {
                    op2 = instruction.getValue(2);
                }
                Value result = op1.and(op2);
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case OR: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1, op2;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                if (instruction.isRef(2)) {
                    op2 = this.getValue(instruction.getRef(2));
                } else {
                    op2 = instruction.getValue(2);
                }
                Value result = op1.or(op2);
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case NOT: {
                if (!instruction.isRef(0)) {
                    throw new InvalidInstructionException();
                }
                Value op1;
                if (instruction.isRef(1)) {
                    op1 = this.getValue(instruction.getRef(1));
                } else {
                    op1 = instruction.getValue(1);
                }
                Value result = op1.not();
                this.assignRegister(instruction.getRef(0), result);
                break;
            }
            case GOTO: {
                break;
            }
            default:
                break;
        }
        return this;
    }
}
