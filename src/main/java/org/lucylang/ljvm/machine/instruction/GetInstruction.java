package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.Register;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;
import org.lucylang.ljvm.type.TypeUnmatchedException;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.ValueUnavailableException;

import java.util.ArrayList;
import java.util.Scanner;

public class GetInstruction extends Instruction {
    public GetInstruction(Operand value) {
        this.type = Type.GET;
        this.operands = new ArrayList<Operand>();
        this.operands.add(value);

        this.validSize = 1;
        this.validRefs = new int[]{0};
    }

    @Override
    public boolean executeValid(Machine vm, Module module) throws InvalidInstruction, TypeUnmatchedException, ValueUnavailableException, UndefinedException, OverdefinedException {
        Register r = this.getRegister(vm, 0);
        Scanner scanner = new Scanner(System.in);
        r.assignValue(new StringValue(scanner.next()));
        return false;
    }
}
