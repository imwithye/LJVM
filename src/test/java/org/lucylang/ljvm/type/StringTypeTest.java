package org.lucylang.ljvm.type;

import junit.framework.Test;
import junit.framework.TestSuite;

public class StringTypeTest extends TypeTest<StringType> {
    public StringTypeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(StringTypeTest.class);
    }

    @Override
    protected StringType newType() {
        return null;
    }
}
