package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class BneInstruction extends Instruction {
    public BneInstruction(Operand value, Operand<Value> target) {
        this.type = Type.BNE;
        this.operands = new ArrayList<Operand>();
        this.operands.add(value);
        this.operands.add(target);

        this.validSize = 2;
        this.validRefs = new int[]{};
    }

    public void setTarget(Operand<Value> target) {
        this.operands.remove(1);
        this.operands.add(target);
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, ValueUnavailableException, UndefinedException {
        Value v = this.getValue(vm, 0);
        if (v.booleanValue()) {
            return false;
        }
        Value target = this.getValue(vm, 1);
        if (!(target instanceof NumberValue)) {
            throw new InvalidInstruction(target, this);
        }
        int next = target.intValue() - 1;
        vm.setProgramCounter(next);
        return false;
    }
}
