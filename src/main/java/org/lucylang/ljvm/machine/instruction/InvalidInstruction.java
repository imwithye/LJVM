package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.exception.RuntimeException;

public class InvalidInstruction extends RuntimeException {
    public InvalidInstruction(Instruction instruction) {
        super("Missing operand or operand is null for instruction " + instruction);
    }

    public InvalidInstruction(Object o, Instruction instruction) {
        super("Unexpected value type " + o.getClass().getSimpleName() + " for instruction " + instruction);
    }

    public InvalidInstruction(int expectedSize, int realSize, Instruction instruction) {
        super("Expecting operand size " + expectedSize + " but get " + realSize + " for instruction " + instruction);
    }
}
