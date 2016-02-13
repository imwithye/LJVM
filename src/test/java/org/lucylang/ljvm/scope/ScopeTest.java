package org.lucylang.ljvm.scope;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ScopeTest extends TestCase {
    public ScopeTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ScopeTest.class);
    }

    public void testSetParent() {
        Scope<String, String> scope = new Scope<String, String>();
        Scope<String, String> parent = new Scope<String, String>();
        scope.setParent(parent);
        assertEquals(parent, scope.getParent());
        scope = new Scope<String, String>(parent);
        assertEquals(parent, scope.getParent());
    }

    public void testSet() {
        Scope<String, String> scope = new Scope<String, String>();
        scope.set("1", "test1");
        assertEquals("test1", scope.get("1"));
        scope.set("2", "test2");
        assertEquals("test2", scope.get("2"));
        scope.set("3", "test3");
        assertEquals("test3", scope.get("3"));
        scope.set("4", "test4");
        assertEquals("test4", scope.get("4"));
        scope.set("1", "test5");
        assertEquals("test5", scope.get("1"));
    }

    protected void safeSet(Scope<String, String> scope, String key, String value, boolean willThrow) {
        try {
            scope.safeSet(key, value);
            if (willThrow) {
                fail();
            }
        } catch (OverdefinedException oe) {
            if (!willThrow) {
                fail();
            }
        }
    }

    public void testSafeSet() {
        Scope<String, String> scope = new Scope<String, String>();
        safeSet(scope, "1", "test", false);
        safeSet(scope, "1", "test", true);
        safeSet(scope, "2", "test", false);
        safeSet(scope, "2", "test", true);
        safeSet(scope, "", "test", false);
        safeSet(scope, "", "test", true);
    }

    public void testIsDefine() {
        Scope<String, String> scope = new Scope<String, String>();
        scope.set("1", "test1");
        scope.set("2", "test2");
        scope.set("3", "test3");
        scope.set("4", "test4");
        scope.set("1", "test5");
        assertTrue(scope.isDefined("1"));
        assertTrue(scope.isDefined("2"));
        assertTrue(scope.isDefined("3"));
        assertTrue(scope.isDefined("4"));
    }

    public void testGet() {
        Scope<String, String> scope = new Scope<String, String>();
        scope.set("1", "test1");
        assertEquals("test1", scope.get("1"));
        scope.set("2", "test2");
        assertEquals("test2", scope.get("2"));
        scope.set("3", "test3");
        assertEquals("test3", scope.get("3"));
        scope.set("4", "test4");
        assertEquals("test4", scope.get("4"));
        scope.set("1", "test5");
        assertEquals("test5", scope.get("1"));
    }

    private String safeGet(Scope<String, String> scope, String key, boolean willThrow) {
        try {
            String value = scope.safeGet(key);
            if (willThrow) {
                fail();
            }
            return value;
        } catch (UndefinedException oe) {
            if (!willThrow) {
                fail();
            }
            return null;
        }
    }

    public void testSafeGet() {
        Scope<String, String> scope = new Scope<String, String>();
        scope.set("1", "test1");
        assertEquals("test1", safeGet(scope, "1", false));
        scope.set("2", "test2");
        assertEquals("test2", safeGet(scope, "2", false));
        scope.set("3", "test3");
        assertEquals("test3", safeGet(scope, "3", false));
        scope.set("4", "test4");
        assertEquals("test4", safeGet(scope, "4", false));
        scope.set("1", "test5");
        assertEquals("test5", safeGet(scope, "1", false));
        safeGet(scope, "5", true);
    }

    public void testKeySet() {
        Scope<String, String> scope = new Scope<String, String>();
        scope.set("1", "test1");
        scope.set("2", "test2");
        scope.set("3", "test3");
        scope.set("4", "test4");
        scope.set("1", "test5");
        assertEquals(4, scope.keySet().size());
    }

    public void testAllKeySet() {
        Scope<String, String> parent = new Scope<String, String>();
        Scope<String, String> scope = new Scope<String, String>(parent);
        parent.set("1", "parent1");
        scope.set("1", "test1");
        scope.set("2", "test2");
        scope.set("3", "test3");
        scope.set("4", "test4");
        scope.set("1", "test5");
        assertEquals(4, scope.allKeySet().size());
    }
}
