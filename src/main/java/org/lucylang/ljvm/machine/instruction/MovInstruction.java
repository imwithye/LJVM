package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.UndefinedException;
import org.lucylang.ljvm.value.Value;

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

    @Override
    public void executeValid(Machine vm) throws InvalidInstruction, UndefinedException {
        Value v = this.getValue(vm, 1);
        Register result = this.getRegister(vm, 0);
        result.assignValue(v);
    }
}
