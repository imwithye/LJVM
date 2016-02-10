package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.AddInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class AddExpr extends BinaryExpr {
    public AddExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new AddInstruction(target, lhsRef, rhsRef);
    }
}
