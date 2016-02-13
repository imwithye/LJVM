package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.Instruction;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.io.Serializable;
import java.util.HashMap;

public class Module implements Serializable {
    private HashMap<String, Routine> routines;

    public Module() {
        this.routines = new HashMap<String, Routine>();
    }

    public boolean hasMain() {
        return this.routines.get("main") != null;
    }

    public Module defineRoutine(String name, Routine routine) throws OverdefinedException {
        if (this.routines.get(name) != null) {
            throw new OverdefinedException();
        }
        this.routines.put(name, routine);
        return this;
    }

    public Routine getRoutine(String name) throws UndefinedException {
        Routine routine = this.routines.get(name);
        if (routine == null) {
            throw new UndefinedException();
        }
        return routine;
    }

    @Override
    public String toString() {
        try {
            String string = new String();
            for (String key : this.routines.keySet()) {
                Routine routine = this.routines.get(key);
                String instruction = new String();
                for (Instruction i : routine.getInstructions()) {
                    instruction += "\t" + i + "\n";
                }
                string += "\n" + key + " {\n" + instruction + "}\n";
            }
            return string;
        } catch (Exception e) {
            return new String();
        }
    }
}
