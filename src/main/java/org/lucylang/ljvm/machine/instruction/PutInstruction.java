package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.value.Value;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class PutInstruction extends Instruction {
    public PutInstruction(Operand value) {
        this.type = Type.PUT;
        this.operands = new ArrayList<Operand>();
        this.operands.add(value);

        this.validSize = 1;
        this.validRefs = new int[]{0};
    }


    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, UndefinedException, ValueUnavailableException {
        Value v = this.getValue(vm, 0);
        System.out.print(v.stringValue());
        return false;
    }
}
