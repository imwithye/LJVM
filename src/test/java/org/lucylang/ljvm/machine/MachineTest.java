package org.lucylang.ljvm.machine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.value.*;

public class MachineTest extends TestCase {
    public MachineTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MachineTest.class);
    }

    public void testReset() {
        Machine m = new Machine();
        assertEquals(0, m.pc);
        assertEquals(null, m.currentScope);
        assertEquals(0, m.memoryStack.size());

        m.setProgramCounter(10);
        m.currentScope = new Scope<String, Register>();
        m.memoryStack.push(new NumberValue(0));
        m.reset();
        assertEquals(0, m.pc);
        assertEquals(null, m.currentScope);
        assertEquals(0, m.memoryStack.size());
    }

    public void testGetRegister() {
        Machine m = new Machine();
        m.currentScope = new Scope<String, Register>();

        assertTrue(m.getRegister("main") != null);
        assertTrue(m.getRegister("main") != null);
        assertTrue(m.getRegister("$1") != null);
        assertTrue(m.getRegister("$2") != null);
        assertTrue(m.currentScope.keySet().size() == 3);
    }

    public void testGetValue() {
        Machine m = new Machine();
        m.currentScope = new Scope<String, Register>();

        assertEquals(new NoneValue(), m.getValue("main"));
        m.getRegister("main").assignValue(new NumberValue(0));
        assertEquals(new NumberValue(0), m.getValue("main"));
    }

    public void testMemoryStack() {
        Machine m = new Machine();
        m.pushValue(new NumberValue(0));
        m.pushValue(new StringValue(""));
        m.pushValue(new BooleanValue(false));
        m.pushValue(new NoneValue());
        assertEquals(4, m.memoryStack.size());
        assertEquals(new NoneValue(), m.peekValue());
        m.popValue();
        assertEquals(new BooleanValue(false), m.peekValue());
        m.popValue();
        assertEquals(new StringValue(""), m.peekValue());
        m.popValue();
        assertEquals(new NumberValue(0), m.peekValue());
        m.popValue();
    }


    private int fibonacci(int n) {
        if (n < 3) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public void routineFibonacci(int n) throws Exception {
        Machine vm = new Machine();
        Module module = new Module();
        module.defineRoutine("fibonacci", new Routine(new Instruction[]{
                new MovInstruction(new RefOperand("n"), new ValueOperand(new NumberValue(0))),
                new PopInstruction(new RefOperand("n")),
                new MovInstruction(new RefOperand("cmp"), new ValueOperand(new BooleanValue(false))),
                new LesInstruction(new RefOperand("cmp"), new RefOperand("n"), new ValueOperand(new NumberValue(3))),
                new BeqInstruction(new RefOperand("cmp"), new ValueOperand(new NumberValue(19))),
                // IF n >= 3
                new MovInstruction(new RefOperand("result"), new RefOperand("n")),
                new SubInstruction(new RefOperand("result"), new RefOperand("result"), new ValueOperand(new NumberValue(1))),
                new PushInstruction(new RefOperand("result")),
                new CallInstruction(new RefOperand("fibonacci")),
                new PopInstruction(new RefOperand("result")),
                new MovInstruction(new RefOperand("sum"), new RefOperand("result")),

                new MovInstruction(new RefOperand("result"), new RefOperand("n")),
                new SubInstruction(new RefOperand("result"), new RefOperand("result"), new ValueOperand(new NumberValue(2))),
                new PushInstruction(new RefOperand("result")),
                new CallInstruction(new RefOperand("fibonacci")),
                new PopInstruction(new RefOperand("result")),
                new AddInstruction(new RefOperand("sum"), new RefOperand("sum"), new RefOperand("result")),

                //   return result
                new PushInstruction(new RefOperand("sum")),
                new RetInstruction(),
                // ELSE
                //   return n
                new PushInstruction(new ValueOperand(new NumberValue(1))),
                new RetInstruction()
        }));
        module.defineRoutine("main", new Routine(new Instruction[]{
                new PushInstruction(new ValueOperand(new NumberValue(n))),
                new CallInstruction(new RefOperand("fibonacci")),
                new RetInstruction()
        }));
        Value result = vm.execute(module);
        assertEquals(fibonacci(n), result.intValue().intValue());
        assertEquals(0, vm.memoryStack.size());
        assertEquals(null, vm.currentScope);
    }

    public void testRoutineFibonacci() throws Exception {
        routineFibonacci(0);
        routineFibonacci(1);
        routineFibonacci(2);
        routineFibonacci(3);
        routineFibonacci(10);
        routineFibonacci(20);
        routineFibonacci(27);
    }
}
