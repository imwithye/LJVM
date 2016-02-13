package org.lucylang.ljvm.value;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.lucylang.ljvm.type.*;

public class BooleanValueTest extends ValueTest {
    public BooleanValueTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BooleanValueTest.class);
    }

    public void testGetType() {
        BooleanValue value = new BooleanValue();
        assertEquals(new BooleanType(), value.getType());

        value = new BooleanValue(true);
        assertEquals(new BooleanType(), value.getType());

        value = new BooleanValue(false);
        assertEquals(new BooleanType(), value.getType());
    }

    public void testIntValue() {
        BooleanValue value = new BooleanValue();
        assertEquals(new Integer(0), value.intValue());

        value = new BooleanValue(false);
        assertEquals(new Integer(0), value.intValue());

        value = new BooleanValue(true);
        assertEquals(new Integer(1), value.intValue());
    }

    public void testFloatValue() {
        BooleanValue value = new BooleanValue();
        assertEquals(new Double(0), value.floatValue());

        value = new BooleanValue(false);
        assertEquals(new Double(0), value.floatValue());

        value = new BooleanValue(true);
        assertEquals(new Double(1), value.floatValue());
    }

    public void testStringValue() {
        BooleanValue value = new BooleanValue();
        assertEquals("false", value.stringValue());

        value = new BooleanValue(false);
        assertEquals("false", value.stringValue());

        value = new BooleanValue(true);
        assertEquals("true", value.stringValue());
    }

    public void testBooleanValue() {
        BooleanValue value = new BooleanValue();
        assertEquals(new Boolean(false), value.booleanValue());

        value = new BooleanValue(false);
        assertEquals(new Boolean(false), value.booleanValue());

        value = new BooleanValue(true);
        assertEquals(new Boolean(true), value.booleanValue());
    }

    public void testAdd() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform add over " + left + " value");
        add.testThrow(left, new BooleanValue(), ve);
        add.testThrow(left, new NumberValue(), ve);
        add.testThrow(left, new StringValue(), ve);
        add.testThrow(left, new NoneValue(), ve);
    }

    public void testSub() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform sub over " + left + " value");
        sub.testThrow(left, new BooleanValue(), ve);
        sub.testThrow(left, new NumberValue(), ve);
        sub.testThrow(left, new StringValue(), ve);
        sub.testThrow(left, new NoneValue(), ve);
    }

    public void testMul() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform mul over " + left + " value");
        mul.testThrow(left, new BooleanValue(), ve);
        mul.testThrow(left, new NumberValue(), ve);
        mul.testThrow(left, new StringValue(), ve);
        mul.testThrow(left, new NoneValue(), ve);
    }

    public void testDiv() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform div over " + left + " value");
        div.testThrow(left, new BooleanValue(), ve);
        div.testThrow(left, new NumberValue(), ve);
        div.testThrow(left, new StringValue(), ve);
        div.testThrow(left, new NoneValue(), ve);
    }

    public void testAnd() {
        BooleanValue left = new BooleanValue();
        and.testThrow(left, new NumberValue(), new TypeUnmatchedException(left.getType(), new NumberType()));
        and.testThrow(left, new StringValue(), new TypeUnmatchedException(left.getType(), new StringType()));
        and.testThrow(left, new NoneValue(), new TypeUnmatchedException(left.getType(), new NoneType()));

        and.testEqual(new BooleanValue(false), new BooleanValue(false), new BooleanValue(false));
        and.testEqual(new BooleanValue(false), new BooleanValue(true), new BooleanValue(false));
        and.testEqual(new BooleanValue(true), new BooleanValue(false), new BooleanValue(false));
        and.testEqual(new BooleanValue(true), new BooleanValue(true), new BooleanValue(true));
    }

    public void testOr() {
        BooleanValue left = new BooleanValue();
        or.testThrow(left, new NumberValue(), new TypeUnmatchedException(left.getType(), new NumberType()));
        or.testThrow(left, new StringValue(), new TypeUnmatchedException(left.getType(), new StringType()));
        or.testThrow(left, new NoneValue(), new TypeUnmatchedException(left.getType(), new NoneType()));

        or.testEqual(new BooleanValue(false), new BooleanValue(false), new BooleanValue(false));
        or.testEqual(new BooleanValue(false), new BooleanValue(true), new BooleanValue(true));
        or.testEqual(new BooleanValue(true), new BooleanValue(false), new BooleanValue(true));
        or.testEqual(new BooleanValue(true), new BooleanValue(true), new BooleanValue(true));
    }

    public void testNot() {
        BooleanValue left = new BooleanValue();
        not.testThrow(left, null, null);
        not.testEqual(new BooleanValue(true), null, new BooleanValue(false));
        not.testEqual(new BooleanValue(false), null, new BooleanValue(true));
    }

    public void testEqu() {
        BooleanValue left = new BooleanValue();
        equ.testEqual(left, new BooleanValue(false), new BooleanValue(true));
        equ.testEqual(left, new BooleanValue(true), new BooleanValue(false));
        equ.testEqual(left, new NumberValue(), new BooleanValue(false));
        equ.testEqual(left, new StringValue(), new BooleanValue(false));
        equ.testEqual(left, new NoneValue(), new BooleanValue(false));
    }

    public void testLes() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform < over " + left + " value");
        les.testThrow(left, new BooleanValue(), ve);
        les.testThrow(left, new NumberValue(), ve);
        les.testThrow(left, new StringValue(), ve);
        les.testThrow(left, new NoneValue(), ve);
    }

    public void testGre() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform > over " + left + " value");
        gre.testThrow(left, new BooleanValue(), ve);
        gre.testThrow(left, new NumberValue(), ve);
        gre.testThrow(left, new StringValue(), ve);
        gre.testThrow(left, new NoneValue(), ve);
    }

    public void testLeq() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform <= over " + left + " value");
        leq.testThrow(left, new BooleanValue(), ve);
        leq.testThrow(left, new NumberValue(), ve);
        leq.testThrow(left, new StringValue(), ve);
        leq.testThrow(left, new NoneValue(), ve);
    }

    public void testGeq() {
        BooleanValue left = new BooleanValue();
        ValueUnavailableException ve = new ValueUnavailableException("unable to perform >= over " + left + " value");
        geq.testThrow(left, new BooleanValue(), ve);
        geq.testThrow(left, new NumberValue(), ve);
        geq.testThrow(left, new StringValue(), ve);
        geq.testThrow(left, new NoneValue(), ve);
    }
}
