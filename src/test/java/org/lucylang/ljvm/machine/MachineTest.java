package org.lucylang.ljvm.machine;

import org.lucylang.ljvm.LJVMTest;
import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.type.StringType;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;

public class MachineTest extends LJVMTest {
    public MachineTest(String testName) {
        super(testName);
    }

    public void testMachine() throws Exception {
        Machine vm = new Machine();
        vm.execute(new Instruction[]{
                new DefInstruction(new RefOperand("a"), new ValueOperand(new NumberValue(10))),
                new DefInstruction(new RefOperand("b"), new ValueOperand(new NumberValue(11))),
                new AddInstruction(new RefOperand("a"), new RefOperand("a"), new RefOperand("b")),
        });
        assertEquals(vm.getValue("a").intValue().intValue(), 21);

        vm.reset();
        vm.execute(new Instruction[]{
                new DefInstruction(new RefOperand("a"), new ValueOperand(new NumberValue(10))),
                new DefInstruction(new RefOperand("b"), new ValueOperand(new NumberValue(11.9))),
                new AddInstruction(new RefOperand("a"), new RefOperand("a"), new RefOperand("b")),
        });
        assertEquals(vm.getValue("a").floatValue().doubleValue(), 21.9);
        vm.execute(new Instruction[]{
                new StrInstruction(new RefOperand("a"), new RefOperand("a"))
        });
        assertEquals(vm.getValue("a").getType(), new StringType());

        vm.reset();
        vm.execute(new Instruction[]{
                new DefInstruction(new RefOperand("a"), new ValueOperand(new StringValue("Hello"))),
                new DefInstruction(new RefOperand("b"), new ValueOperand(new StringValue("World"))),
                new AddInstruction(new RefOperand("a"), new RefOperand("a"), new RefOperand("b")),
        });
        assertEquals(vm.getValue("a").stringValue(), "HelloWorld");

        vm.reset();
        vm.execute(new Instruction[]{
                new DefInstruction(new RefOperand("a"), new ValueOperand(new StringValue("Hello"))),
                new DefInstruction(new RefOperand("b"), new ValueOperand(new StringValue("World"))),
                new AddInstruction(new RefOperand("a"), new RefOperand("a"), new RefOperand("b")),
                new PutInstruction(new RefOperand("a"))
        });
    }
}
