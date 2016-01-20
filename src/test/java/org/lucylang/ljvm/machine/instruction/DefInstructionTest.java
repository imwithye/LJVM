package org.lucylang.ljvm.machine.instruction;

import org.lucylang.ljvm.LJVMTest;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;

public class DefInstructionTest extends LJVMTest {
    public DefInstructionTest(String testName) {
        super(testName);
    }

    public void testValid() {
        DefInstruction def;
        def = new DefInstruction(new RefOperand("value"), new RefOperand("value"));
        assertTrue(def.isValid());
        def = new DefInstruction(new RefOperand("value"), new ValueOperand(new StringValue("test")));
        assertTrue(def.isValid());
        def = new DefInstruction(new RefOperand("value"), new ValueOperand(new NumberValue(10)));
        assertTrue(def.isValid());
    }
}
