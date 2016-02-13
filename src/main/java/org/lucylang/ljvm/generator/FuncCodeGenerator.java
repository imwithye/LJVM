package org.lucylang.ljvm.generator;

import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.node.Function;
import org.lucylang.ljvm.node.VarName;

import java.util.ArrayList;

public class FuncCodeGenerator {
    private void acceptParameter(VarName parameter, ArrayList<Instruction> instructions) {
        assert parameter != null;
        assert instructions != null;
        instructions.add(new PopInstruction(new RefOperand(parameter.getVarName())));
    }

    public Routine visitFunction(Function function) {
        assert function != null;
        Routine routine = new Routine();
        for (int i = 0; i < function.getParameters().size(); i++) {
            this.acceptParameter(function.getParameters().get(i), routine.getInstructionsList());
        }
        StmtCodeGenerator gen = new StmtCodeGenerator();
        for (int i = 0; i < function.getStmts().size(); i++) {
            gen.visitStmt(function.getStmts().get(i), routine.getInstructionsList());
        }
        return routine;
    }
}
