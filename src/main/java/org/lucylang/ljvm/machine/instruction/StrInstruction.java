package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.UndefinedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class StrInstruction extends Instruction {
    public StrInstruction(Operand<String> ref, Operand value) {
        this.type = Type.STR;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);
        this.operands.add(value);

        this.validSize = 2;
        this.validRefs = new int[]{0};
    }


    @Override
    public void executeValid(Machine vm) throws InvalidInstruction, ValueUnavailableException, UndefinedException {
        Value v = this.getValue(vm, 1);
        Register result = this.getRegister(vm, 0);
        result.assignValue(v.toStringValue());
    }
}
