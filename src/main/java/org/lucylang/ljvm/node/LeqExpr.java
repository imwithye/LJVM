package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.LeqInstruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class LeqExpr extends BinaryExpr {
    public LeqExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new LeqInstruction(target, lhsRef, rhsRef);
    }
}
