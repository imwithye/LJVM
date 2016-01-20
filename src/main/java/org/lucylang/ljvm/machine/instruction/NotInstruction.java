package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

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

    @Override
    public void executeValid(Machine vm) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException {
        Value v = this.getValue(vm, 1);
        Register result = this.getRegister(vm, 0);
        result.assignValue(v.not());
    }
}
