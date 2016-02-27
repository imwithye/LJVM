package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.scope.UndefinedException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Module implements Serializable {
    protected final String name;
    protected Scope<String, Routine> routines;
    protected Set<String> imports;

    public Module(String name) {
        this.name = name;
        this.routines = new Scope<String, Routine>(name);
        this.imports = new HashSet<String>();
    }

    public boolean isMain() {
        return this.routines.get(this.getName() + "::main") != null;
    }

    public String getName() {
        return this.name;
    }

    public Module imports(String moduleName) {
        this.imports.add(moduleName);
        return this;
    }

    public Module defineRoutine(String name, Routine routine) throws OverdefinedException, InvalidInstruction {
        if (!name.contains("::")) {
            name = this.getName() + "::" + name;
        }
        this.routines.safeSet(name, routine.adjustCalls(this));
        return this;
    }

    public Routine getRoutine(String name) throws UndefinedException {
        if (!name.contains("::")) {
            name = this.getName() + "::" + name;
        }
        return this.routines.safeGet(name);
    }

    public Set<String> getImports() {
        return this.imports;
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
