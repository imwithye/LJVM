package org.lucylang.ljvm.machine.instruction;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.Value;

public class AddInstructionTest extends InstructionTest {
    public AddInstructionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AddInstructionTest.class);
    }

    public void testGetType() {
        Instruction instruction = new AddInstruction(new RefOperand("test"), new ValueOperand(new NumberValue(0)), new ValueOperand(new NumberValue(0)));
        assertEquals(Type.ADD, instruction.getType());
    }

    public void testExecute() throws Exception {
        Value v;
        v = execMain(new Instruction[]{
                new AddInstruction(new RefOperand("test"), new ValueOperand(10), new ValueOperand(10)),
                new PushInstruction(new RefOperand("test")),
                new RetInstruction()
        });
        assertEquals(new NumberValue(20), v);

        v = execMain(new Instruction[]{
                new AddInstruction(new RefOperand("test"), new ValueOperand("Hello"), new ValueOperand("World")),
                new PushInstruction(new RefOperand("test")),
                new RetInstruction()
        });
        assertEquals(new StringValue("HelloWorld"), v);
    }
}
