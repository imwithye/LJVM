package org.lucylang.ljvm.machine.instruction;

import java.util.ArrayList;

public class NullInstruction extends Instruction {
    public NullInstruction() {
        this.type = Type.NULL;
        this.operands = new ArrayList<Operand>();

        this.validSize = 0;
        this.validRefs = new int[]{};
    }
}
