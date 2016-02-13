package org.lucylang.ljvm.type;

import junit.framework.TestCase;

public abstract class TypeTest<T extends Type> extends TestCase {
    public TypeTest(String testName) {
        super(testName);
    }

    protected abstract T newType();

    public void testEqual() {
        T type = this.newType();
        assertEquals(this.newType(), type);
    }
}
