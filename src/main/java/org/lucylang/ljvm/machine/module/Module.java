package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.library.Loader;
import org.lucylang.ljvm.machine.instruction.*;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.Scope;
import org.lucylang.ljvm.scope.UndefinedException;

import java.io.Serializable;
import java.util.Set;

public class Module implements Serializable {
    protected Scope<String, Routine> routines;
    protected Scope<String, Module> imports;

    public Module(String name) {
        this.routines = new Scope<String, Routine>(name);
        this.imports = new Scope<String, Module>(name + "#imports");
    }

    public boolean isMain() {
        return this.routines.get("main") != null;
    }

    public Module imports(String moduleName) throws UndefinedException, OverdefinedException {
        if (moduleName.startsWith("./")) {
            //TODO: import from file
            throw new UnsupportedOperationException();
        } else {
            Module m = Loader.getLoader().getModule(moduleName);
            this.imports.safeSet(moduleName, m);
        }
        return this;
    }

    public Module defineRoutine(String name, Routine routine) throws OverdefinedException {
        this.routines.safeSet(name, routine);
        return this;
    }

    public Routine getRoutine(String moduleName, String name) throws UndefinedException {
        Module m = this.imports.safeGet(moduleName);
        return m.getRoutine(name);
    }

    public Routine getRoutine(String name) throws UndefinedException {
        return this.routines.safeGet(name);
    }

    public Routine getCorrespondingRoutine(String name) throws UndefinedException, OverdefinedException {
        Routine routine, routineNext;
        routine = this.routines.get(name);
        if (routine != null) {
            // Module overrides all imports
            return routine;
        }

        // Routine is Null
        Set<String> importModules = this.imports.keySet();
        for (String importModule : importModules) {
            Module m = this.imports.get(importModule);
            routineNext = m.routines.get(name);
            if (routine != null && routineNext != null) {
                throw new OverdefinedException("Duplicate definition of " + name + "in imports");
            }
            if(routineNext != null) {
                routine = routineNext;
            }
        }

        if (routine == null) {
            routine = this.getRoutine(name);
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
