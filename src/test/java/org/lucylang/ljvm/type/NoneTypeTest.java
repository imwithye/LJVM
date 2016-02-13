package org.lucylang.ljvm.type;

import junit.framework.Test;
import junit.framework.TestSuite;

public class NoneTypeTest extends TypeTest<NoneType> {
    public NoneTypeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(NoneTypeTest.class);
    }

    @Override
    protected NoneType newType() {
        return new NoneType();
    }
}
