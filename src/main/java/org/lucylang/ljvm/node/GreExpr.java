package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.GreInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class GreExpr extends BinaryExpr {
    public GreExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new GreInstruction(target, lhsRef, rhsRef);
    }
}
