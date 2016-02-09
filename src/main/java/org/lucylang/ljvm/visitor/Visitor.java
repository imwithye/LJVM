package org.lucylang.ljvm.visitor;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;
import org.lucylang.ljvm.machine.instruction.ValueOperand;
import org.lucylang.ljvm.node.*;

import java.util.ArrayList;

public abstract class Visitor {
    protected abstract ValueOperand visitBooleanLiteral(BooleanLiteral node);

    protected abstract ValueOperand visitNumberLiteral(NumberLiteral node);

    protected abstract ValueOperand visitStringLiteral(StringLiteral node);

    protected abstract RefOperand visitVarName(VarName node);

    protected abstract int visitBinaryExpr(BinaryExpr expr, ArrayList<Instruction> instructions);

    protected abstract int visitAssignment(Assignment assignment, ArrayList<Instruction> instructions);

    protected abstract int visitIfElse(IfElse ifElse, ArrayList<Instruction> instructions);

    protected abstract int visitWhile(While whileStmt, ArrayList<Instruction> instructions);

    public int visitStmt(IStmt stmt, ArrayList<Instruction> instructions) {
        if (stmt instanceof Assignment) {
            return this.visitAssignment((Assignment) stmt, instructions);
        } else if (stmt instanceof IfElse) {
            return this.visitIfElse((IfElse) stmt, instructions);
        } else if (stmt instanceof While) {
            return this.visitWhile((While) stmt, instructions);
        } else {
            return 0;
        }
    }
}
