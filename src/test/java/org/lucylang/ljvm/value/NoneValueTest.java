package org.lucylang.ljvm.value;

import junit.framework.Test;
import junit.framework.TestSuite;

public class NoneValueTest extends ValueTest {
    protected NoneValue left;

    public NoneValueTest(String testName) {
        super(testName);
        left = new NoneValue();
    }

    public static Test suite() {
        return new TestSuite(BooleanValueTest.class);
    }

    public void testGetType() {
        NoneValue value = new NoneValue();
        assertEquals(new NoneValue(), value.getType());
    }

    public void testIntValue() {
        NoneValue value = new NoneValue();
        try {
            value.intValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
    }

    public void testFloatValue() {
        NoneValue value = new NoneValue();
        try {
            value.floatValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
    }

    public void testStringValue() {
        NoneValue value = new NoneValue();
        try {
            value.stringValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
    }

    public void testBooleanValue() {
        NoneValue value = new NoneValue();
        try {
            value.booleanValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
    }

    public void testAdd() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform add over " + left + " value");
        add.testThrow(left, new BooleanValue(), ve);
        add.testThrow(left, new NumberValue(), ve);
        add.testThrow(left, new StringValue(), ve);
        add.testThrow(left, new NoneValue(), ve);
    }

    public void testSub() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform sub over " + left + " value");
        sub.testThrow(left, new BooleanValue(), ve);
        sub.testThrow(left, new NumberValue(), ve);
        sub.testThrow(left, new StringValue(), ve);
        sub.testThrow(left, new NoneValue(), ve);
    }

    public void testMul() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform mul over " + left + " value");
        mul.testThrow(left, new BooleanValue(), ve);
        mul.testThrow(left, new NumberValue(), ve);
        mul.testThrow(left, new StringValue(), ve);
        mul.testThrow(left, new NoneValue(), ve);
    }

    public void testDiv() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform div over " + left + " value");
        div.testThrow(left, new BooleanValue(), ve);
        div.testThrow(left, new NumberValue(), ve);
        div.testThrow(left, new StringValue(), ve);
        div.testThrow(left, new NoneValue(), ve);
    }

    public void testAnd() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform and over " + left + " value");
        and.testThrow(left, new BooleanValue(), ve);
        and.testThrow(left, new NumberValue(), ve);
        and.testThrow(left, new StringValue(), ve);
        and.testThrow(left, new NoneValue(), ve);
    }

    public void testOr() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform or over " + left + " value");
        or.testThrow(left, new BooleanValue(), ve);
        or.testThrow(left, new NumberValue(), ve);
        or.testThrow(left, new StringValue(), ve);
        or.testThrow(left, new NoneValue(), ve);
    }

    public void testNot() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform not over " + left + " value");
        not.testThrow(left, null, ve);
    }

    public void testEqu() {
        equ.testEqual(left, new BooleanValue(false), new BooleanValue(false));
        equ.testEqual(left, new BooleanValue(true), new BooleanValue(false));
        equ.testEqual(left, new NumberValue(), new BooleanValue(false));
        equ.testEqual(left, new StringValue(), new BooleanValue(false));
        equ.testEqual(left, new NoneValue(), new BooleanValue(true));
    }

    public void testLes() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform les over " + left + " value");
        les.testThrow(left, new BooleanValue(), ve);
        les.testThrow(left, new NumberValue(), ve);
        les.testThrow(left, new StringValue(), ve);
        les.testThrow(left, new NoneValue(), ve);
    }

    public void testGre() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform gre over " + left + " value");
        gre.testThrow(left, new BooleanValue(), ve);
        gre.testThrow(left, new NumberValue(), ve);
        gre.testThrow(left, new StringValue(), ve);
        gre.testThrow(left, new NoneValue(), ve);
    }

    public void testLeq() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform leq over " + left + " value");
        leq.testThrow(left, new BooleanValue(), ve);
        leq.testThrow(left, new NumberValue(), ve);
        leq.testThrow(left, new StringValue(), ve);
        leq.testThrow(left, new NoneValue(), ve);
    }

    public void testGeq() {
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform geq over " + left + " value");
        geq.testThrow(left, new BooleanValue(), ve);
        geq.testThrow(left, new NumberValue(), ve);
        geq.testThrow(left, new StringValue(), ve);
        geq.testThrow(left, new NoneValue(), ve);
    }
}
