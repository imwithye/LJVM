package org.lucylang.ljvm.type;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BooleanTypeTest extends TypeTest<BooleanType> {
    public BooleanTypeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BooleanTypeTest.class);
    }

    @Override
    protected BooleanType newType() {
        return new BooleanType();
    }
}
