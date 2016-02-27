package org.lucylang.ljvm.machine.module;

import org.lucylang.ljvm.exception.CompileException;
import org.lucylang.ljvm.library.Loader;
import org.lucylang.ljvm.machine.instruction.InvalidInstruction;
import org.lucylang.ljvm.scope.OverdefinedException;
import org.lucylang.ljvm.scope.UndefinedException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class LinkerException extends CompileException {
    public LinkerException(String message) {
        super(message);
    }
}

public class Linker {
    public Module linkModules(ArrayList<Module> modules) throws UndefinedException, OverdefinedException, InvalidInstruction, LinkerException, NotExecutableException {
        Module m = new Module("main");
        Set<String> imports = new HashSet<String>();
        Set<String> packageName = new HashSet<String>();
        boolean isExecutable = false;
        for (int i = 0; i < modules.size(); i++) {
            Module module = modules.get(i);
            packageName.add(module.getName());
            imports.addAll(module.getImports());
            for (String routineName : module.routines.keySet()) {
                isExecutable = isExecutable || module.isMain();
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
                isExecutable = isExecutable || importModule.isMain();
                m.defineRoutine(routineName, importModule.getRoutine(routineName));
            }
        }

        if ((packageName.size() > 1 && !isExecutable) || packageName.size() == 0) {
            throw new LinkerException("package name does not match or no main function presents");
        }
        if (!isExecutable) {
            for (String name : packageName) {
                m.name = name;
            }
        }
        return m;
    }
}
