package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.EquInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class EquExpr extends BinaryExpr {
    public EquExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new EquInstruction(target, lhsRef, rhsRef);
    }
}
