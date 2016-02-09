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

    public abstract int visitBinaryExpr(BinaryExpr expr, ArrayList<Instruction> instructions);

    public abstract int visitAssignment(Assignment assignment, ArrayList<Instruction> instructions);

    public abstract int visitIfElse(IfElse ifElse, ArrayList<Instruction> instructions);

    public int visitStmt(IStmt stmt, ArrayList<Instruction> instructions) {
        if (stmt instanceof Assignment) {
            return this.visitAssignment((Assignment) stmt, instructions);
        } else if (stmt instanceof IfElse) {
            return this.visitIfElse((IfElse) stmt, instructions);
        } else {
            return 0;
        }
    }
}
