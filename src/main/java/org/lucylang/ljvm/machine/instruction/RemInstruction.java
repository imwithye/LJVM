package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class RemInstruction extends Instruction {
    public RemInstruction(Operand ref) {
        this.type = Type.REM;
        this.operands = new ArrayList<Operand>();
        this.operands.add(ref);

        this.validSize = 1;
        this.validRefs = new int[]{};
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Register r = this.getRegister(vm, 0);
        r.assignValue(new NumberValue(Math.random()));
        return false;
    }
}
