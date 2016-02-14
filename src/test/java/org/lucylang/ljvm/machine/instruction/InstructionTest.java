package org.lucylang.ljvm.machine.instruction;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.machine.Machine;
import org.lucylang.ljvm.machine.module.Module;
import org.lucylang.ljvm.machine.module.Routine;
import org.lucylang.ljvm.value.Value;

public abstract class InstructionTest extends TestCase {
    public InstructionTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(InstructionTest.class);
    }

    protected Value execMain(Instruction[] instructions) throws Exception {
        Module module = new Module();
        Routine main = new Routine(instructions);
        module.defineRoutine("main", main);
        Machine vm = new Machine();
        return vm.execute(module);
    }
}
