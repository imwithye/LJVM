package org.lucylang.ljvm.value;

import junit.framework.TestCase;
import org.lucylang.ljvm.type.TypeUnmatchedException;

public class ValueTest extends TestCase {
    protected MethodRunner add;
    protected MethodRunner sub;
    protected MethodRunner mul;
    protected MethodRunner div;
    protected MethodRunner and;
    protected MethodRunner or;
    protected MethodRunner not;
    protected MethodRunner equ;
    protected MethodRunner les;
    protected MethodRunner gre;
    protected MethodRunner leq;
    protected MethodRunner geq;

    public ValueTest(String testName) {
        super(testName);
        add = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.add(right);
            }
        };
        sub = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.sub(right);
            }
        };
        mul = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.mul(right);
            }
        };
        div = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.div(right);
            }
        };
        and = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.and(right);
            }
        };
        or = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.or(right);
            }
        };
        not = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.not();
            }
        };
        equ = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.equ(right);
            }
        };
        les = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.les(right);
            }
        };
        gre = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.gre(right);
            }
        };
        leq = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.leq(right);
            }
        };
        geq = new MethodRunner() {
            @Override
            protected Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException {
                return left.geq(right);
            }
        };
    }

    protected abstract class MethodRunner {
        protected abstract Value run(Value left, Value right) throws ValueUnavailableException, TypeUnmatchedException;

        public void testThrow(Value left, Value right, Exception e) {
            try {
                run(left, right);
                if (e != null) {
                    fail();
                }
            } catch (ValueUnavailableException v) {
                assertEquals(e, v);
            } catch (TypeUnmatchedException t) {
                assertEquals(e, t);
            }
        }

        public void testEqual(Value left, Value right, Value expect) {
            try {
                Value result = run(left, right);
                assertTrue(expect.equ(result).booleanValue());
            } catch (Exception e) {
                fail();
            }
        }
    }
}
