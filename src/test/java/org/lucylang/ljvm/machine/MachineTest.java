package org.lucylang.ljvm.machine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;
import org.lucylang.ljvm.value.ValueUnavailableException;

public class MachineTest extends TestCase {
    Machine vm;

    public MachineTest(String testName) {
        super(testName);
        vm = new Machine();
    }

    public static Test suite() {
        return new TestSuite(MachineTest.class);
    }

    public void testMachineDefineRegister() {
        vm.reset();
        try {
            vm.execute(Instruction.create(InstructionType.DEFINE).first("a").second(new NumberValue(0)));
            vm.execute(Instruction.create(InstructionType.DEFINE).first("b").second("a"));
            assertTrue(vm.getValue("a").intValue() == 0);
            assertTrue(vm.getValue("b").intValue() == 0);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

        vm.reset();
        try {
            vm.execute(Instruction.create(InstructionType.DEFINE).first("a").second(new StringValue("Hello")));
            vm.execute(Instruction.create(InstructionType.DEFINE).first("b").second("a"));
            assertTrue(vm.getValue("a").stringValue().equals("Hello"));
            assertTrue(vm.getValue("b").stringValue().equals("Hello"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testMachineAdd() {
        vm.reset();
        try {
            vm.execute(Instruction.create(InstructionType.DEFINE).first("a").second(new NumberValue(1)));
            vm.execute(Instruction.create(InstructionType.DEFINE).first("b").second("a"));
            assertTrue(vm.getValue("a").intValue() == 1);
            assertTrue(vm.getValue("b").intValue() == 1);
            vm.execute(Instruction.create(InstructionType.ADD).first("a").second("a").third("b"));
            assertTrue(vm.getValue("a").intValue() == 2);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

        vm.reset();
        try {
            vm.execute(Instruction.create(InstructionType.DEFINE).first("a").second(new StringValue("Hello World")));
            vm.execute(Instruction.create(InstructionType.DEFINE).first("b").second(new StringValue("!")));
            vm.execute(Instruction.create(InstructionType.ADD).first("a").second("a").third("b"));
            assertTrue(vm.getValue("a").stringValue().equals("Hello World!"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    public void testMachineMul() {
        vm.reset();
        try {
            vm.execute(Instruction.create(InstructionType.DEFINE).first("a").second(new NumberValue(100)));
            vm.execute(Instruction.create(InstructionType.DEFINE).first("b").second(new NumberValue(20)));
            assertTrue(vm.getValue("a").intValue() == 100);
            assertTrue(vm.getValue("b").intValue() == 20);
            vm.execute(Instruction.create(InstructionType.MUL).first("a").second("a").third("b"));
            assertTrue(vm.getValue("a").intValue() == 100*20);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }

        vm.reset();
        try {
            vm.execute(Instruction.create(InstructionType.DEFINE).first("a").second(new StringValue("Hello World")));
            vm.execute(Instruction.create(InstructionType.DEFINE).first("b").second(new StringValue("!")));
            vm.execute(Instruction.create(InstructionType.MUL).first("a").second("a").third("b"));
            assertTrue(false);
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
