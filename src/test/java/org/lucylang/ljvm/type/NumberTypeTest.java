package org.lucylang.ljvm.type;

import junit.framework.Test;
import junit.framework.TestSuite;

public class NumberTypeTest extends TypeTest<NumberType> {
    public NumberTypeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(NumberTypeTest.class);
    }

    protected NumberType newType() {
        return new NumberType();
    }
}
