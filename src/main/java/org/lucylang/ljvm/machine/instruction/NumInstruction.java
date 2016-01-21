package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class NumInstruction extends Instruction {
    public NumInstruction(Operand<String> ref, Operand value) {
        this.type = Type.NUM;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);
        this.operands.add(value);

        this.validSize = 2;
        this.validRefs = new int[]{0};
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, ValueUnavailableException, UndefinedException {
        Value v = this.getValue(vm, 1);
        Register result = this.getRegister(vm, 0);
        result.assignValue(v.toNumberValue());
        return false;
    }
}
