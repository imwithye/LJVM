package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.OrInstruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public class OrExpr extends BinaryExpr {
    public OrExpr(IValue lhs, IValue rhs) {
        super(lhs, rhs);
    }

    @Override
    public Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef) {
        return new OrInstruction(target, lhsRef, rhsRef);
    }
}
