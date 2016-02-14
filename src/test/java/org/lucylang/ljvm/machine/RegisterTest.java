package org.lucylang.ljvm.machine;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.lucylang.ljvm.type.NumberType;
import org.lucylang.ljvm.type.StringType;
import org.lucylang.ljvm.value.NumberValue;
import org.lucylang.ljvm.value.StringValue;

public class RegisterTest extends TestCase {
    public RegisterTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(RegisterTest.class);
    }

    public void testGetValue() {
        Register r = new Register(new NumberValue(0));
        assertEquals(new NumberValue(0), r.getValue());
        r.assignValue(new StringValue("hello"));
        assertEquals(new StringValue("hello"), r.getValue());
    }

    public void testGetType() {
        Register r = new Register(new NumberValue(0));
        assertEquals(new NumberType(), r.getType());
        r.assignValue(new StringValue("hello"));
        assertEquals(new StringType(), r.getType());
    }
}
