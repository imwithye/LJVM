package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.LJVMTest;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;

public class MovInstructionTest extends LJVMTest {
    public MovInstructionTest(String testName) {
        super(testName);
    }

    public void testValid() {
        MovInstruction mov;
        mov = new MovInstruction(new RefOperand("value"), new RefOperand("value"));
        assertTrue(mov.isValid());
        mov = new MovInstruction(new RefOperand("value"), new ValueOperand(new NumberValue(10)));
        assertTrue(mov.isValid());
        mov = new MovInstruction(new RefOperand("value"), new ValueOperand(new StringValue("testThrow")));
        assertTrue(mov.isValid());
    }
}
