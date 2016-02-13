package org.lucylang.ljvm.value;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.lucylang.ljvm.type.*;

public class StringValueTest extends ValueTest {
    protected StringValue left;

    public StringValueTest(String testName) {
        super(testName);
        left = new StringValue("Hello World");
    }

    public static Test suite() {
        return new TestSuite(StringValueTest.class);
    }

    public void testGetType() {
        StringValue value = new StringValue();
        assertEquals(new StringType(), value.getType());
    }

    public void testIntValue() {
        try {
            StringValue value = new StringValue();
            value.intValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
        try {
            StringValue value = new StringValue("hello");
            value.intValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
        try {
            StringValue value = new StringValue("3.14");
            value.intValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
        try {
            StringValue value = new StringValue("0");
            assertEquals(new Integer(0), value.intValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
        try {
            StringValue value = new StringValue("10");
            assertEquals(new Integer(10), value.intValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
        try {
            StringValue value = new StringValue("-100");
            assertEquals(new Integer(-100), value.intValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
    }

    public void testFloatValue() {
        try {
            StringValue value = new StringValue();
            value.floatValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
        try {
            StringValue value = new StringValue("hello");
            value.floatValue();
            fail();
        } catch (ValueUnavailableException e) {
            assertTrue(true);
        }
        try {
            StringValue value = new StringValue("3.14");
            assertEquals(new Double(3.14), value.floatValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
        try {
            StringValue value = new StringValue("0");
            assertEquals(new Double(0), value.floatValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
        try {
            StringValue value = new StringValue("10");
            assertEquals(new Double(10), value.floatValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
        try {
            StringValue value = new StringValue("-100");
            assertEquals(new Double(-100), value.floatValue());
        } catch (ValueUnavailableException e) {
            fail();
        }
    }

    public void testStringValue() {
        StringValue value = new StringValue();
        assertEquals("", value.stringValue());

        value = new StringValue("hi");
        assertEquals("hi", value.stringValue());

        value = new StringValue("lucy");
        assertEquals("lucy", value.stringValue());
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

    private void add(String left, String right) {
        add.testEqual(new StringValue(left), new StringValue(right), new StringValue(left + right));
    }

    public void testAdd() {
        add.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new StringType(), new BooleanType()));
        add.testThrow(left, new NumberValue(), new TypeUnmatchedException(new StringType(), new NumberType()));
        add.testThrow(left, new NoneValue(), new TypeUnmatchedException(new StringType(), new NoneType()));

        add("Hello", "World");
        add("", "World");
        add("Hello", "");
        add("Hello 1", "World");
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
        equ.testEqual(left, new BooleanValue(), new BooleanValue(false));
        equ.testEqual(left, new NumberValue(), new BooleanValue(false));
        equ.testEqual(left, new NoneValue(), new BooleanValue(false));

        equ.testEqual(left, new StringValue(), new BooleanValue(false));
        equ.testEqual(left, new StringValue("Hello"), new BooleanValue(false));
        equ.testEqual(left, new StringValue("Hello World"), new BooleanValue(true));
    }

    private void les(String left, String right) {
        les.testEqual(new StringValue(left), new StringValue(right), new BooleanValue(left.compareTo(right) < 0));
    }

    public void testLes() {
        les.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new StringType(), new BooleanType()));
        les.testThrow(left, new NumberValue(), new TypeUnmatchedException(new StringType(), new NumberType()));
        les.testThrow(left, new NoneValue(), new TypeUnmatchedException(new StringType(), new NoneType()));

        les("Hello", "world");
        les("Hi", "hello");
        les("", "world");
    }

    private void gre(String left, String right) {
        gre.testEqual(new StringValue(left), new StringValue(right), new BooleanValue(left.compareTo(right) > 0));
    }

    public void testGre() {
        gre.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new StringType(), new BooleanType()));
        gre.testThrow(left, new NumberValue(), new TypeUnmatchedException(new StringType(), new NumberType()));
        gre.testThrow(left, new NoneValue(), new TypeUnmatchedException(new StringType(), new NoneType()));

        gre("Hello", "world");
        gre("Hi", "hello");
        gre("", "world");
    }

    private void leq(String left, String right) {
        leq.testEqual(new StringValue(left), new StringValue(right), new BooleanValue(left.compareTo(right) <= 0));
    }

    public void testLeq() {
        leq.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new StringType(), new BooleanType()));
        leq.testThrow(left, new NumberValue(), new TypeUnmatchedException(new StringType(), new NumberType()));
        leq.testThrow(left, new NoneValue(), new TypeUnmatchedException(new StringType(), new NoneType()));

        leq("Hello", "world");
        leq("Hi", "hello");
        leq("", "world");
        leq("hello", "hello");
    }

    private void geq(String left, String right) {
        geq.testEqual(new StringValue(left), new StringValue(right), new BooleanValue(left.compareTo(right) >= 0));
    }

    public void testGeq() {
        geq.testThrow(left, new BooleanValue(), new TypeUnmatchedException(new StringType(), new BooleanType()));
        geq.testThrow(left, new NumberValue(), new TypeUnmatchedException(new StringType(), new NumberType()));
        geq.testThrow(left, new NoneValue(), new TypeUnmatchedException(new StringType(), new NoneType()));

        geq("Hello", "world");
        geq("Hi", "hello");
        geq("", "world");
        geq("hello", "hello");
    }
}
