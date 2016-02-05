package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class CallInstruction extends Instruction {
    public CallInstruction(Operand target) {
        this.type = Type.CALL;
        this.operands = new ArrayList<Operand>();
        this.operands.add(target);

        this.validSize = 1;
        this.validRefs = new int[]{};
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        String routineName = this.getRef(0);
        vm.call(module, routineName);
        return false;
    }
}
