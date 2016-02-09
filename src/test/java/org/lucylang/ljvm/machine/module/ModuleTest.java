package org.lucylang.ljvm.machine.module;

import org.json.JSONArray;
import org.lucylang.ljvm.LJVMTest;
import org.json.JSONObject;

public class ModuleTest extends LJVMTest {
    public ModuleTest(String testName) {
        super(testName);
    }

    public void testJSON() throws Exception {
        JSONObject object = new JSONObject();
        object.put("name", "fibonacci");
        JSONObject[] vars = new JSONObject[]{
                new JSONObject().put("name", "result").put("value", 10),
                new JSONObject().put("name", "text").put("value", "result is "),
        };
        JSONObject[] routines = new JSONObject[]{

        };
        object.put("vars", new JSONArray(vars));
        object.put("routines", new JSONArray(routines));
        Module m = Module.loadFromJSON(object);
        assertEquals(2, m.getVars().length);
        assertEquals("result", m.getVars()[0].getRef(0));
        assertEquals("text", m.getVars()[1].getRef(0));
    }
}
