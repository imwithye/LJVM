package org.lucylang.ljvm.generator;

import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.node.*;
import org.lucylang.ljvm.value.ArrayValue;
import org.lucylang.ljvm.value.NumberValue;

import java.util.ArrayList;

public class StmtCodeGenerator {
    private int registerCounter = 0;

    private RefOperand getNewRegister() {
        String ref = "$" + registerCounter;
        registerCounter++;
        return new RefOperand(ref);
    }

    private int acceptLiteral(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        assert target != null;
        ValueOperand vo = null;
        if (node instanceof BooleanLiteral) {
            vo = this.visitBooleanLiteral((BooleanLiteral) node);
        } else if (node instanceof NumberLiteral) {
            vo = this.visitNumberLiteral((NumberLiteral) node);
        } else if (node instanceof StringLiteral) {
            vo = this.visitStringLiteral((StringLiteral) node);
        } else if (node instanceof ArrayLiteral) {
            return this.visitArrayLiteral((ArrayLiteral) node, instructions, target);
        }
        if (vo != null) {
            instructions.add(new MovInstruction(target, vo));
            return 1;
        } else {
            return 0;
        }
    }

    private int acceptRef(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        assert target != null;
        RefOperand ref = null;
        if (node instanceof VarName) {
            ref = this.visitVarName((VarName) node);
        }
        if (ref != null) {
            instructions.add(new MovInstruction(target, ref));
            return 1;
        } else {
            return 0;
        }
    }

    private int acceptBinaryExpr(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        assert target != null;
        if (!(node instanceof BinaryExpr)) return 0;
        BinaryExpr expr = (BinaryExpr) node;
        IValue lhs = expr.getLhs(), rhs = expr.getRhs();
        int count = 0;
        RefOperand lhsRef = this.getNewRegister();
        count += this.acceptValue(lhs, instructions, lhsRef);
        RefOperand rhsRef = this.getNewRegister();
        count += this.acceptValue(rhs, instructions, rhsRef);
        instructions.add(expr.getInstruction(target, lhsRef, rhsRef));
        return 1 + count;
    }

    private int acceptParameter(IValue parameter, ArrayList<Instruction> instructions) {
        assert parameter != null;
        assert instructions != null;
        RefOperand parameterRef = this.getNewRegister();
        int count = this.acceptValue(parameter, instructions, parameterRef);
        instructions.add(new PushInstruction(parameterRef));
        return count + 1;
    }

    private int acceptNot(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        if (!(node instanceof NotExpr)) return 0;
        int count = 0;
        NotExpr not = (NotExpr) node;
        RefOperand result = this.getNewRegister();
        count += this.acceptValue(not.getValue(), instructions, result);
        instructions.add(new NotInstruction(target, result));
        return 1 + count;
    }

    protected int acceptCall(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        assert target != null;
        if (!(node instanceof Call)) return 0;
        Call call = (Call) node;
        int count = 0;
        for (int i = call.getParameters().size() - 1; i >= 0; i--) {
            count += this.acceptParameter(call.getParameters().get(i), instructions);
        }
        instructions.add(new CallInstruction(new RefOperand(call.getFuncName().getVarName())));
        instructions.add(new PopInstruction(target));
        return count + 2;
    }

    protected int acceptValueAt(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        assert target != null;
        if (!(node instanceof ValueAt)) return 0;
        ValueAt valueAt = (ValueAt) node;
        int count = 0;
        RefOperand index = this.getNewRegister();
        count += this.acceptValue(valueAt.getIndex(), instructions, index);
        String varName = valueAt.getVarName().getVarName();
        instructions.add(new ValueAtInstruction(target, new RefOperand(varName), index));
        return 1 + count;
    }

    protected int acceptValue(IValue node, ArrayList<Instruction> instructions, RefOperand target) {
        if (node == null) return 0;
        return this.acceptLiteral(node, instructions, target) + this.acceptRef(node, instructions, target) +
                this.acceptBinaryExpr(node, instructions, target) + this.acceptNot(node, instructions, target) +
                this.acceptCall(node, instructions, target) + this.acceptValueAt(node, instructions, target);
    }

    protected ValueOperand visitBooleanLiteral(BooleanLiteral node) {
        assert node != null;
        return new ValueOperand(node.getValue());
    }

    protected ValueOperand visitNumberLiteral(NumberLiteral node) {
        assert node != null;
        return new ValueOperand(node.getValue());
    }

    protected ValueOperand visitStringLiteral(StringLiteral node) {
        assert node != null;
        return new ValueOperand(node.getValue());
    }

    protected int visitArrayLiteral(ArrayLiteral node, ArrayList<Instruction> instructions, RefOperand target) {
        assert node != null;
        assert instructions != null;
        assert target != null;
        ArrayValue arrayValue = new ArrayValue(node.getValues().length);
        int count = 0;
        instructions.add(new MovInstruction(target, new ValueOperand(arrayValue)));
        count += 1;
        for (int i = 0; i < node.getValues().length; i++) {
            RefOperand value = this.getNewRegister();
            count += this.acceptValue(node.getValues()[i], instructions, value);
            instructions.add(new SetInstruction(target, new ValueOperand(i), value));
            count += 1;
        }
        return count;
    }

    protected RefOperand visitVarName(VarName node) {
        assert node != null;
        return new RefOperand(node.getVarName());
    }

    protected int visitAssignment(Assignment assignment, ArrayList<Instruction> instructions) {
        RefOperand ref = this.getNewRegister();
        int count = this.acceptValue(assignment.getExpr(), instructions, ref);
        RefOperand target = this.visitVarName(assignment.getVarName());
        instructions.add(new MovInstruction(target, ref));
        return count + 1;
    }

    protected int visitIfElse(IfElse ifElse, ArrayList<Instruction> instructions) {
        assert ifElse != null;
        assert instructions != null;
        IValue value = ifElse.getValue();
        RefOperand conditionRef = this.getNewRegister();
        int index = instructions.size();
        int numberOfInstruction = this.acceptValue(value, instructions, conditionRef);
        BneInstruction bneInstruction = new BneInstruction(conditionRef, new ValueOperand(0));
        instructions.add(bneInstruction);
        ArrayList<IStmt> ifStmts = ifElse.getIfStmts();
        for (int i = 0; i < ifStmts.size(); i++) {
            numberOfInstruction += this.visitStmt(ifStmts.get(i), instructions);
        }
        numberOfInstruction += 2;    // Bne Instruction and Goto Instruction
        bneInstruction.setTarget(new ValueOperand(index + numberOfInstruction));
        GotoInstruction gotoInstruction = new GotoInstruction(new ValueOperand(0));
        instructions.add(gotoInstruction);
        ArrayList<IStmt> elseStmts = ifElse.getElseStmts();
        for (int i = 0; i < elseStmts.size(); i++) {
            numberOfInstruction += this.visitStmt(elseStmts.get(i), instructions);
        }
        gotoInstruction.setTarget(new ValueOperand(index + numberOfInstruction));
        return numberOfInstruction;
    }

    protected int visitWhile(While whileStmt, ArrayList<Instruction> instructions) {
        assert whileStmt != null;
        assert instructions != null;
        IValue value = whileStmt.getValue();
        RefOperand conditionRef = this.getNewRegister();
        int index = instructions.size();
        int numberOfInstruction = this.acceptValue(value, instructions, conditionRef);
        BneInstruction bneInstruction = new BneInstruction(conditionRef, new ValueOperand(0));
        instructions.add(bneInstruction);
        ArrayList<IStmt> whileStmts = whileStmt.getStmts();
        for (int i = 0; i < whileStmts.size(); i++) {
            numberOfInstruction += this.visitStmt(whileStmts.get(i), instructions);
        }
        numberOfInstruction += 2; // GOTO;
        bneInstruction.setTarget(new ValueOperand(index + numberOfInstruction));
        instructions.add(new GotoInstruction(new ValueOperand(index)));
        return numberOfInstruction;
    }

    protected int visitReturn(Return returnStmt, ArrayList<Instruction> instructions) {
        assert returnStmt != null;
        assert instructions != null;
        IValue value = returnStmt.getValue();
        RefOperand returnRef = this.getNewRegister();
        int numberOfInstruction = this.acceptValue(value, instructions, returnRef);
        instructions.add(new PushInstruction(returnRef));
        instructions.add(new RetInstruction());
        return numberOfInstruction + 2;
    }

    protected int visitCall(Call call, ArrayList<Instruction> instructions) {
        assert call != null;
        assert instructions != null;
        RefOperand result = this.getNewRegister();
        return this.acceptCall(call, instructions, result);
    }

    public int visitStmt(IStmt stmt, ArrayList<Instruction> instructions) {
        assert stmt != null;
        assert instructions != null;
        if (stmt instanceof Assignment) {
            return this.visitAssignment((Assignment) stmt, instructions);
        } else if (stmt instanceof IfElse) {
            return this.visitIfElse((IfElse) stmt, instructions);
        } else if (stmt instanceof While) {
            return this.visitWhile((While) stmt, instructions);
        } else if (stmt instanceof Return) {
            return this.visitReturn((Return) stmt, instructions);
        } else if (stmt instanceof Call) {
            return this.visitCall((Call) stmt, instructions);
        } else {
            return 0;
        }
    }
}
