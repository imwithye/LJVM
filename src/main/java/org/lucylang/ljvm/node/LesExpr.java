package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.LesInstruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class LesExpr extends BinaryExpr {
    public LesExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new LesInstruction(target, lhsRef, rhsRef);
    }
}
