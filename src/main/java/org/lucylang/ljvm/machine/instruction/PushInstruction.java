package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.value.Value;

import java.util.ArrayList;

public class PushInstruction extends Instruction {
    public PushInstruction(Operand<String> ref) {
        this.type = Type.PUSH;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);

        this.validSize = 1;
        this.validRefs = new int[]{0};
    }

    @Override
    public void executeValid(Machine vm) throws InvalidInstruction, UndefinedException {
        Value v = this.getValue(vm, 0);
        vm.pushValue(v);
    }
}
