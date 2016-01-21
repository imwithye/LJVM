package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;

public class RetInstruction extends Instruction {
    public RetInstruction() {
        this.type = Type.RET;
        this.operands = new ArrayList<Operand>();

        this.validSize = 0;
        this.validRefs = new int[]{};
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        return true;
    }
}
