package org.lucylang.ljvm.machine.instruction;

import java.util.ArrayList;

public class MovInstruction extends Instruction {
    public MovInstruction(Operand<String> ref, Operand value) {
        this.type = Type.MOV;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);
        this.operands.add(value);

        this.validSize = 2;
        this.validRefs = new int[]{0};
    }
}
