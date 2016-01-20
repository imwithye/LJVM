package org.lucylang.ljvm.machine.instruction;

import java.util.ArrayList;

public class MulInstruction extends Instruction {
    public MulInstruction(Operand<String> ref, Operand op1, Operand op2) {
        this.type = Type.MUL;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);
        this.operands.add(op1);
        this.operands.add(op2);

        this.validSize = 3;
        this.validRefs = new int[]{0};
    }
}
