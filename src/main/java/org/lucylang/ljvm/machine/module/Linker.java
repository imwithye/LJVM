package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.library.Loader;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Linker {
    public Module linkModules(String name, ArrayList<Module> modules) throws UndefinedException, OverdefinedException, InvalidInstruction {
        Module m = new Module(name);
        Set<String> imports = new HashSet<String>();
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            imports.addAll(module.getImports());
            for (String routineName : module.routines.keySet()) {
                m.defineRoutine(routineName, module.getRoutine(routineName));
            }
        }
        Loader loader = Loader.getLoader();
        for (String importName : imports) {
            Module importModule;
            try {
                importModule = loader.getModule(importName);
            } catch (UndefinedException e) {
                m.imports(importName);
                continue;
            }
            for (String routineName : importModule.routines.keySet()) {
                m.defineRoutine(routineName, importModule.getRoutine(routineName));
            }
        }
        return m;
    }
}
