package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.StringType;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.Value;
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
    public void executeValid(Machine vm) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Value v = this.getValue(vm, 0);
        if(!v.getType().equals(new StringType())) {
            throw new InvalidInstruction();
        }
        String mf = v.stringValue();
        //TODO: currently support only one module
        vm.call(mf);
    }
}
