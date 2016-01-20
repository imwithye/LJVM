package org.lucylang.ljvm.machine.instruction;

import java.util.ArrayList;

public class GotoInstruction extends Instruction {
    public GotoInstruction(Operand ref) {
        this.type = Type.ADD;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);

        this.validSize = 1;
        this.validRefs = new int[]{};
    }
}
