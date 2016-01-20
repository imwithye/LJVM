package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class SubInstruction extends Instruction {
    public SubInstruction(Operand<String> ref, Operand op1, Operand op2) {
        this.type = Type.SUB;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);
        this.operands.add(op1);
        this.operands.add(op2);

        this.validSize = 3;
        this.validRefs = new int[]{0};
    }

    @Override
    public void executeValid(Machine vm) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException {
        Value v1 = this.getValue(vm, 1);
        Value v2 = this.getValue(vm, 2);
        Register result = this.getRegister(vm, 0);
        result.assignValue(v1.sub(v2));
    }
}
