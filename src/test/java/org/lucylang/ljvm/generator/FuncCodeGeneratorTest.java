package org.lucylang.ljvm.generator;

import org.lucylang.ljvm.LJVMTest;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.node.*;

public class FuncCodeGeneratorTest extends LJVMTest {
    public FuncCodeGeneratorTest(String testName) {
        super(testName);
    }

    public void testFunc() {
        FuncCodeGenerator funcCodeGenerator = new FuncCodeGenerator();
        Function function = new Function("main");
        function.addParameter(new VarName("argc"));
        function.addParameter(new VarName("argv"));
        function.addStmt(new Assignment(new VarName("result"), new AddExpr(new NumberLiteral(0), new NumberLiteral(10))));
        function.addStmt(new Return(new NumberLiteral(0)));
        Routine routine = funcCodeGenerator.visitFunction(function);
        for(int i=0; i<routine.getInstructionsList().size(); i++) {
            System.out.printf("%4d ", i);
            System.out.println(routine.getInstructionsList().get(i));
        }
    }
}
