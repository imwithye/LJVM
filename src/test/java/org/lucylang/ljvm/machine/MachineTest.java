package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.LJVMTest;
import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.value.BooleanValue;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;

public class MachineTest extends LJVMTest {
    public MachineTest(String testName) {
        super(testName);
    }

    public void defineMain(Machine vm, Instruction[] instructions) throws Exception {
        Module module = new Module();
        module.defineRoutine("main", new Routine(instructions));
        vm.execute(module);
    }

    public void testMachine() throws Exception {
    }

    public void testRoutine() throws Exception {
    }

    private int fibonacci(int n) {
        if(n < 3) {
            return 1;
        } else {
            return fibonacci(n-1) + fibonacci(n-2);
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
                new PopInstruction(new RefOperand("result")),
                new RetInstruction()
        }));
        vm.execute(module);
        assertEquals(fibonacci(n), vm.getValue("result").intValue().intValue());
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
