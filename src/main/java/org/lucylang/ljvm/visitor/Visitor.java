package org.lucylang.ljvm.visitor;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;
import org.lucylang.ljvm.machine.instruction.ValueOperand;
import org.lucylang.ljvm.node.*;

import java.util.ArrayList;

public abstract class Visitor {
    public abstract ValueOperand visitBooleanLiteral(BooleanLiteral node);

    public abstract ValueOperand visitNumberLiteral(NumberLiteral node);

    public abstract ValueOperand visitStringLiteral(StringLiteral node);

    public abstract RefOperand visitVarName(VarName node);

    public abstract void visitBinaryExpr(BinaryExpr expr, ArrayList<Instruction> instructions);

    public abstract void visitAssignment(Assignment assignment, ArrayList<Instruction> instructions);
}
