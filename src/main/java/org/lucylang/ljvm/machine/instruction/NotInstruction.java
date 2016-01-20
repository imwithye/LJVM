package org.lucylang.ljvm.machine.instruction;

import java.util.ArrayList;

public class NotInstruction extends Instruction {
    public NotInstruction(Operand<String> ref, Operand op) {
        this.type = Type.NOT;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);
        this.operands.add(op);

        this.validSize = 2;
        this.validRefs = new int[]{0};
    }
}
