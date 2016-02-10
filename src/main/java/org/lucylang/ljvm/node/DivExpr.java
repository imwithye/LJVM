package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.DivInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class DivExpr extends BinaryExpr {
    public DivExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new DivInstruction(target, lhsRef, rhsRef);
    }
}
