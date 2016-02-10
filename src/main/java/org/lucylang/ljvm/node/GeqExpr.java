package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.GeqInstruction;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class GeqExpr extends BinaryExpr {
    public GeqExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new GeqInstruction(target, lhsRef, rhsRef);
    }
}
