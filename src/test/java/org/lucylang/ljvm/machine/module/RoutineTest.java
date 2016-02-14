package org.lucylang.ljvm.machine.module;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.machine.instruction.MovInstruction;
import org.lucylang.ljvm.machine.instruction.RefOperand;
import org.lucylang.ljvm.machine.instruction.ValueOperand;
import org.lucylang.ljvm.value.NumberValue;

import java.util.ArrayList;

public class RoutineTest extends TestCase {
    public RoutineTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(RoutineTest.class);
    }

    public void testConstructor() {
        Routine r = new Routine();
        assertEquals(0, r.getInstructionsList().size());
        r = new Routine((ArrayList<Instruction>) null);
        assertEquals(0, r.getInstructionsList().size());
        r = new Routine(new Instruction[0]);
        assertEquals(0, r.getInstructionsList().size());
    }

    public void testAddInstruction() {
        Routine r = new Routine();
        r.addInstruction(new MovInstruction(new RefOperand("test"), new ValueOperand(new NumberValue(0))));
        assertEquals(1, r.getInstructionsList().size());
    }
}
