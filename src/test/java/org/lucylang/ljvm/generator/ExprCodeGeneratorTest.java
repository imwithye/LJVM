package org.lucylang.ljvm.generator;

import org.lucylang.ljvm.LJVMTest;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.node.AddExpr;
import org.lucylang.ljvm.node.NumberLiteral;
import org.lucylang.ljvm.node.VarName;

import java.util.ArrayList;

public class ExprCodeGeneratorTest extends LJVMTest {
    public ExprCodeGeneratorTest(String testName) {
        super(testName);
    }

    public void testExpr() {
        NumberLiteral n1 = new NumberLiteral(10);
        NumberLiteral n2 = new NumberLiteral(20);
        NumberLiteral n3 = new NumberLiteral(30);
        AddExpr expr = new AddExpr(new AddExpr(n1, n2), new AddExpr(n1, n3));
        ExprCodeGenerator gen = new ExprCodeGenerator();
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        gen.visitBinaryExpr(expr, instructions);
        for(int i=0; i<instructions.size(); i++) {
            System.out.println(instructions.get(i));
        }
    }
}
