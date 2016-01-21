package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.UndefinedException;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class BneInstruction extends Instruction {
    public BneInstruction(Operand value, Operand target) {
        this.type = Type.BNE;
        this.operands = new ArrayList<Operand>();
        this.operands.add(value);
        this.operands.add(target);

        this.validSize = 2;
        this.validRefs = new int[]{0};
    }

    @Override
    public void executeValid(Machine vm) throws InvalidInstruction, ValueUnavailableException, UndefinedException {
        Value v = this.getValue(vm, 0);
        if (v.booleanValue()) {
            return;
        }
        Value target = this.getValue(vm, 1);
        if (!(target instanceof NumberValue)) {
            throw new InvalidInstruction();
        }
        int next = target.intValue();
        vm.setNext(next);
    }
}
