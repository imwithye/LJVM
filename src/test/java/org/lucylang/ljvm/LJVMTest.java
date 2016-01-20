package org.lucylang.ljvm;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public abstract class LJVMTest extends TestCase {
    public LJVMTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(LJVMTest.class);
    }
}