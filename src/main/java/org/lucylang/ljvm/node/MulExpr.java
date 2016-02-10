package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.MulInstruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class MulExpr extends BinaryExpr {
    public MulExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new MulInstruction(target, lhsRef, rhsRef);
    }
}
