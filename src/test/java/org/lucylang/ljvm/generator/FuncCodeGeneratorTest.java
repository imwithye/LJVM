package org.lucylang.ljvm.generator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.node.*;

public class FuncCodeGeneratorTest extends TestCase {
    public FuncCodeGeneratorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(FuncCodeGeneratorTest.class);
    }

    public void testFunc() {
        FuncCodeGenerator funcCodeGenerator = new FuncCodeGenerator();
        Function function = new Function("main");
        function.addParameter(new VarName("argc"));
        function.addParameter(new VarName("argv"));
        function.addStmt(new Assignment(new VarName("result"), new AddExpr(new NumberLiteral(0), new NumberLiteral(10))));
        function.addStmt((new Call(new VarName("sum"))).addParameter(new NumberLiteral(10)).addParameter(new NumberLiteral(20)));
        function.addStmt(new Return(new NumberLiteral(0)));
        Routine routine = funcCodeGenerator.visitFunction(function);
        for (int i = 0; i < routine.getInstructionsList().size(); i++) {
            System.out.printf("%4d ", i);
            System.out.println(routine.getInstructionsList().get(i));
        }
    }
}
