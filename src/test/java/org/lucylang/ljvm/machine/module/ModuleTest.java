package org.lucylang.ljvm.machine.module;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

public class ModuleTest extends TestCase {
    public ModuleTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ModuleTest.class);
    }

    public void testIsMain() throws Exception {
        Module m1 = new Module("module");
        m1.defineRoutine("main", new Routine());
        assertTrue(m1.isMain());
        Module m2 = new Module("module");
        m2.defineRoutine("sum", new Routine());
        assertTrue(!m2.isMain());
    }

    private void defineRoutine(Module m, String name, Routine r, boolean willThrow) {
        try {
            m.defineRoutine(name, r);
            if (willThrow) {
                fail();
            }
        } catch (OverdefinedException oe) {
            if (!willThrow) {
                fail();
            }
        }
    }

    public void testDefineRoutine() {
        Module m = new Module("module");
        defineRoutine(m, "main", new Routine(), false);
        defineRoutine(m, "main", new Routine(), true);
        defineRoutine(m, "sum", new Routine(), false);
        defineRoutine(m, "sum", new Routine(), true);
    }

    private void getRoutine(Module m, String name, boolean willThrow) {
        try {
            m.getRoutine(name);
            if (willThrow) {
                fail();
            }
        } catch (UndefinedException ue) {
            if (!willThrow)
                fail();
        }
    }

    public void testGetRoutine() {
        Module m = new Module("module");
        defineRoutine(m, "main", new Routine(), false);
        defineRoutine(m, "main", new Routine(), true);
        defineRoutine(m, "sum", new Routine(), false);
        defineRoutine(m, "sum", new Routine(), true);
        getRoutine(m, "main", false);
        getRoutine(m, "sum", false);
        getRoutine(m, "sub", true);
    }
}
