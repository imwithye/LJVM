package org.lucylang.ljvm.value;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.lucylang.ljvm.type.*;

public class NumberValueTest extends ValueTest {
    protected NumberValue left;

    public NumberValueTest(String testName) {
        super(testName);
        left = new NumberValue(0);
    }

    public static Test suite() {
        return new TestSuite(NumberValueTest.class);
    }

    public void testGetType() {
        NumberValue value = new NumberValue();
        assertEquals(new NumberType(), value.getType());
    }

    public void testIntValue() {
        NumberValue value = new NumberValue(0);
        assertEquals(0, value.intValue().intValue());
        value = new NumberValue(10);
        assertEquals(10, value.intValue().intValue());
        value = new NumberValue(-10);
        assertEquals(-10, value.intValue().intValue());
        value = new NumberValue(1000);
        assertEquals(1000, value.intValue().intValue());
    }

    public void testFloatValue() {
        NumberValue value = new NumberValue(0);
        assertEquals(new Double(0), value.floatValue());
        value = new NumberValue(10);
        assertEquals(new Double(10), value.floatValue());
        value = new NumberValue(-10);
        assertEquals(new Double(-10), value.floatValue());
        value = new NumberValue(3.1415926);
        assertEquals(new Double(3.1415926), value.floatValue());
    }

    public void testStringValue() {
        NumberValue value = new NumberValue(0);
        assertEquals("0", value.stringValue());
        value = new NumberValue(10);
        assertEquals("10", value.stringValue());
        value = new NumberValue(-10);
        assertEquals("-10", value.stringValue());
        value = new NumberValue(3.1415926);
        assertEquals("3.1415926", value.stringValue());
    }

    public void testBooleanValue() {
        NumberValue value = new NumberValue();
        try {
            value.booleanValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
    }

    public void testAdd() {
        add.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        add.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        add.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        add.testEqual(new NumberValue(10), new NumberValue(11), new NumberValue(21));
        add.testEqual(new NumberValue(10.25), new NumberValue(11), new NumberValue(21.25));
        add.testEqual(new NumberValue(-10), new NumberValue(11), new NumberValue(1));
        add.testEqual(new NumberValue(15.7), new NumberValue(14.3), new NumberValue(30));
        add.testEqual(new NumberValue(66), new NumberValue(-66), new NumberValue(0));
        add.testEqual(new NumberValue(100), new NumberValue(-101), new NumberValue(-1));
    }

    public void testSub() {
        sub.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        sub.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        sub.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        sub.testEqual(new NumberValue(21), new NumberValue(10), new NumberValue(11));
        sub.testEqual(new NumberValue(21.25), new NumberValue(10.25), new NumberValue(11));
        sub.testEqual(new NumberValue(1), new NumberValue(-10), new NumberValue(11));
        sub.testEqual(new NumberValue(30), new NumberValue(15.7), new NumberValue(14.3));
        sub.testEqual(new NumberValue(0), new NumberValue(66), new NumberValue(-66));
        sub.testEqual(new NumberValue(-1), new NumberValue(100), new NumberValue(-101));
    }

    private void mul(double left, double right) {
        mul.testEqual(new NumberValue(left), new NumberValue(right), new NumberValue(left * right));
    }

    public void testMul() {
        mul.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        mul.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        mul.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        mul(100, 10);
        mul(-10, 98);
        mul(123, 3.123);
        mul(-10, -10);
        mul(0, 10);
    }

    private void div(double left, double right) {
        div.testEqual(new NumberValue(left), new NumberValue(right), new NumberValue(left / right));
    }

    public void testDiv() {
        div.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        div.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        div.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        div(100, 10);
        div(100, 0);
        div(10, 10);
        div(0, 100);
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
        equ.testEqual(left, new BooleanValue(), new BooleanValue(false));
        equ.testEqual(left, new StringValue(), new BooleanValue(false));
        equ.testEqual(left, new NoneValue(), new BooleanValue(false));

        equ.testEqual(left, new NumberValue(), new BooleanValue(true));
        equ.testEqual(left, new NumberValue(10), new BooleanValue(false));
        equ.testEqual(left, new NumberValue(11), new BooleanValue(false));
        equ.testEqual(left, new NumberValue(-10), new BooleanValue(false));
    }

    private void les(double left, double right) {
        les.testEqual(new NumberValue(left), new NumberValue(right), new BooleanValue(left < right));
    }

    public void testLes() {
        les.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        les.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        les.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        les(10, 100);
        les(10, 10);
        les(0, -10);
        les(0, 0);
    }

    private void gre(double left, double right) {
        gre.testEqual(new NumberValue(left), new NumberValue(right), new BooleanValue(left > right));
    }

    public void testGre() {
        gre.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        gre.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        gre.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        gre(10, 100);
        gre(10, 10);
        gre(0, -10);
        gre(0, 0);
    }

    private void leq(double left, double right) {
        leq.testEqual(new NumberValue(left), new NumberValue(right), new BooleanValue(left <= right));
    }

    public void testLeq() {
        leq.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        leq.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        leq.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        leq(10, 100);
        leq(10, 10);
        leq(0, -10);
        leq(0, 0);
    }

    private void geq(double left, double right) {
        geq.testEqual(new NumberValue(left), new NumberValue(right), new BooleanValue(left >= right));
    }

    public void testGeq() {
        geq.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new NumberType(), new BooleanType()));
        geq.testThrow(left, new StringValue(), new TypeUnmatchedException(new NumberType(), new StringType()));
        geq.testThrow(left, new NoneValue(), new TypeUnmatchedException(new NumberType(), new NoneType()));

        geq(10, 100);
        geq(10, 10);
        geq(0, -10);
        geq(0, 0);
    }
}
