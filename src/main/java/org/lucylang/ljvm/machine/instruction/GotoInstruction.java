package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class GotoInstruction extends Instruction {
    public GotoInstruction(Operand<Value> target) {
        this.type = Type.GOTO;
        this.operands = new ArrayList<Operand>();
        this.operands.add(target);

        this.validSize = 1;
        this.validRefs = new int[]{};
    }

    public void setTarget(Operand<Value> target) {
        assert target != null;
        this.operands.remove(0);
        this.operands.add(target);
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Value v = this.getValue(vm, 0);
        if (!(v instanceof NumberValue)) {
            throw new InvalidInstruction();
        }
        int next = v.intValue() - 1;
        vm.setNext(next);
        return false;
    }
}
