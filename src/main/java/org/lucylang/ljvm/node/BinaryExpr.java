package org.lucylang.ljvm.node;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;

public abstract class BinaryExpr extends Node implements IValue {
    private IValue lhs;
    private IValue rhs;

    public BinaryExpr(IValue lhs, IValue rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue getLhs() {
        return this.lhs;
    }

    public IValue getRhs() {
        return this.rhs;
    }

    public abstract Instruction getInstruction(RefOperand target, RefOperand lhsRef, RefOperand rhsRef);
}
